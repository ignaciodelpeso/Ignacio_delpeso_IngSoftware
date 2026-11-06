# Order Management (demo2)

Aplicación de escritorio Java Swing (patrón MVC) para consultar pedidos de `orders.json`
y ver sus totales en euros y dólares.

## Requisitos
- Java 17+ (para ejecutar) y Maven (para compilar).

## Estructura
```
src/main/java/demo2/
  Main.java                    arranque, carga orders.json
  model/    article, order     datos y cálculo de importes
  view/     OrderView          ventana Swing (icono: resources/app.png)
  controller/OrderController   búsqueda de pedidos
  service/  ExchangeRateService  tipo de cambio EUR/USD
src/main/resources/            orders.json, app.png
```

## Compilar y ejecutar
```bash
mvn clean package
java -jar target/demo2-1.1.jar
```
El jar generado es ejecutable (incluye dependencias y `Main-Class` vía `maven-shade-plugin`),
por lo que también se abre con doble clic si Java 17+ está instalado.

## Tipo de cambio EUR/USD

El tipo de cambio varía, así que **no se guarda como constante**: se consulta a un servicio externo
cada vez que se muestra un pedido (valor en el momento de visualización).

- **Servicio:** [Frankfurter](https://frankfurter.dev) (datos del Banco Central Europeo), gratuito y sin clave de API:
  `https://api.frankfurter.dev/v1/latest?base=EUR&symbols=USD` → `{"rates":{"USD":1.16...}}`.
- **Implementación:** `ExchangeRateService` usa `java.net.http.HttpClient` (incluido en el JDK 17, sin dependencias nuevas)
  y Jackson (ya usado en el proyecto) para leer `rates.USD`. Timeouts de 3-4 s.
- **Sin bloquear la interfaz:** `OrderController` hace la llamada en un `SwingWorker` (hilo de fondo).
- **Tolerancia a fallos:** si no hay red, hay timeout, error HTTP o JSON inesperado, se usa un valor de respaldo
  (`FALLBACK_RATE`) y la ventana lo indica: «service unavailable, using fallback rate».
- **Alternativas descartadas:** APIs que exigen clave (exchangerate-api, Open Exchange Rates), porque no podemos
  publicar claves en un repositorio; y cachear el valor, porque el enunciado pide el cambio en el momento de visualización.
- **Test:** `ExchangeRateServiceTest` prueba el parseo de la respuesta sin necesidad de red.

## Release
El workflow `.github/workflows/main.yml` publica una release en GitHub (`v<versión>`) con el jar ejecutable
cuando un push a `main` modifica `pom.xml` (cambiar `<version>` para nueva release).

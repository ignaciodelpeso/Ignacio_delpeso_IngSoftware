package demo2.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Obtiene el tipo de cambio EUR/USD en el momento de la consulta.
 *
 * Fuente: API pública Frankfurter (datos del BCE, sin clave de API):
 *   https://api.frankfurter.dev/v1/latest?base=EUR&symbols=USD
 * Respuesta ejemplo: {"amount":1.0,"base":"EUR","date":"2026-11-06","rates":{"USD":1.1629}}
 *
 * Si el servicio no responde (sin red, timeout, error HTTP o JSON inesperado)
 * se devuelve FALLBACK_RATE y se marca el resultado como "no en vivo".
 */
public class ExchangeRateService {

    public static final String URL = "https://api.frankfurter.dev/v1/latest?base=EUR&symbols=USD";
    public static final double FALLBACK_RATE = 1.162976;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** Tipo de cambio y si procede del servicio externo (live) o del valor de respaldo. */
    public record Rate(double value, boolean live) {}

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    public Rate getEurUsdRate() {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(URL))
                    .timeout(Duration.ofSeconds(4))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return new Rate(FALLBACK_RATE, false);
            }
            return new Rate(parseRate(response.body()), true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new Rate(FALLBACK_RATE, false);
        } catch (Exception e) {
            return new Rate(FALLBACK_RATE, false);
        }
    }

    /** Extrae rates.USD del JSON de Frankfurter. Lanza IllegalArgumentException si no es válido. */
    static double parseRate(String json) {
        try {
            JsonNode usd = MAPPER.readTree(json).path("rates").path("USD");
            if (!usd.isNumber() || usd.asDouble() <= 0) {
                throw new IllegalArgumentException("Respuesta sin rates.USD válido");
            }
            return usd.asDouble();
        } catch (java.io.IOException e) {
            throw new IllegalArgumentException("JSON inválido", e);
        }
    }
}

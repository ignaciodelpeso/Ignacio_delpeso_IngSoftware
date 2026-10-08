package demo2.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class orderTest {

    private static final double DELTA = 0.0001;

    private List<article> articulos;
    private order pedido;

    @BeforeEach
    void setUp() {
        articulos = new ArrayList<>();
        articulos.add(new article("Raton", 2, 10.0, 0));    // bruto 20, desc 20
        articulos.add(new article("Teclado", 1, 50.0, 10)); // bruto 50, desc 45
        pedido = new order("P001", articulos);
    }

    @Test
    void getId_devuelveElId() {
        assertEquals("P001", pedido.getId());
    }

    @Test
    void setId_cambiaElId() {
        pedido.setId("P002");
        assertEquals("P002", pedido.getId());
    }

    @Test
    void getArticulos_devuelveLaLista() {
        assertEquals(2, pedido.getArticulos().size());
        assertSame(articulos, pedido.getArticulos());
    }

    @Test
    void setArticulos_reemplazaLaLista() {
        List<article> nueva = new ArrayList<>();
        nueva.add(new article("Monitor", 1, 100.0, 0));

        pedido.setArticulos(nueva);

        assertEquals(1, pedido.getArticulos().size());
        assertEquals(100.0, pedido.getGrossTotal(), DELTA);
    }

    @Test
    void getGrossTotal_sumaLosBrutos() {
        assertEquals(70.0, pedido.getGrossTotal(), DELTA);
    }

    @Test
    void getDiscountedTotal_sumaLosConDescuento() {
        assertEquals(65.0, pedido.getDiscountedTotal(), DELTA);
    }

    @Test
    void totales_pedidoVacio() {
        order vacio = new order("P003", new ArrayList<>());

        assertEquals(0.0, vacio.getGrossTotal(), DELTA);
        assertEquals(0.0, vacio.getDiscountedTotal(), DELTA);
    }

    @Test
    void totales_seActualizanAlAnadirArticulo() {
        pedido.getArticulos().add(new article("Cable", 4, 5.0, 50)); // bruto 20, desc 10

        assertEquals(90.0, pedido.getGrossTotal(), DELTA);
        assertEquals(75.0, pedido.getDiscountedTotal(), DELTA);
    }

    @Test
    void toString_contieneIdYArticulos() {
        String s = pedido.toString();

        assertTrue(s.contains("P001"));
        assertTrue(s.contains("Raton"));
        assertTrue(s.contains("Teclado"));
    }
}
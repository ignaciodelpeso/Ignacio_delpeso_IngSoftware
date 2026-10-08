package demo2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class articleTest {

    private static final double DELTA = 0.0001;

    @Test
    void getGrossAmount_multiplicaCantidadPorPrecio() {
        article a = new article("Raton", 3, 10.0, 0);
        assertEquals(30.0, a.getGrossAmount(), DELTA);
    }

    @Test
    void getDiscountedAmount_sinDescuento() {
        article a = new article("Raton", 3, 10.0, 0);
        assertEquals(30.0, a.getDiscountedAmount(), DELTA);
    }

    @Test
    void getDiscountedAmount_conDescuento() {
        article a = new article("Teclado", 2, 50.0, 10);
        assertEquals(90.0, a.getDiscountedAmount(), DELTA);
    }

    @Test
    void getDiscountedAmount_descuentoTotal() {
        article a = new article("Regalo", 5, 20.0, 100);
        assertEquals(0.0, a.getDiscountedAmount(), DELTA);
    }

    @Test
    void getGrossAmount_cantidadCero() {
        article a = new article("Nada", 0, 99.99, 20);
        assertEquals(0.0, a.getGrossAmount(), DELTA);
        assertEquals(0.0, a.getDiscountedAmount(), DELTA);
    }

    @Test
    void gettersYSetters() {
        article a = new article("A", 1, 1.0, 0);

        a.setNombre("B");
        a.setCantidad(4);
        a.setPrecio(2.5);
        a.setDescuento(15);

        assertEquals("B", a.getNombre());
        assertEquals(4, a.getCantidad());
        assertEquals(2.5, a.getPrecio(), DELTA);
        assertEquals(15, a.getDescuento(), DELTA);
    }

    @Test
    void toString_contieneLosCampos() {
        article a = new article("Raton", 2, 10.0, 5);
        String s = a.toString();

        assertTrue(s.contains("Raton"));
        assertTrue(s.contains("cantidad=2"));
        assertTrue(s.contains("precio=10.0"));
        assertTrue(s.contains("descuento=5.0"));
    }
}
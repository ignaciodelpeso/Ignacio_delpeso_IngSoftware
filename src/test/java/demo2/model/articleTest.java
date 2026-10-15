package demo2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class articleTest {

    @Test
    void testGetGrossAmount() {
        article articulo = new article("Teclado", 2, 50.0, 10.0);
        double resultado = articulo.getGrossAmount();
        assertEquals(100.0, resultado, 0.001);
    }

    @Test
    void testGetDiscountedAmount() {
        article articulo = new article("Teclado", 2, 50.0, 10.0);
        double resultado = articulo.getDiscountedAmount();
        assertEquals(90.0, resultado, 0.001);
    }
}
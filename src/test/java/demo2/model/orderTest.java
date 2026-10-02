package demo2.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class orderTest {

    @Test
    void testGetGrossTotal() {
        article articulo1 = new article("Teclado", 2, 50.0, 10.0);
        article articulo2 = new article("Raton", 1, 30.0, 20.0);

        List<article> articulos = new ArrayList<>();
        articulos.add(articulo1);
        articulos.add(articulo2);

        order pedido = new order("PEDIDO-001", articulos);
        double resultado = pedido.getGrossTotal();

        assertEquals(130.0, resultado, 0.001);
    }

    @Test
    void testGetDiscountedTotal() {
        article articulo1 = new article("Teclado", 2, 50.0, 10.0);
        article articulo2 = new article("Raton", 1, 30.0, 20.0);

        List<article> articulos = new ArrayList<>();
        articulos.add(articulo1);
        articulos.add(articulo2);

        order pedido = new order("PEDIDO-001", articulos);
        double resultado = pedido.getDiscountedTotal();

        assertEquals(114.0, resultado, 0.001);
    }
}
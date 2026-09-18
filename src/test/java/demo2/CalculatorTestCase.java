package demo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTestCase {

    private static final double DELTA = 1e-9;

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // ---------- multiply ----------

    @Test
    @DisplayName("multiply: multiplicación normal")
    void multiply_normal() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    @DisplayName("multiply: multiplicación con cero")
    void multiply_withZero() {
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(0, calculator.multiply(5, 0));
        assertEquals(0, calculator.multiply(0, 0));
    }

    @Test
    @DisplayName("multiply: multiplicación con números negativos")
    void multiply_withNegatives() {
        assertEquals(-6, calculator.multiply(-2, 3));
        assertEquals(-6, calculator.multiply(2, -3));
        assertEquals(6, calculator.multiply(-2, -3));
    }

    // ---------- concat ----------

    @Test
    @DisplayName("concat: dos cadenas normales")
    void concat_normalStrings() {
        assertEquals("HolaMundo", calculator.concat("Hola", "Mundo"));
    }

    @Test
    @DisplayName("concat: primer parámetro null devuelve \"empty\"")
    void concat_firstNull_returnsEmpty() {
        assertEquals("empty", calculator.concat(null, "Mundo"));
    }

    @Test
    @DisplayName("concat: segundo parámetro null devuelve \"empty\"")
    void concat_secondNull_returnsEmpty() {
        assertEquals("empty", calculator.concat("Hola", null));
    }

    @Test
    @DisplayName("concat: ambos parámetros null devuelve \"empty\"")
    void concat_bothNull_returnsEmpty() {
        assertEquals(Calculator.EMPTY, calculator.concat(null, null));
    }

    @Test
    @DisplayName("concat: cadena vacía no es null")
    void concat_emptyString_isNotNull() {
        assertEquals("Hola", calculator.concat("Hola", ""));
    }

    // ---------- sum ----------

    @Test
    @DisplayName("sum: suma normal")
    void sum_normal() {
        assertEquals(5.0, calculator.sum(2.0, 3.0), DELTA);
        assertEquals(4.0, calculator.sum(1.5, 2.5), DELTA);
    }

    @Test
    @DisplayName("sum: suma con valores negativos")
    void sum_withNegatives() {
        assertEquals(-5.0, calculator.sum(-2.0, -3.0), DELTA);
        assertEquals(-1.0, calculator.sum(2.0, -3.0), DELTA);
        assertEquals(0.0, calculator.sum(-2.5, 2.5), DELTA);
    }

    // ---------- discount ----------

    @Test
    @DisplayName("discount: descuento válido")
    void discount_validPercentage() {
        assertEquals(180.0, calculator.discount(200.0, 10.0), DELTA);
        assertEquals(75.0, calculator.discount(100.0, 25.0), DELTA);
    }

    @Test
    @DisplayName("discount: 0% no cambia el importe")
    void discount_zeroPercent() {
        assertEquals(200.0, calculator.discount(200.0, 0.0), DELTA);
    }

    @Test
    @DisplayName("discount: 100% deja el importe a cero")
    void discount_hundredPercent() {
        assertEquals(0.0, calculator.discount(200.0, 100.0), DELTA);
    }

    @Test
    @DisplayName("discount: porcentaje negativo lanza IllegalArgumentException")
    void discount_negativePercent_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(200.0, -1.0));
    }

    @Test
    @DisplayName("discount: porcentaje mayor que 100 lanza IllegalArgumentException")
    void discount_percentAbove100_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(200.0, 100.1));
    }

    // ---------- calculateTotal ----------

    @Test
    @DisplayName("calculateTotal: lista de importes devuelve la suma correcta")
    void calculateTotal_normalList() {
        List<Double> amounts = Arrays.asList(10.0, 20.5, 30.0);

        assertEquals(60.5, calculator.calculateTotal(amounts), DELTA);
    }

    @Test
    @DisplayName("calculateTotal: lista vacía devuelve 0.0")
    void calculateTotal_emptyList_returnsZero() {
        assertEquals(0.0, calculator.calculateTotal(Collections.emptyList()), DELTA);
    }
}

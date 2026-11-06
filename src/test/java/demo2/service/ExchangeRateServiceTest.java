package demo2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ExchangeRateServiceTest {

    @Test
    void parsesFrankfurterResponse() {
        String json = "{\"amount\":1.0,\"base\":\"EUR\",\"date\":\"2026-11-06\",\"rates\":{\"USD\":1.1629}}";
        assertEquals(1.1629, ExchangeRateService.parseRate(json), 1e-9);
    }

    @Test
    void rejectsResponseWithoutUsdRate() {
        assertThrows(IllegalArgumentException.class, () -> ExchangeRateService.parseRate("{\"rates\":{}}"));
    }

    @Test
    void rejectsInvalidJson() {
        assertThrows(IllegalArgumentException.class, () -> ExchangeRateService.parseRate("no es json"));
    }
}

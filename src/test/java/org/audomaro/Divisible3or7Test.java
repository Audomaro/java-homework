package org.audomaro;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Divisible3or7Test {
    @Test
    public void Divisible3or7CountTest() {
        int expectedCount = 429;

        int actualCount = Divisible3or7.calculate();

        assertEquals(expectedCount, actualCount);
    }
}

package org.week2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.week2.Task2.*;

public class Task2Test {
    @Test
    public void testArraySizeEqualsExpected() {
        int expectedSize = 10;

        int[] data = createArray(10, 10);
        int currentSize = data.length;

        assertEquals(expectedSize, currentSize);
    }

    @Test
    public void testArrayElementBelowLimit() {
        int size = 10;
        int limit = 10;

        int[] data = createArray(size, limit);
        int firstElement = data[0];

        assertTrue(firstElement < limit);
    }

    @Test
    public void testArrayElementIsNonNegative() {
        int size = 10;
        int limit = 10;

        int[] data = createArray(size, limit);
        int firstElement = data[0];

        assertTrue(firstElement >= 0);
    }
}

package org.week2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.week2.Task1.squareArray;

public class Task1Test {
    @Test
    public void test36() {
        int expected = 36;

        int[] data = squareArray(10);
        int current = data[6];

        assertEquals(expected,current);
    }

    @Test
    public void test25() {
        int expected = 25;

        int[] data = squareArray(10);
        int current = data[5];

        assertEquals(expected,current);
    }
}

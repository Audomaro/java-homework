package org.week2;

import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        int[] ints = createArray(5, 100);

        for (int data : ints) {
            System.out.println(data);
        }
    }

    public static int[] createArray(int size, int limit) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] =    random.nextInt(0,limit);
        }

        return array;
    }
}

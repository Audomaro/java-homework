package org.week2;

public class Task1 {
    public static void main(String[] args) {
        int top = 10;
        int[] elements = squareArray(top);

        for (int i = 0; i < top; i++) {
            System.out.printf("Square of %d is %d %n", i, elements[i]);
        }
    }

    public static int[] squareArray(int top) {
        int[] elements = new int[top];

        for(int i = 0; i < top; i++) {
            elements[i] = i * i;
        }

        return elements;
    }
}

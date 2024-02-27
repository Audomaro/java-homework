package org.audomaro;

public class Divisible3or7 {
    public static void main(String[] args) {
        print();

        int count = calculate();
        System.out.println("Count: " + count);
    }

    public  static void print() {
        System.out.println("Numbers divisible by 3 or 7 in the range -500 to +500 using a while loop:");

        int number = -500;

        while (number <= 500) {
            if (number % 3 == 0 || number % 7 == 0) {
                System.out.println(number);
            }
            number++;
        }
    }

    public static int calculate() {

        int count = 0;

        for (int i = -500; i <= 500; i++) {
            if (i % 3 == 0 || i % 7 == 0) {
                count++;
            }
        }

        return count;
    }
}

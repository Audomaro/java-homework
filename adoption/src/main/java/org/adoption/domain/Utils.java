package org.adoption.domain;

import java.time.LocalDate;
import java.util.Random;

public class Utils {

    public static LocalDate RandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}

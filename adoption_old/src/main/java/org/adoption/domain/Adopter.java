package org.adoption.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Random;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@JsonSerialize
public class Adopter {

    private int id;
    private String name;
    private String phoneNumber;
    private LocalDate adoptionDate;
    Pet pet;

    final Faker faker = new Faker();

    public Adopter(){
        this.setName( new Faker().name().fullName());
        this.setPhoneNumber(new Faker().phoneNumber().phoneNumber());
        this.setAdoptionDate(randomAdoptionDate());
        this.pet = new Pet();
    }

    public Adopter(Pet pet) {
        this.setName( new Faker().name().fullName());
        this.setPhoneNumber(new Faker().phoneNumber().phoneNumber());
        this.setAdoptionDate(randomAdoptionDate());
        this.setPet(pet);
    }

    public static  LocalDate randomAdoptionDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}

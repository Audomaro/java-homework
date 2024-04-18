package org.adoption.domain;

import com.github.javafaker.Faker;

import java.util.Random;
import java.time.LocalDate;

public class Adopter {

    private int id;
    private String name;
    private String phoneNumber;
    private LocalDate adoptionDate;
    Pet pet;

    final Faker faker = new Faker();

    public Adopter(){
        this.id = faker.number().numberBetween(1, 999);
        this.pet = new Pet();
    }

    public Adopter(int id, String name, String phoneNumber, LocalDate adoptionDate, Pet pet) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.adoptionDate = adoptionDate;
        this.pet = pet;
    }

    public Adopter(String name, String phoneNumber, LocalDate adoptionDate, Pet pet) {
        this(
                new Faker().random().nextInt(10000),
                name,
                phoneNumber,
                adoptionDate,
                pet);
    }

    public Adopter(Pet pet) {
        this(
                new Faker().random().nextInt(10000),
                new Faker().name().fullName(),
                new Faker().phoneNumber().phoneNumber(),
                randomAdoptionDate(),
                pet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public static  LocalDate randomAdoptionDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", adoptionDate=" + adoptionDate +
                ", pet=" + pet +
                '}';
    }
}

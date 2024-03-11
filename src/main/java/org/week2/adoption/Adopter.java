package org.week2.adoption;

import java.time.LocalDate;

public class Adopter {
    private int id;
    private String name;
    private String numberPhone;
    private LocalDate dateOfAdoption;
    private PetType petTypeAdopted;
    private BreedPet breedPet;
    private int agePet;

    public Adopter(int id, String name, String numberPhone, LocalDate dateOfAdoption, PetType petTypeAdopted, BreedPet breedPet, int agePet) {
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
        this.dateOfAdoption = dateOfAdoption;
        this.petTypeAdopted = petTypeAdopted;
        this.breedPet = breedPet;
        this.agePet = agePet;
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

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public LocalDate getDateOfAdoption() {
        return dateOfAdoption;
    }

    public void setDateOfAdoption(LocalDate dateOfAdoption) {
        this.dateOfAdoption = dateOfAdoption;
    }

    public PetType getPetTypeAdopted() {
        return petTypeAdopted;
    }

    public void setPetTypeAdopted(PetType petTypeAdopted) {
        this.petTypeAdopted = petTypeAdopted;
    }

    public BreedPet getBreedPet() {
        return breedPet;
    }

    public void setBreedPet(BreedPet breedPet) {
        this.breedPet = breedPet;
    }

    public int getAgePet() {
        return agePet;
    }

    public void setAgePet(int agePet) {
        this.agePet = agePet;
    }
}

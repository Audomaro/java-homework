package org.week2.adoption;

import java.time.LocalDate;

public class Adopter {
    private int id;
    private String name;

    public Adopter(int id, String name, String numberPhone, LocalDate dateOfAdoption, Pets typePetOfAdopted) {
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
        this.dateOfAdoption = dateOfAdoption;
        this.typePetOfAdopted = typePetOfAdopted;
    }

    private String numberPhone;
    private LocalDate dateOfAdoption;
    private  Pets typePetOfAdopted;

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

    public Pets getTypePetOfAdopted() {
        return typePetOfAdopted;
    }

    public void setTypePetOfAdopted(Pets typePetOfAdopted) {
        this.typePetOfAdopted = typePetOfAdopted;
    }
}

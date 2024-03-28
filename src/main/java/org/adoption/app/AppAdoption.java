package org.adoption.app;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppAdoption {
    public static void main(String[] args) {
        List<Adopter> adopterList = new ArrayList<>();

        adopterList.add(new Adopter(
                1,
                "Celia / ADOPTER ALL DATA",
                "123-456-7890",
                LocalDate.now(),
                new Pet(1,"Michi", Pet.PetType.CAT, Pet.BreedType.British_Shorthair)
        ));

        adopterList.add(new Adopter(
                "Mary / ADOPTER RANDOM ID",
                "223-456-7890",
                LocalDate.now(),
                new Pet(2,"Bob", Pet.PetType.DOG, Pet.BreedType.Buldog)
        ));

        adopterList.add(new Adopter(
                new Pet(Pet.PetType.CAT)
        ));

        adopterList.add(new Adopter(
                new Pet(Pet.PetType.DOG)
        ));

        adopterList.add(new Adopter(
                new Pet(Pet.PetType.TURTLE)
        ));

        for (Adopter adopter : adopterList) {
            System.out.println(adopter);
        }
    }
}

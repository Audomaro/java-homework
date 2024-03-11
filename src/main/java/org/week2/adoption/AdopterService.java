package org.week2.adoption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdopterService {
    public static void main(String[] args) {
         List<Adopter> adopterList = new ArrayList<>();

        adopterList.add(new Adopter(
                1,
                "Bill",
                "878-8878-12-34",
                LocalDate.now(),
                PetType.Cat,
                BreedPet.Buldog,
                1)
        );

        adopterList.add(new Adopter(
                2,
                "Sack",
                "878-8248-54-34",
                LocalDate.now(),
                PetType.Cat,
                BreedPet.British_Shorthair,
                2)
        );

        for (Adopter adopter : adopterList) {
            System.out.println(adopter);
        }
    }
}

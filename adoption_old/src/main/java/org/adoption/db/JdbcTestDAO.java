package org.adoption.db;

import org.adoption.dao.AdopterDAOImpl;
import org.adoption.dao.PetDAOImpl;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;

import java.util.List;

public class JdbcTestDAO {
    public static void main(String[] args) {
        PetDAOImpl petDAO = new PetDAOImpl();
        AdopterDAOImpl adopterDAO = new AdopterDAOImpl();
        List<Adopter> adopters;


//        for (int i = 0; i < 50; i++) {
//            adopterDAO.insert(new Adopter());
//            petDAO.insert(new Pet());
//        }


        System.out.println("::::::::::::::::: New Pet :::::::::::::::::");
        Pet newPet = new Pet();
        Pet insertedPet = petDAO.insert(newPet);
        System.out.println(insertedPet);
        System.out.println();

        System.out.println("::::::::::::::::: Update Pet :::::::::::::::::");
        Pet updatePet = new Pet();
        updatePet.setPetId(insertedPet.getPetId());
        boolean updatedPet = petDAO.update(updatePet);
        System.out.println("Update Result: " + updatedPet);
        System.out.println();

        System.out.println("::::::::::::::::: Get By ID (" + insertedPet.getPetId() + ") :::::::::::::::::");
        System.out.println(petDAO.findByID(insertedPet.getPetId()));
        System.out.println();

        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
        petDAO.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: Delete By ID :::::::::::::::::");
        boolean deletedPet =  petDAO.delete(9999999);
        System.out.println("Result: " + deletedPet);
        System.out.println();

        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
        petDAO.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: New Adopter With Pet :::::::::::::::::");
        Adopter newAdopter = new Adopter();
        Adopter insertedAdopter = adopterDAO.insert(newAdopter);
        System.out.println(insertedAdopter);
        System.out.println();

        System.out.println("::::::::::::::::: Update Pet :::::::::::::::::");
        Adopter updateAdopter = new Adopter();
        updateAdopter.setId(insertedAdopter.getId());
        boolean updatedAdopter = adopterDAO.update(updateAdopter);
        System.out.println("Update Result: " + updatedAdopter);
        System.out.println();

        System.out.println("::::::::::::::::: FIN BY ID " + newAdopter.getId() + " :::::::::::::::::");
        Adopter adopter =  adopterDAO.findByID(newAdopter.getId());
        System.out.println(adopter);
        System.out.println();

        System.out.println("::::::::::::::::: Find By Name 'A' :::::::::::::::::");
        adopters =  adopterDAO.findByName("A");
        adopters.forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
        adopters =  adopterDAO.findAll();
        adopters.forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: Delete By ID 11:::::::::::::::::");
        boolean deleteDelete =  adopterDAO.delete(11);
        System.out.println("Result: " + deleteDelete);
    }
}

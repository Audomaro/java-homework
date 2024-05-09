package org.adoption.db;

import org.adoption.dao.AdopterDAOImpl;
import org.adoption.dao.PetDAOImpl;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;

import java.util.List;

public class JdbcTestDAO {
    public static void main(String[] args) {

        System.out.println("::::::::::::::::: New Pet :::::::::::::::::");
        PetDAOImpl petDAO = new PetDAOImpl();

        Pet newPet = new Pet();
        Pet insertedPet = petDAO.insert(newPet);
        System.out.println(insertedPet);
        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
        petDAO.findAll().forEach(System.out::println);
//        System.out.println("::::::::::::::::: Update Pet :::::::::::::::::");
//        Pet updatePet = new Pet();
//        updatePet.setPetId(insertedPet.getPetId());
//        boolean updatedPet = petDAO.update(updatePet);
//        System.out.println("Result: " + updatedPet);
//
//        System.out.println("::::::::::::::::: Get By ID (" + insertedPet.getPetId() + ") :::::::::::::::::");
//        System.out.println(petDAO.findByID(insertedPet.getPetId()));
//
//        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
//        petDAO.findAll().forEach(System.out::println);
//
//        System.out.println("::::::::::::::::: Delete By ID :::::::::::::::::");
//        boolean deletedPet =  petDAO.delete(9999999);
//        System.out.println("Result: " + deletedPet);

        /*


        List<Adopter> adopters;

        System.out.println("::::::::::::::::: UPDATE ADOPTER WITH ID 11 :::::::::::::::::");

        Adopter oldAdopter = dao.findByID(11);
        System.out.println("Old: ");
        System.out.println(oldAdopter);

        Adopter updateAdopter = new Adopter();
        updateAdopter.setId(oldAdopter.getId());
        boolean result = dao.update(updateAdopter);

        System.out.println("Result: " + result);
        System.out.println("New: ");
        System.out.println(dao.findByID(oldAdopter.getId()));

        System.out.println("::::::::::::::::: INSERT ADOPTER WITH PET :::::::::::::::::");
        Adopter newAdopter = new Adopter();
        Adopter insertedAdopter = dao.insert(newAdopter);
        System.out.println(insertedAdopter);
        System.out.println();

        System.out.println("::::::::::::::::: FIN BY ID '8' :::::::::::::::::");
        Adopter adopter =  dao.findByID(1);
        System.out.println(adopter);
        System.out.println();

        System.out.println("::::::::::::::::: Find By Name 'Gil' :::::::::::::::::");
        adopters =  dao.findByName("Gil");
        adopters.forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
        adopters =  dao.findAll();
        adopters.forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: Delete By ID :::::::::::::::::");
        boolean deleteDelete =  dao.delete(11);
        System.out.println("Result: " + deleteDelete);
        */

    }
}

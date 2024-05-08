package org.adoption.db;

import org.adoption.dao.JdbcTemplateDAO;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;

import java.util.List;

public class JdbcTestDAO {
    public static void main(String[] args) {
        JdbcTemplateDAO dao = new JdbcTemplateDAO();
        List<Adopter> adopters;

        System.out.println("::::::::::::::::: Find By Name 'Gil' :::::::::::::::::");
        adopters =  dao.findByName("Gil");
        adopters.forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: FIN BY ID '8' :::::::::::::::::");
        Adopter adopter =  dao.findByID(8);
        System.out.println(adopter);
        System.out.println();

        System.out.println("::::::::::::::::: Find All :::::::::::::::::");
        adopters =  dao.findAll();
        adopters.forEach(System.out::println);
        System.out.println();

        System.out.println("::::::::::::::::: INSERT ADOPTER WITH PET :::::::::::::::::");
        Adopter newAdopter = new Adopter();
        newAdopter.setPet(new Pet(Pet.PetType.TURTLE));
        Adopter insertedAdopter = dao.insert(newAdopter);
        System.out.println(insertedAdopter);
        System.out.println();
    }
}

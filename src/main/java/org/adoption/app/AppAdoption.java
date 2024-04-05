package org.adoption.app;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.jconfig.AppConfig;
import org.adoption.services.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Comparator;

public class AppAdoption {
    public static void main(String[] args) {

        runAs("dev");
        runAs("prod");
//        AdopterServiceImpl adopterService = new AdopterServiceImpl();
//
//        genData(adopterService);
//
//        System.out.println("- Order by number phone");
//        Comparator<Adopter> comparator = Comparator.comparing(Adopter::getPhoneNumber);
//        adopterService.orderBy(comparator).forEach(System.out::println);
//
//        System.out.println("- Order by id");
//        comparator = Comparator.comparing(Adopter::getId);
//        adopterService.orderBy(comparator).forEach(System.out::println);
//
//        System.out.println("- Order by name");
//        comparator = Comparator.comparing(Adopter::getName);
//        adopterService.orderBy(comparator).forEach(System.out::println);

//        // LISTAR TODOS LOS ADOPTERS
//        adopterService.findAll().forEach(System.out::println);
//
//        // ACTUALIZAR
//        Adopter oldAdopter = adopterService.findAll().getFirst();
//        oldAdopter.setPet(new Pet(Pet.PetType.TURTLE));
//        adopterService.updateAdopter(oldAdopter);
//
//        // ELIMINAR Y BUSQUEDA POR ID
//        Adopter adopterToRemove = adopterService.findByID(1);
//        adopterService.removeAdopter(adopterToRemove);
//
//        // LISTAR
//        adopterService.findAll().forEach(System.out::println);
//
//        // POR FECHA
//        System.out.println("FECHAS ORDENADAS");
//        adopterService.sortByAdoptionDate().forEach(System.out::println);
//
//
//        // POR PREDICADP
//        System.out.println("PREDICADO contains Cel");
//        Predicate<Adopter> where = w -> w.getName().contains("Mar");
//        adopterService.findBy(where).forEach(System.out::println);
    }

    private static void runAs(String profile) {
        System.out.printf("===================== [%s] =====================\r\n", profile);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles(profile);
        context.register(AppConfig.class);
        context.scan("org.adoption");
        context.refresh();

        AdopterService ss = context.getBean("AdopterService", AdopterService.class);
        ss.addAdopter(new Adopter(new Pet(Pet.PetType.CAT)));
        ss.addAdopter(new Adopter(new Pet(Pet.PetType.DOG)));
        ss.addAdopter(new Adopter(new Pet(Pet.PetType.TURTLE)));

        System.out.println("ORDER BY ***");
        Comparator<Adopter> orderBy = Comparator.comparing(Adopter::getId);
        ss.orderBy(orderBy).forEach(System.out::println);

        context.close();
    }

    private static void genData(AdopterServiceImpl adopterService) {
        adopterService.addAdopter(new Adopter(
                1,
                "Celia / ADOPTER ALL DATA",
                "123-456-7890",
                LocalDate.of(2024,4,4),
                new Pet(1,"Michi", Pet.PetType.CAT, Pet.BreedType.British_Shorthair)
        ));

        adopterService.addAdopter(new Adopter(
                2,
                "Mary / ADOPTER RANDOM ID",
                "123-456-7891",
                LocalDate.of(2024,1,1),
                new Pet(2,"Bob", Pet.PetType.DOG, Pet.BreedType.Buldog)
        ));

        adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.CAT)));
        adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.TURTLE)));
        adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.DOG)));
        adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.CAT)));
        adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.TURTLE)));
        adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.DOG)));
    }
}

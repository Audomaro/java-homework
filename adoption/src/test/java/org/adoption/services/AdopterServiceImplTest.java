package org.adoption.services;

import org.adoption.dao.AdopterDAO;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdopterServiceImplTest {

    @Mock
    private AdopterDAO adopterDAO;

    @InjectMocks
    private AdopterServiceImpl adopterService;

    @Test
    void addAdopter() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));

        Mockito.when(adopterDAO.insert(adopter)).thenReturn(adopter);

        assertEquals(adopter, adopterService.addAdopter(adopter));

        Mockito.verify(adopterDAO).insert(adopter);
    }

    @Test
    void removeAdopter() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterDAO.delete(1)).thenReturn(true);

        boolean result = adopterService.removeAdopter(adopter.getId());
        assertTrue(result);

        Mockito.verify(adopterDAO).delete(1);
    }

    @Test
    void updateAdopter() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterDAO.update(adopter)).thenReturn(true);

        boolean result = adopterService.updateAdopter(adopter);
        assertTrue(result);

        Mockito.verify(adopterDAO).update(adopter);
    }

    @Test
    void findAll() {
        List<Adopter> adopters = List.of(
                new Adopter(new Pet(Pet.PetType.CAT)),
                new Adopter(new Pet(Pet.PetType.TURTLE)),
                new Adopter(new Pet(Pet.PetType.DOG))
        );

        Mockito.when(adopterDAO.findAll()).thenReturn(adopters);

        List<Adopter> result = adopterService.findAll();

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).findAll();
    }

    @Test
    void findByID() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterDAO.findByID(1)).thenReturn(adopter);

        Adopter result = adopterService.findByID(1);
        assertEquals(adopter, result);

        Mockito.verify(adopterDAO).findByID(1);
    }

    @Test
    void findByName() {

        List<Adopter> adopters = List.of(
                new Adopter("John A", "", LocalDate.now(),new Pet(Pet.PetType.DOG)),
                new Adopter("John B", "", LocalDate.now(),new Pet(Pet.PetType.DOG)),
                new Adopter("John C", "", LocalDate.now(),new Pet(Pet.PetType.DOG))
        );

        Mockito.when(adopterDAO.findByName("John")).thenReturn(adopters);

        List<Adopter> result = adopterService.findByName("John");
        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).findByName("John");
    }

    @Test
    void sortByAdoptionDate() {
        LocalDate adoptionDate = LocalDate.now() ;
        List<Adopter> adopters = List.of(
                new Adopter("John A", "", adoptionDate,new Pet(Pet.PetType.DOG)),
                new Adopter("John B", "", adoptionDate,new Pet(Pet.PetType.DOG)),
                new Adopter("John C", "", adoptionDate,new Pet(Pet.PetType.DOG))
        );

        Mockito.when(adopterDAO.sortByAdoptionDate()).thenReturn(adopters);

        List<Adopter> result = adopterService.sortByAdoptionDate();

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).sortByAdoptionDate();
    }

    @Test
    void findBy() {
        LocalDate adoptionDate = LocalDate.now() ;
        List<Adopter> adopters = List.of(
                new Adopter("John A", "", adoptionDate,new Pet(Pet.PetType.DOG)),
                new Adopter("John B", "", adoptionDate,new Pet(Pet.PetType.DOG)),
                new Adopter("John C", "", adoptionDate,new Pet(Pet.PetType.DOG))
        );

        Predicate<Adopter> where = adopter -> adopter.getName().startsWith("Celi");

        Mockito.when(adopterDAO.findBy(where)).thenReturn(adopters);

        List<Adopter> result = adopterService.findBy(where);

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).findBy(where);
    }

    @Test
    void orderBy() {
        LocalDate adoptionDate = LocalDate.now() ;
        List<Adopter> adopters = List.of(
                new Adopter("John A", "", adoptionDate,new Pet(Pet.PetType.DOG)),
                new Adopter("John B", "", adoptionDate,new Pet(Pet.PetType.DOG)),
                new Adopter("John C", "", adoptionDate,new Pet(Pet.PetType.DOG))
        );

        Comparator<Adopter> orderBy = Comparator.comparing(Adopter::getId);

        Mockito.when(adopterDAO.orderBy(orderBy)).thenReturn(adopters);

        List<Adopter> result = adopterService.orderBy(orderBy);

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).orderBy(orderBy);
    }
}
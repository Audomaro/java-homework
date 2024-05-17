package org.adoption.services;

import org.adoption.domain.PetType;
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
        Adopter adopter = new Adopter(new Pet(PetType.CAT));

        Mockito.when(adopterDAO.insert(adopter)).thenReturn(adopter);

        assertEquals(adopter, adopterService.addAdopter(adopter));

        Mockito.verify(adopterDAO).insert(adopter);
    }

    @Test
    void removeAdopter() {
        Adopter adopter = new Adopter(new Pet(PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterDAO.delete(1)).thenReturn(true);

        boolean result = adopterService.removeAdopter(adopter.getId());
        assertTrue(result);

        Mockito.verify(adopterDAO).delete(1);
    }

    @Test
    void updateAdopter() {
        Adopter adopter = new Adopter(new Pet(PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterDAO.update(adopter)).thenReturn(true);

        boolean result = adopterService.updateAdopter(adopter);
        assertTrue(result);

        Mockito.verify(adopterDAO).update(adopter);
    }

    @Test
    void findAllAdopters() {
        List<Adopter> adopters = List.of(
                new Adopter(new Pet(PetType.CAT)),
                new Adopter(new Pet(PetType.TURTLE)),
                new Adopter(new Pet(PetType.DOG))
        );

        Mockito.when(adopterDAO.findAll()).thenReturn(adopters);

        List<Adopter> result = adopterService.findAll();

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).findAll();
    }

    @Test
    void findAdopterByID() {
        Adopter adopter = new Adopter(new Pet(PetType.CAT));
        adopter.setId(1);

        Mockito.when(adopterDAO.findByID(1)).thenReturn(adopter);

        Adopter result = adopterService.findByID(1);
        assertEquals(adopter, result);

        Mockito.verify(adopterDAO).findByID(1);
    }

    @Test
    void findAdopterByName() {

        List<Adopter> adopters = List.of(
                Adopter.builder().name("Jonh A").build(),
                Adopter.builder().name("Jonh B").build(),
                Adopter.builder().name("Jonh C").build()
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
                Adopter.builder().adoptionDate(adoptionDate).build(),
                Adopter.builder().adoptionDate(adoptionDate).build(),
                Adopter.builder().adoptionDate(adoptionDate).build()
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
                Adopter.builder().name("Lily A").build(),
                Adopter.builder().name("Lilu B").build(),
                Adopter.builder().name("Licy C").build()
        );

        Predicate<Adopter> where = adopter -> adopter.getName().startsWith("Li");

        Mockito.when(adopterDAO.findBy(where)).thenReturn(adopters);

        List<Adopter> result = adopterService.findBy(where);

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).findBy(where);
    }

    @Test
    void orderBy() {
        List<Adopter> adopters = List.of(
                Adopter.builder().build(),
                Adopter.builder().build(),
                Adopter.builder().build()
        );

        Comparator<Adopter> orderBy = Comparator.comparing(Adopter::getId);

        Mockito.when(adopterDAO.orderBy(orderBy)).thenReturn(adopters);

        List<Adopter> result = adopterService.orderBy(orderBy);

        assertEquals(adopters, result);

        Mockito.verify(adopterDAO).orderBy(orderBy);
    }
}
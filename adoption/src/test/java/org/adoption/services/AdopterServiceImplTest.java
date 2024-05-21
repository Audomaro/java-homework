package org.adoption.services;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.repository.AdopterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdopterServiceImplTest {
    @Mock
    private AdopterRepository adopterRepository;

    @InjectMocks
    private AdopterServiceImpl adopterService;

    @Test
    void findAllAdopters() {
        List<Adopter> adopters = List.of(
                new Adopter(),
                new Adopter(),
                new Adopter()
        );

        when(adopterRepository.findAll()).thenReturn(adopters);

        List<Adopter> result = adopterService.findAllAdopters();

        assertNotNull(result);
        assertEquals(adopters.size(),result.size());

        verify(adopterRepository).findAll();
    }

    @Test
    void findAdopterById() {
        Adopter adopter = new Adopter();
        adopter.setId(1);

        when(adopterRepository.findById(1)).thenReturn(Optional.of(adopter));

        Optional<Adopter> result = adopterService.findAdopterById(adopter.getId());

        assertTrue(result.isPresent());
        assertEquals(adopter.getName(), result.get().getName());
        verify(adopterRepository).findById(adopter.getId());
    }

    @Test
    void findAdopterByName() {
        String name = "Ru";

        List<Adopter> adopters = List.of(
                new Adopter(),
                new Adopter(),
                new Adopter()
        );

        when(adopterRepository.findAdopterByName(name)).thenReturn(adopters);

        List<Adopter> result = adopterService.findAdopterByName(name);

        assertNotNull(result);
        assertEquals(adopters.size(),result.size());

        verify(adopterRepository).findAdopterByName(name);
    }

    @Test
    void findAdopterWithoutPets() {
        List<Adopter> adopters = List.of(
                new Adopter(),
                new Adopter(),
                new Adopter()
        );

        when(adopterRepository.findAdopterWithoutPets()).thenReturn(adopters);

        List<Adopter> result = adopterService.findAdopterWithoutPets();

        assertNotNull(result);
        assertEquals(adopters.size(),result.size());
        assertEquals(0, adopters.get(0).getPets().size());
        assertEquals(0, adopters.get(1).getPets().size());
        assertEquals(0, adopters.get(2).getPets().size());

        verify(adopterRepository).findAdopterWithoutPets();
    }

    @Test
    void findAdopterWithPets() {
        List<Adopter> adopters = new ArrayList<>();

        for (int i = 1; i <= 3;i++ ){
            Adopter adopter = new Adopter();
            adopter.setId(i);

            for (int j = 1; j <= i; j ++) {
                Pet pet = new Pet();
                pet.setId(i*j);
                pet.setAdopter(adopter);
                adopter.getPets().add(pet);
            }

            adopters.add(adopter);
        }

        when(adopterRepository.findAdopterWithPets()).thenReturn(adopters);

        List<Adopter> result = adopterService.findAdopterWithPets();

        assertNotNull(result);
        assertEquals(adopters.size(),result.size());
        assertEquals(1, adopters.get(0).getPets().size());
        assertEquals(2, adopters.get(1).getPets().size());
        assertEquals(3, adopters.get(2).getPets().size());

        verify(adopterRepository).findAdopterWithPets();
    }

    @Test
    void addAdopterWithPets() {
        Adopter adopter = new Adopter();
        adopter.setId(1);

        for (int j = 0; j < 3; j ++) {
            Pet pet = new Pet();
            pet.setId(j+1);
            pet.setAdopter(adopter);
            adopter.getPets().add(pet);
        }

        when(adopterRepository.save(any(Adopter.class))).thenReturn(adopter);

        Adopter result = adopterService.addAdopterWithPets(adopter);

        assertNotNull(result);
        assertEquals(adopter.getName(), result.getName());
        assertEquals(adopter.getPets().size(), result.getPets().size());

        verify(adopterRepository).save(any(Adopter.class));
    }

    @Test
    void updateAdopter() {
        Adopter adopter = new Adopter();
        adopter.setId(100);
        adopter.setName("Jex");

        when(adopterRepository.save(any(Adopter.class))).thenReturn(adopter);

        Adopter result = adopterService.updateAdopter(adopter);

        assertNotNull(result);
        assertEquals(result.getId(), adopter.getId());
        assertEquals(result.getName(), adopter.getName());

        verify(adopterRepository).save(any(Adopter.class));

    }

    @Test
    void deleteAdopter() {
        int adopterId = 500;

        doNothing().when(adopterRepository).deleteById(adopterId);

        boolean result = adopterService.deleteAdopter(adopterId);

        assertTrue(result);

        verify(adopterRepository).deleteById(adopterId);
    }
}
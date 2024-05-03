package org.adoption.controllers;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.services.AdopterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockitoAdopterControllerTest {

    @Mock
    private AdopterService service;

    @InjectMocks
    private AdopterController controller;

    @Test
    public void getAll() {
        List<Adopter> adopters = List.of(
                new Adopter(new Pet(Pet.PetType.CAT)),
                new Adopter(new Pet(Pet.PetType.TURTLE)),
                new Adopter(new Pet(Pet.PetType.DOG))
        );

        Mockito.when(service.findAll()).thenReturn(adopters);

        List<Adopter> result = controller.getAdopters();

        assertEquals(adopters, result);

        Mockito.verify(service).findAll();
    }


    @Test
    public void getById() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);

        Mockito.when(service.findByID(adopter.getId())).thenReturn(adopter);

        ResponseEntity<?> response = controller.getAdopter(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Mockito.verify(service).findByID(1);
    }

    @Test
    public void addAdopter() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(service.addAdopter(adopter)).thenReturn(adopter);

        ResponseEntity<?> response = controller.addAdopter(adopter);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Mockito.verify(service).addAdopter(adopter);
    }

    @Test
    public void updateAdopter() {
        Adopter adopter = new Adopter(new Pet(Pet.PetType.CAT));
        adopter.setId(1);

        Mockito.when(service.updateAdopter(adopter)).thenReturn(true);

        ResponseEntity<?> response = controller.updateAdopter(adopter);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Mockito.verify(service).updateAdopter(adopter);
    }

    @Test
    public void deleteAdopter() {
        Adopter adopter = new Adopter();
        adopter.setId(1);

        Mockito.when(service.removeAdopter(1)).thenReturn(true);

        ResponseEntity<?> response = controller.deleteAdopter(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Mockito.verify(service).removeAdopter(1);
    }
}
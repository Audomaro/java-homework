package org.adoption.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.adoption.domain.Pet;
import org.adoption.services.PetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetServiceImpl petService;

    public PetController(PetServiceImpl petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getPets() {
        List<Pet> adopter = petService.getAllPets();
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable int petId) {
        Pet adopter = petService.getPetById(petId);
        return ResponseEntity.ok(adopter);
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet createdPet = petService.addPet(pet);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPet.getId())
                .toUri();

        return ResponseEntity.created(newResource).build();
    }

    @PutMapping
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        Pet updatedAdopter = petService.updatePet(pet);
        return ResponseEntity.ok(updatedAdopter);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer petId) {
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

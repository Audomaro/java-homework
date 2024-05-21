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
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetServiceImpl petService;

    public PetController(PetServiceImpl petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<?> getPets() {
        List<Pet> adopter = petService.findAllPets();
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<?> getPetById(@PathVariable int petId) {
        Optional<Pet> adopter = petService.getPetById(petId);

        if (adopter.isPresent())
        {
            return ResponseEntity.ok(adopter);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getPetsByName(@PathVariable String name) {
        List<Pet> adopter = petService.findPetsByName(name);
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/with-adopter")
    public ResponseEntity<?> getPetsWithAdopter() {
        List<Pet> adopter = petService.findPetsWithAdopter();
        return ResponseEntity.ok(adopter);
    }


    @GetMapping("/without-adopter")
    public ResponseEntity<?> getPetsWithoutAdopter() {
        List<Pet> adopter = petService.findPetsWithoutAdopter();
        return ResponseEntity.ok(adopter);
    }

    @PostMapping
    public ResponseEntity<?> addPet(@RequestBody Pet pet) {
        Pet createdPet = petService.addPet(pet);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPet.getId())
                .toUri();

        return ResponseEntity.created(newResource).build();
    }

    @PutMapping
    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        Pet updatedPet = petService.updatePet(pet);

        if (updatedPet == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No pet with id: " + pet.getId());
        }

        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Integer petId) {
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

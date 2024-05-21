package org.adoption.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.adoption.domain.Adopter;
import org.adoption.services.AdopterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adopter")
public class AdopterController {
    private final AdopterServiceImpl adopterService;

    public AdopterController(AdopterServiceImpl adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping
    public ResponseEntity<?> getAdopters() {
        List<Adopter> adopter = adopterService.findAllAdopters();
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/{adopterId}")
    public ResponseEntity<?> getAdopterById(@PathVariable int adopterId) {
        Optional<Adopter> adopter = adopterService.findAdopterById(adopterId);

        if(adopter.isPresent()){
            return ResponseEntity.ok(adopter);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adopter not found");
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        List<Adopter> adopter = adopterService.findAdopterByName(name);
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("with-pets")
    public ResponseEntity<?> getAdopterWithPets() {
        List<Adopter> adopter = adopterService.findAdopterWithPets();
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("without-pets")
    public ResponseEntity<?> getAdopterWithoutPets() {
        List<Adopter> adopter = adopterService.findAdopterWithoutPets();
        return ResponseEntity.ok(adopter);
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestBody Adopter adopter) {
        Adopter createdAdopter = adopterService.addAdopterWithPets(adopter);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAdopter.getId())
                .toUri();

        return ResponseEntity.created(newResource).build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Adopter adopter) {
        Adopter updatedAdopter = adopterService.updateAdopter(adopter);

        if (updatedAdopter == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No adopter with id: " + adopter.getId());
        }

        return ResponseEntity.ok(updatedAdopter);
    }

    @DeleteMapping("/{adopterId}")
    public ResponseEntity<?> deleteAdopter(@PathVariable Integer adopterId) {
        boolean result = adopterService.deleteAdopter(adopterId);

        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No adopter with id: " + adopterId);
        }
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

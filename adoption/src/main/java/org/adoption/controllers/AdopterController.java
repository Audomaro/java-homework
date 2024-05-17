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

@RestController
@RequestMapping("/adopter")
public class AdopterController {
    private final AdopterServiceImpl adopterService;

    public AdopterController(AdopterServiceImpl adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping
    public ResponseEntity<List<Adopter>> getAdopters() {
        List<Adopter> adopter = adopterService.findAllAdopters();
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/{adopterId}")
    public ResponseEntity<Adopter> getAdopterById(@PathVariable int adopterId) {
        Adopter adopter = adopterService.findAdopterById(adopterId);
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<Adopter>> getByName(@PathVariable String name) {
        List<Adopter> adopter = adopterService.findAdopterByName(name);
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("with-pets")
    public ResponseEntity<List<Adopter>> getAdopterWithPets() {
        List<Adopter> adopter = adopterService.findAdopterWithPets();
        return ResponseEntity.ok(adopter);
    }

    @GetMapping("without-pets")
    public ResponseEntity<List<Adopter>> getAdopterWithoutPets() {
        List<Adopter> adopter = adopterService.findAdopterWithoutPets();
        return ResponseEntity.ok(adopter);
    }

    @PostMapping
    public ResponseEntity<Adopter> addAdopter(@RequestBody Adopter adopter) {
        Adopter createdAdopter = adopterService.addAdopterWithPets(adopter);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAdopter.getId())
                .toUri();

        return ResponseEntity.created(newResource).build();
    }

    @PutMapping("/{adopterId}")
    public ResponseEntity<Adopter> updateAdopter(@PathVariable Integer adopterId, @RequestBody Adopter adopter) {
        Adopter updatedAdopter = adopterService.updateAdopter(adopterId, adopter);
        return ResponseEntity.ok(updatedAdopter);
    }

    @DeleteMapping("/{adopterId}")
    public ResponseEntity<Void> deleteAdopter(@PathVariable Integer adopterId) {
        adopterService.deleteAdopter(adopterId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

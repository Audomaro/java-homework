package org.adoption.controllers;

import org.adoption.domain.Adopter;
import org.adoption.services.AdopterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/adopter")
public class AdopterController {
    private final AdopterService adopterService;

    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping
    public List<Adopter> getAdopters() {
        return this.adopterService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdopter(@PathVariable int id) {
        Adopter adopter = adopterService.findByID(id);

        if (adopter == null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(STR."No adopter with id: \{id}");
        }

        return ResponseEntity.ok(adopter);
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestBody Adopter adopter) {
            Adopter newAdopter = adopterService.addAdopter(adopter);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAdopter.getId())
                .toUri();

        return ResponseEntity.created(newResource).build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Adopter adopter) {
        boolean result = adopterService.updateAdopter(adopter);

        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(STR."No adopter with id: \{adopter.getId()}");
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdopter(@PathVariable int id) {
        boolean result = adopterService.removeAdopter(id);

        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."No adopter with id: \{id}");
        }

        return ResponseEntity.noContent().build();
    }
}

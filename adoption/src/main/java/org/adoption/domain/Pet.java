package org.adoption.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
//@Builder
//@AllArgsConstructor
@Entity
@Table(name = "t_pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private PetType type;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bread", nullable = false)
    private BreedType bread;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;

    @Column(name = "date_adoption")
    private LocalDate dateAdoption;

//    @Override
//    public String toString() {
//        return "Pet{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", type=" + type +
//                ", bread=" + bread +
//                ", adopter=" + (adopter != null ? adopter.getId() : null) +
//                ", dateAdoption=" + dateAdoption +
//                '}';
//    }

    public Pet() {
        Faker faker = new Faker();
        this.setName(faker.funnyName().name());
        this.setType(PetType.random());
        this.setBread(BreedType.random()); // Establece un valor para bread
        this.setDateAdoption(Utils.RandomDate());
    }
}
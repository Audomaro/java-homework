package org.adoption.domain;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
//@Builder
//@AllArgsConstructor
@Entity
@Table(name = "t_adopter")
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adopter_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @OneToMany(mappedBy = "adopter", cascade = CascadeType.ALL)
    private Set<Pet> pets = new HashSet<>();

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Adopter{id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
////
////        if (this.pets != null) {
////            sb.append(", pets=").append(pets);
////        }
//
//        sb.append('}');
//        return sb.toString();
//    }

    public Adopter() {
        Faker faker = new Faker();
        setName(faker.name().fullName());
        setPhoneNumber(faker.phoneNumber().phoneNumber());
        setPets(new HashSet<>());
    }
}
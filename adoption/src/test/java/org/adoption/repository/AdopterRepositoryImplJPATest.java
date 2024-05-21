package org.adoption.repository;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AdopterRepositoryImplJPATest {
    @Autowired
    private AdopterRepository adopterRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() {
//        entityManager.getEntityManager().createNativeQuery("DELETE FROM t_adopter").executeUpdate();
//        entityManager.getEntityManager().createNativeQuery("DELETE FROM t_pet").executeUpdate();
//        entityManager.getEntityManager().createNativeQuery("ALTER TABLE t_adopter ALTER COLUMN adopter_id RESTART WITH 1").executeUpdate();
//        entityManager.getEntityManager().createNativeQuery("ALTER TABLE t_pet ALTER COLUMN pet_id RESTART WITH 1").executeUpdate();

        Adopter adopter1 = new Adopter();
        adopter1.setName("Isiah Deckow");

        Pet pet1 = new Pet();
        pet1.setName("Rover");
        pet1.setAdopter(adopter1);
        adopter1.getPets().add(pet1);

        entityManager.persist(adopter1);
        entityManager.persist(new Adopter());
        entityManager.persist(new Adopter());
        entityManager.persist(new Adopter());

        entityManager.flush();
    }

    @Test
    void findAllAdopters() {
        List<Adopter> result = adopterRepository.findAll();
        assertNotNull(result);
    }

    @Test
    void findAdopterById() {
        Optional<Adopter> adopter = adopterRepository.findById(1);
        assertNotNull(adopter);
        assertTrue(adopter.isPresent());
        assertEquals("Isiah Deckow", adopter.get().getName());
    }

    @Test
    void findAdopterByName() {
        List<Adopter> adopters = adopterRepository.findAdopterByName("ck");
        assertNotNull(adopters);
        assertFalse(adopters.isEmpty());
    }

    @Test
    void findAdopterWithoutPets() {
        List<Adopter> result = adopterRepository.findAdopterWithoutPets();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3,result.size());
    }

    @Test
    void findAdopterWithPets() {
        List<Adopter> result = adopterRepository.findAdopterWithPets();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
    }

    @Test
    void addAdopterWithPets() {
        Adopter newAdopter = new Adopter();

        List<Pet> pets = List.of(
                new Pet(),
                new Pet()
        );

        newAdopter.getPets().addAll(pets);
        adopterRepository.save(newAdopter);
    }

    @Test
    void updateAdopter() {
        Adopter adopter = new Adopter();
        adopter.setId(1);
        adopter.setName("Isiah Deckow Modif");
        Adopter result = adopterRepository.save(adopter);
        assertNotNull(result);
        assertEquals("Isiah Deckow Modif", result.getName());
    }

    @Test
    void deleteAdopter() {
        adopterRepository.deleteById(3);
        Optional<Adopter> result = adopterRepository.findById(9999);
        assertTrue(result.isEmpty());
    }
}
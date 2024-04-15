package org.adoption;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.services.AdopterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AdoptionApplication.class})
@SpringBootTest
@ActiveProfiles({"dev"})
class AdoptionApplicationTests {

	@Autowired
	private AdopterService adopterService;

	@BeforeEach
	void setUp() {
		adopterService.addAdopter(new Adopter(
				1,
				"Celia A",
				"123-456-7890",
				LocalDate.of(2018,4,4),
				new Pet(1,"Michi", Pet.PetType.CAT, Pet.BreedType.British_Shorthair)
		));

		adopterService.addAdopter(new Adopter(
				2,
				"Celia B",
				"124-456-7890",
				LocalDate.of(2021,3,4),
				new Pet(1,"Michi", Pet.PetType.TURTLE, Pet.BreedType.British_Shorthair)
		));

		adopterService.addAdopter(new Adopter(
				3,
				"Mary / ADOPTER RANDOM ID",
				"223-456-7890",
				LocalDate.of(2024,1,1),
				new Pet(2,"Bob", Pet.PetType.DOG, Pet.BreedType.Buldog)
		));

		adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.CAT)));
		adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.TURTLE)));
		adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.DOG)));
		adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.CAT)));
		adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.TURTLE)));
		adopterService.addAdopter(new Adopter(new Pet(Pet.PetType.DOG)));
	}

	@Test
	public void testAddAdopter() {
		Adopter alice = new Adopter("NEW ALICE", "123-234-456", LocalDate.of(2020, 5, 10), new Pet(Pet.PetType.DOG));
		adopterService.addAdopter(alice);
		List<Adopter> allAdopters = adopterService.findAll();
		assertTrue(allAdopters.contains(alice));
	}

	@Test
	public void testRemoveAdopter() {
		Adopter adopterToRemove = adopterService.findByID(1);
		boolean removed = adopterService.removeAdopter(adopterToRemove);
		assertTrue(removed);
	}

	@Test
	public void testUpdateAdopter() {
		Adopter old = adopterService.findByID(1);
		old.setPet(new Pet(Pet.PetType.CAT));

		boolean updated = adopterService.updateAdopter(old);
		assertTrue(updated);
	}

	@Test
	public void testFindByName() {
		List<Adopter> aliceAdopters = adopterService.findByName("Celia");
		assertEquals(2, aliceAdopters.size());
	}

	@Test
	public void testSortByAdoptionDate() {
		List<Adopter> sortedAdopters = adopterService.sortByAdoptionDate();
		assertEquals("Celia A", sortedAdopters.get(0).getName());
	}

	@Test
	public void testFindByID() {
		//AdopterServiceImpl adopterService = new AdopterServiceImpl();
		Adopter alice = new Adopter("Alice", "654-65651-3515", LocalDate.of(2023, 5, 10), new Pet(Pet.PetType.CAT));
		adopterService.addAdopter(alice);

		Adopter foundAdopter = adopterService.findByID(alice.getId());
		assertEquals(alice, foundAdopter);
	}

	@Test
	public void testFindByCriteria() {
		Predicate<Adopter> whereee = adopter -> adopter.getName().startsWith("Celi");
		List<Adopter> res = adopterService.findBy(whereee);
		assertEquals(2, res.size());
	}

	@Test
	public void testOrderByCriteria() {
		Comparator<Adopter> whereee = Comparator.comparing(Adopter::getId);
		List<Adopter> res = adopterService.orderBy(whereee);

		assertEquals(1, res.getFirst().getId());
	}



}

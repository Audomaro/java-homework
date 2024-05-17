package org.adoption;

import org.adoption.services.AdopterService;
import org.adoption.services.PetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdoptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionApplication.class, args);
	}
}

//@Component
class MyRunner implements CommandLineRunner {
	private final AdopterService adopterService;
	private final PetService petService;

	public MyRunner(AdopterService adopterService, PetService petService) {
		this.adopterService = adopterService;
		this.petService = petService;
	}

	@Override
	public void run(String... args) {
//		Adopter newAdopter = new Adopter();
//		newAdopter.setName("TEST");
//		newAdopter.setPhoneNumber("TEST");
//
//		Pet newPet = new Pet();
//		newPet.setName("TEST");
//		newPet.setType(PetType.TURTLE);
//		newPet.setBread(BreedType.Chihuahua);
//		newPet.setDateAdoption(Utils.RandomDate());
//		newPet.setAdopter(newAdopter);
//
//		newAdopter.getPets().add(newPet);

//		System.out.println(":::::::::::::::::::: Add Adopters with Pets");
//
//		for(int i = 0; i < 10 ; i++) {
//			Adopter newAdopter = new Adopter();
//
//			Random rnd = new Random();
//
//			int top = rnd.nextInt(1, 5);
//
//			for (int p = 0; p < top; p++) {
//				Pet newPet = new Pet();
//				newPet.setAdopter(newAdopter);
//				newAdopter.getPets().add(newPet);
//			}
//
//			adopterService.addAdopterWithPets(newAdopter);
//		}

//		System.out.println(":::::::::::::::::::: Add Adopter with Pets");
//		for (int p = 0; p < 2; p++) {
//			newAdopter.getPets().add(new Pet());
//		}
//

//		System.out.println(":::::::::::::::::::: Get Adopters by name");
//		adopterService.findByName("Kaye").forEach(System.out::println);
//		System.out.println();
//
//		System.out.println(":::::::::::::::::::: Get Adopters Without Pets");
//		adopterService.findAdopterWithoutPets().forEach(System.out::println);
//		System.out.println();
//
//		System.out.println(":::::::::::::::::::: Get Adopters With Pets");
//		adopterService.findAdopterWithPets().forEach(System.out::println);
//		System.out.println();

		System.out.println(":::::::::::::::::::: Find Adopters By ID");
		adopterService.findAllAdopters().forEach(System.out::println);

		System.out.println();
	}
}

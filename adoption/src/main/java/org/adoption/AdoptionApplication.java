package org.adoption;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.adoption.services.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@SpringBootApplication
public class AdoptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionApplication.class, args);
	}
}

@Component
class MyRunner implements CommandLineRunner {
	final
	AdopterService adopterService;

	public MyRunner(AdopterService adopterService) {
		this.adopterService = adopterService;
	}

	@Override
	public void run(String... args) {
		System.out.println("run from ***CommandLineRunner**");
		adopterService.addAdopter(new Adopter(new Pet()));
		adopterService.addAdopter(new Adopter(new Pet()));
		adopterService.addAdopter(new Adopter(new Pet()));

		System.out.println("ORDER BY PhoneNumber");
		Comparator<Adopter> orderBy = Comparator.comparing(Adopter::getPhoneNumber);
		adopterService.orderBy(orderBy).forEach(System.out::println);
	}
}

package org.adoption.db;

import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;

public abstract class JdbcAdoption {
    protected JdbcTemplate getTemplate() {
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String password = "larku";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, password);
        return new JdbcTemplate(dataSource);
    }

    protected RowMapper<Adopter> getAdopterMapper() {
        return  (resultSet, _) -> {
            // PET ROW MAPPER
            int petid = resultSet.getInt("pet_id");
            String name = resultSet.getString("pet_name");

            int typeValue = resultSet.getInt("pet_type");
            Pet.PetType type = Pet.PetType.values()[typeValue];

            int breedValue = resultSet.getInt("pet_breed");
            Pet.BreedType breed = Pet.BreedType.values()[breedValue];

            Pet pet = new Pet();
            pet.setPetId(petid);
            pet.setName(name);
            pet.setType(type);
            pet.setBreed(breed);

            // ADOPTER ROW MAPPER
            int adopterId = resultSet.getInt("adopter_id");
            String adopterName = resultSet.getString("adopter_name");
            String phoneNumber = resultSet.getString("phone_number");
            LocalDate adoptionDate = LocalDate.parse(resultSet.getDate("adoptiondate").toString());

            Adopter adopter = new Adopter();
            adopter.setId(adopterId);
            adopter.setName(adopterName);
            adopter.setPhoneNumber(phoneNumber);
            adopter.setPet(pet);
            adopter.setAdoptionDate(adoptionDate);

            return adopter;
        };
    }
}

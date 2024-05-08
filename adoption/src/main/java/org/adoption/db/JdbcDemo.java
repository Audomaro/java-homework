//package org.adoption.db;
//
//import org.adoption.domain.Adopter;
//import org.adoption.domain.Pet;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.lang.System.out;
//
//public class JdbcDemo {
//    public static void main(String[] args) {
//        String url = "jdbc:postgresql://localhost:5433/adoptapp";
//        String user = "larku";
//        String password = "larku";
//
////        try {
////            Connection connectionection = DriverManager.getConnection(url, user, password);
////            addPet(connectionection);
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, password);
//        JdbcTemplate template = new JdbcTemplate(dataSource);
//        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//
//        JdbcDemo jdbDemo = new JdbcDemo();
//
//        out.println("Pet list:");
//        jdbDemo.getPets(template);
//
//        out.println("Adopter list with your pet:");
//        jdbDemo.getAdopterWithPets(template);
//    }
//
//    public static void addPet(Connection connection) {
//        String sql = "insert into pet (name, type, breed) values (?, ?, ?)";
//
//        List<Integer> newKeys = new ArrayList<>();
//        int rowsAffected = 0;
//
//        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            ps.setString(1, "Harry Potter");
//            ps.setInt(2, 1);
//            ps.setInt(3, 2);
//
//            rowsAffected= ps.executeUpdate();
//
//            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//                generatedKeys.next();
//                int id = generatedKeys.getInt(1);
//                newKeys.add(id);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        out.println("numRows: " + rowsAffected + ", newKeys: " + newKeys);
//    }
//
//    public  void getPets(JdbcTemplate template ) {
//        String sql = "select * from pet";
//
//        RowMapper<Pet> rowMapper = (resultSet, rowNum) -> {
//            int petid = resultSet.getInt("petid");
//
//            String name = resultSet.getString("name");
//
//            int typeValue = resultSet.getInt("type");
//            Pet.PetType type = Pet.PetType.values()[typeValue];
//
//            int breedValue = resultSet.getInt("breed");
//            Pet.BreedType breed = Pet.BreedType.values()[breedValue];
//
//            Pet pet = new Pet();
//            pet.setPetId(petid);
//            pet.setName(name);
//            pet.setType(type);
//            pet.setBreed(breed);
//
//            return pet;
//        };
//
//        List<Pet> pets = template.query(sql, rowMapper);
//
//        pets.forEach(out::println);
//    }
//
//    public  void getAdopterWithPets(JdbcTemplate template) {
//
//        String sql = """
//                SELECT
//                    a.id As adopter_id,
//                    a.name AS adopter_name,
//                    a.phonenumber as phone_number,
//                    a.adoptiondate,
//                    p.petid as pet_id,
//                    p.name AS pet_name,
//                    p.type AS pet_type,
//                    p.breed AS pet_breed
//                FROM adopter a
//                JOIN pet p ON a.petid = p.petid
//        """;
//
//        RowMapper<Adopter> adopterRowMapper = (resultSet, rowNum) -> {
//            // PET ROW MAPPER
//            int petid = resultSet.getInt("pet_id");
//            String name = resultSet.getString("pet_name");
//
//            int typeValue = resultSet.getInt("pet_type");
//            Pet.PetType type = Pet.PetType.values()[typeValue];
//
//            int breedValue = resultSet.getInt("pet_breed");
//            Pet.BreedType breed = Pet.BreedType.values()[breedValue];
//
//            Pet pet = new Pet();
//            pet.setPetId(petid);
//            pet.setName(name);
//            pet.setType(type);
//            pet.setBreed(breed);
//
//            // ADOPTER ROW MAPPER
//            int adopterId = resultSet.getInt("adopter_id");
//            String adopterName = resultSet.getString("adopter_name");
//            String phoneNumber = resultSet.getString("phone_number");
//            LocalDate adoptionDate = LocalDate.parse(resultSet.getDate("adoptiondate").toString());
//
//            Adopter adopter = new Adopter();
//            adopter.setId(adopterId);
//            adopter.setName(adopterName);
//            adopter.setPhoneNumber(phoneNumber);
//            adopter.setPet(pet);
//            adopter.setAdoptionDate(adoptionDate);
//
//            return adopter;
//        };
//
//        List<Adopter> adopters = template.query(sql, adopterRowMapper);
//
//        adopters.forEach(out::println);
//
//    }
//}
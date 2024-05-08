package org.adoption.dao;

import org.adoption.db.JdbcAdoption;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class JdbcTemplateDAO extends JdbcAdoption implements AdopterDAO {

    @Override
    public Adopter insert(Adopter newAdopter) {
        Pet pet = insertPet(newAdopter.getPet());

        String sql = "INSERT INTO adopter (name, phonenumber, adoptiondate, petid) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, newAdopter.getName());
            ps.setString(2, newAdopter.getPhoneNumber());
            ps.setObject(3, newAdopter.getAdoptionDate());
            ps.setInt(4, pet.getPetId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            newAdopter.setId(keyHolder.getKey().intValue());
        }

        return newAdopter;
    }

    private Pet insertPet(Pet pet) {
        String sql = "INSERT INTO pet (name, type, breed) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"petid"});
            ps.setString(1, pet.getName());
            ps.setInt(2, pet.getType().ordinal());
            ps.setInt(3, pet.getBreed().ordinal());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            pet.setPetId(keyHolder.getKey().intValue());
        }

        return pet;
    }

    @Override
    public Adopter findByID(int id) {
        String sql = """
                SELECT
                    a.id As adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber as phone_number,
                    a.adoptiondate,
                    p.petid as pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter a
                JOIN pet p ON a.petid = p.petid
                WHERE a.id = ?
        """;

        return getTemplate().query(sql, ps -> ps.setInt(1, id), getAdopterMapper()).getFirst();
    }

    @Override
    public boolean update(Adopter updateAdopter) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Adopter> findAll() {
        String sql = """
                SELECT
                    a.id As adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber as phone_number,
                    a.adoptiondate,
                    p.petid as pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter a
                JOIN pet p ON a.petid = p.petid
        """;

        return getTemplate().query(sql, getAdopterMapper());
    }

    @Override
    public List<Adopter> findByName(String name) {
        String sql = """
                SELECT
                    a.id As adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber as phone_number,
                    a.adoptiondate,
                    p.petid as pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter a
                JOIN pet p ON a.petid = p.petid
                WHERE a.name LIKE ?
        """;

        String like = "%" + name + "%";

        return getTemplate().query(sql, ps -> ps.setString(1, like), getAdopterMapper());
    }

    @Override
    public List<Adopter> sortByAdoptionDate() {
        String sql = """
                SELECT
                    a.id As adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber as phone_number,
                    a.adoptiondate,
                    p.petid as pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter a
                JOIN pet p ON a.petid = p.petid
                ORDER BY a.adoptiondate
        """;

        return getTemplate().query(sql, getAdopterMapper());
    }

    @Override
    public List<Adopter> findBy(Predicate<Adopter> criteria) {
        return List.of();
    }

    @Override
    public List<Adopter> orderBy(Comparator<Adopter> comparator) {
        return List.of();
    }
}

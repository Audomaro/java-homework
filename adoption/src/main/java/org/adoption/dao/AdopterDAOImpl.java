package org.adoption.dao;

import org.adoption.db.JdbcAdoption;
import org.adoption.domain.Adopter;
import org.adoption.domain.Pet;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AdopterDAOImpl extends JdbcAdoption implements AdopterDAO {

    @Override
    public Adopter insert(Adopter newAdopter) {
        Pet pet = insertPet(newAdopter.getPet());

        String sql = "INSERT INTO adopter (name, phonenumber, adoptiondate, petid) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, newAdopter.getName());
            ps.setString(2, newAdopter.getPhoneNumber());
            ps.setDate(3, Date.valueOf(newAdopter.getAdoptionDate()));
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
            ps.setInt(2, pet.getType().getValue());
            ps.setInt(3, pet.getBreed().getValue());
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
                    a.id AS adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber AS phone_number,
                    a.adoptiondate,
                    p.petid AS pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter AS a
                JOIN pet AS p ON a.petid = p.petid
                WHERE a.id = ?
        """;

        try {
            return getTemplate().queryForObject(sql, getAdopterMapper(true), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean update(Adopter updateAdopter) {
        String sql = "UPDATE adopter SET name = ?, phonenumber = ?, adoptiondate = ? WHERE id = ?";

        try {
            int rowsUpdated = getTemplate().update(
                    sql,
                    updateAdopter.getName(),
                    updateAdopter.getPhoneNumber(),
                    updateAdopter.getAdoptionDate(),
                    updateAdopter.getId()
            );

            return rowsUpdated > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM adopter WHERE id = ?";

        try {
            int rowsDeleted = getTemplate().update(sql, id);
            return rowsDeleted > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Adopter> findAll() {
//        String sql = """
//                SELECT
//                    a.id AS adopter_id,
//                    a.name AS adopter_name,
//                    a.phonenumber AS phone_number,
//                    a.adoptiondate,
//                    p.petid AS pet_id,
//                    p.name AS pet_name,
//                    p.type AS pet_type,
//                    p.breed AS pet_breed
//                FROM adopter AS a
//                JOIN pet p ON a.petid = p.petid
//                ORDER BY a.id
//        """;
        String sql = """
                SELECT
                    a.id AS adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber AS phone_number,
                    a.adoptiondate,
                    a.petid AS pet_id
                FROM adopter AS a
                ORDER BY a.id
        """;

        try {
            return getTemplate().query(sql, getAdopterMapper(false));
        } catch (DataAccessException e) {
            return List.of();
        }
    }

    @Override
    public List<Adopter> findByName(String name) {
        String sql = """
                SELECT
                    a.id As adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber AS phone_number,
                    a.adoptiondate,
                    p.petid AS pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter AS a
                JOIN pet AS p ON a.petid = p.petid
                WHERE a.name LIKE ?
                ORDER BY a.id
        """;

        String like = "%" + name + "%";

        try {
            return getTemplate().query(sql, ps -> ps.setString(1, like), getAdopterMapper(true));
        } catch (DataAccessException e) {
            return List.of();
        }
    }

    @Override
    public List<Adopter> sortByAdoptionDate() {
        String sql = """
                SELECT
                    a.id As adopter_id,
                    a.name AS adopter_name,
                    a.phonenumber AS phone_number,
                    a.adoptiondate,
                    p.petid AS pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM adopter AS a
                JOIN pet p ON a.petid = p.petid
                ORDER BY a.AdopterDAOImpl
        """;

        try {
            return getTemplate().query(sql, getAdopterMapper(true));
        } catch (DataAccessException e) {
            return List.of();
        }
    }

    @Override
    public List<Adopter> findBy(Predicate<Adopter> criteria) {
        List<Adopter> allAdopters = findAll();

        List<Adopter> filteredAdopters = new ArrayList<>();

        for (Adopter adopter : allAdopters) {
            if (criteria.test(adopter)) {
                filteredAdopters.add(adopter);
            }
        }

        return filteredAdopters;
    }

    @Override
    public List<Adopter> orderBy(Comparator<Adopter> comparator) {
        List<Adopter> adopters = findAll();

        adopters.sort(comparator);

        return adopters;
    }
}

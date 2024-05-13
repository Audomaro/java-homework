package org.adoption.dao;

import org.adoption.db.JdbcAdoption;
import org.adoption.domain.Pet;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class PetDAOImpl extends JdbcAdoption implements PetDAO {

    @Override
    public Pet insert(Pet newPet) {
        String sql = "INSERT INTO pet (name, type, breed) VALUES (?, ?, ?)";
        try {
            getTemplate().update(
                    sql,
                    newPet.getName(),
                    newPet.getType().getValue(),
                    newPet.getBreed().getValue()
            );

            return newPet;
        } catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Pet findByID(int id) {
        String sql = """
                SELECT
                    p.petid AS pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM pet AS p
                WHERE p.petid = ?
            """;
        try {
            return getTemplate().queryForObject(sql, getPetMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean update(Pet updatePet) {
        String sql = "UPDATE pet SET name = ?, type = ?, breed = ? WHERE petId = ?";

        try {
            int rowsUpdated = getTemplate().update(
                    sql, updatePet.getName(),
                    updatePet.getType().getValue(),
                    updatePet.getBreed().getValue(),
                    updatePet.getPetId()
            );

            return rowsUpdated > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM pet WHERE petId = ?";
        try {
            int rowsDeleted = getTemplate().update(sql, id);
            return rowsDeleted > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Pet> findAll() {
        String sql = """
                SELECT
                    p.petid AS pet_id,
                    p.name AS pet_name,
                    p.type AS pet_type,
                    p.breed AS pet_breed
                FROM pet AS p
                ORDER BY p.petid
            """;
        try {
            return getTemplate().query(sql, getPetMapper());
        } catch (DataAccessException e) {
            return List.of();
        }
    }
}
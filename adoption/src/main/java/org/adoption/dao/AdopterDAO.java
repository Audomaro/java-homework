package org.adoption.dao;

import org.adoption.domain.Adopter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface AdopterDAO {
    Adopter insert(Adopter newAdopter);

    Adopter findByID(int id);

    boolean update(Adopter updateAdopter);

    boolean delete(int id);

    List<Adopter> findAll();

    List<Adopter> findByName(String name);

    List<Adopter> sortByAdoptionDate();

    List<Adopter> findBy(Predicate<Adopter> criteria);

    List<Adopter> orderBy(Comparator<Adopter> comparator);
}

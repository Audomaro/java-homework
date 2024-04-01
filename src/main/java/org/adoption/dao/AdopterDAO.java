package org.adoption.dao;

import org.adoption.domain.Adopter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface AdopterDAO {
    Adopter insert(Adopter student);

    Adopter findByID(int id);

    boolean update(Adopter updatedStudent);

    boolean delete(int id);

    List<Adopter> findAll();

    public List<Adopter> findByName(String name);

    public List<Adopter> sortByAdoptionDate();

    public List<Adopter> findBy(Predicate<Adopter> criteria);

    public List<Adopter> orderBy(Comparator<Adopter> comparator);
}

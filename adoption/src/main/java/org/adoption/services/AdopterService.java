package org.adoption.services;

import org.adoption.dao.AdopterDAO;
import org.adoption.domain.Adopter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public interface AdopterService {
    Adopter addAdopter(Adopter adopter);

    boolean removeAdopter(int id);

    boolean updateAdopter(Adopter newAdopter);

    List<Adopter> findAll();

    Adopter findByID(int id);

    List<Adopter> findByName(String name);

    List<Adopter> sortByAdoptionDate();

    List<Adopter> findBy(Predicate<Adopter> criteria);

    List<Adopter> orderBy(Comparator<Adopter> comparator);
}

package org.adoption.services;
import org.adoption.dao.AdopterDAO;
import org.adoption.dao.FactoryDAO;
import org.adoption.domain.Adopter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AdopterService {

    private AdopterDAO adopters;

    public AdopterService() {
        adopters = FactoryDAO.adopterDAO();
    }

    public Adopter addAdopter(Adopter adopter) {
        adopters.insert(adopter);
        return adopter;
    }

    public boolean removeAdopter(Adopter adopter) {
        return adopters.delete(adopter.getId());
    }

    public boolean updateAdopter(Adopter newAdopter) {

        return adopters.update(newAdopter);
    }

    public List<Adopter> findAll() {
        return adopters.findAll();
    }

    public Adopter findByID(int id) {
        return adopters.findByID(id);
    }

    public List<Adopter> findByName(String name) {
        return adopters.findByName(name);
    }

    public List<Adopter> sortByAdoptionDate() {
        return adopters.sortByAdoptionDate();
    }

    public List<Adopter> findBy(Predicate<Adopter> criteria) {
        return adopters.findBy(criteria);
    }

    public List<Adopter> orderBy(Comparator<Adopter> comparator) {
        return adopters.orderBy(comparator);
    }

    public void setDAO(AdopterDAO dao) {
        adopters = dao;
    }
}

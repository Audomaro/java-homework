package org.adoption.dao;

import org.adoption.domain.Adopter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class InMemoryDAO implements AdopterDAO {
    private static Map<Integer, Adopter> adopters;

    public InMemoryDAO() {
        System.out.println("DAO DE DEV");
        adopters = new ConcurrentHashMap<>();
    }

    @Override
    public Adopter insert(Adopter newAdopter) {
        adopters.put(newAdopter.getId(), newAdopter);
        return newAdopter;
    }

    @Override
    public Adopter findByID(int id) {
        return adopters.get(id);
    }

    @Override
    public boolean update(Adopter updateAdopter) {
        return adopters.replace(updateAdopter.getId(), updateAdopter) != null;
    }

    @Override
    public boolean delete(int id) {
        return  adopters.remove(id) != null;
    }

    @Override
    public List<Adopter> findAll() {
        return new ArrayList<>(adopters.values());
    }

    public List<Adopter> findByName(String name) {
        return adopters
                .values()
                .stream()
                .filter(adopter -> adopter.getName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Adopter> sortByAdoptionDate() {
        return adopters
                .values()
                .stream()
                .sorted(Comparator.comparing(Adopter::getAdoptionDate))
                .collect(Collectors.toList());
    }

    public List<Adopter> findBy(Predicate<Adopter> criteria) {
        return adopters
                .values()
                .stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    public List<Adopter> orderBy(Comparator<Adopter> comparator) {
        return adopters.values()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

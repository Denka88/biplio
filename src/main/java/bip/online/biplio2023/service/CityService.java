package bip.online.biplio2023.service;

import bip.online.biplio2023.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> findAllCities();

    Optional<City> findById(Long id);

    City save(City data);

    void update(City data);

    void delete(City data);
}

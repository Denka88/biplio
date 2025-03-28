package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.repo.CityRepo;
import bip.online.biplio2023.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepo cityRepo;

    public CityServiceImpl(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @Override
    public List<City> findAllCities() {
        return cityRepo.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepo.findById(id);
    }

    @Override
    public City save(City data) {
        return cityRepo.save(data);
    }

    @Override
    public void update(City data) {
        cityRepo.save(data);
    }

    @Override
    public void deleteById(Long id) {
        cityRepo.deleteById(id);
    }


}

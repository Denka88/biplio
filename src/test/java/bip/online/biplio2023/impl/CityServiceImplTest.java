package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.repo.CityRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepo cityRepo;
    
    @InjectMocks
    private CityServiceImpl cityService;

    /**
     * Метод для тестирования поиска всех городов
     * @param title - Название городов
     */
    @ParameterizedTest
    @CsvSource({
            "Омск",
            "Москва",
            "Краснодар",
            "Белореченск",
            "Лондон"
    })
    void findAllCities(String title) {
        City city1 = new City(1L, title);
        City city2 = new City(2L, title);

        List<City> cities = Arrays.asList(city1, city2);
        
        when(cityRepo.findAll()).thenReturn(cities);
        
        List<City> result = cityService.findAllCities();
        
        assertEquals(result, cities);
    }

    /**
     * Метод для тестирования поиска города по id
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource({
            "1, Омск",
            "2, Москва",
            "3, Краснодар",
            "4, Белореченск",
            "5, Лондон"
    })
    void findById(Long id, String title) {
        City city = new City(id, title);
        
        when(cityRepo.findById(id)).thenReturn(Optional.of(city));
        
        Optional<City> result = cityService.findById(id);
        
        assertTrue(result.isPresent());
        assertEquals(result.get(), city);
        verify(cityRepo, times(1)).findById(id);
    }

    /**
     * Метод для тестирования сохранение города в базу
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource({
            "1, Омск",
            "2, Москва",
            "3, Краснодар",
            "4, Белореченск",
            "5, Лондон"
    })
    void save(Long id, String title) {
        City city = new City(id, title);
        
        when(cityRepo.save(city)).thenReturn(city);
        
        City result = cityService.save(city);
        
        assertEquals(result, city);        
    }

    /**
     * Метод для тестирования обновления города в базе
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource({
            "Омск",
            "Москва",
            "Краснодар",
            "Белореченск",
            "Лондон"
    })
    void update(String title) {
        City city = new City(1L, title);
        
        cityService.update(city);
        
        verify(cityRepo, times(1)).save(city);
    }

    /**
     * Метод для тестирования удаления города из базы
     * @param id - идентификатор города
     * @param title - название города
     */
    @ParameterizedTest
    @CsvSource({
            "1, Омск",
            "2, Москва",
            "3, Краснодар",
            "4, Белореченск",
            "5, Лондон"
    })
    void deleteById(Long id, String title) {
        City city = new City(id, title);
        
        cityService.save(city);
        
        cityService.deleteById(id);
        
        assertFalse(cityRepo.existsById(id));
    }
}
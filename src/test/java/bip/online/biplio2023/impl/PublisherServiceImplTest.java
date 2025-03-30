package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.City;
import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.repo.PublisherRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class PublisherServiceImplTest {

    @Mock
    private PublisherRepo publisherRepo;
    
    @InjectMocks
    private PublisherServiceImpl publisherService;

    /**
     * Метод для тестирования поиска всех издателей
     * @param title - название издателя
     * @param city - город издателя
     */
    @ParameterizedTest
    @CsvSource({
            "Издатель№1, Омск",
            "Издатель№151, Волгоград",
            "Издатель№524, Константинополь",
            "Издатель№14, Санкт-Петербург",
            "Издатель№52, Москва"
    })
    void findAllPublishers(String title, String city) {
        Publisher publisher1 = new Publisher(1L, title, new City(1L, city));
        Publisher publisher2 = new Publisher(1L, title, new City(1L, city));
        
        List<Publisher> publishers = Arrays.asList(publisher1, publisher2);
        
        when(publisherRepo.findAll()).thenReturn(publishers);
        
        List<Publisher> result = publisherService.findAllPublishers();
        
        assertEquals(publishers, result);
    }

    /**
     * Метод для тестирования поиска издателя по id
     * @param id - идентификатор издателя
     * @param title - название издателя
     * @param city - город издателя
     */
    @ParameterizedTest
    @CsvSource({
            "1, Издатель№1, Омск",
            "2, Издатель№151, Волгоград",
            "3, Издатель№524, Константинополь",
            "4, Издатель№14, Санкт-Петербург",
            "5, Издатель№52, Москва"
    })
    void findById(Long id, String title, String city) {
        Publisher publisher = new Publisher(id, title, new City(1L, city));
        
        when(publisherRepo.findById(id)).thenReturn(Optional.of(publisher));
        
        Optional<Publisher> result = publisherService.findById(id);
        
        assertTrue(result.isPresent());
        assertEquals(publisher, result.get());
        verify(publisherRepo, times(1)).findById(id);
    }

    /**
     * Метод для тестирования сохранения издателя в базу
     * @param id - идентификатор издателя
     * @param title - название издателя 
     * @param city - город издателя
     */
    @ParameterizedTest
    @CsvSource({
            "1, Издатель№1, Омск",
            "2, Издатель№151, Волгоград",
            "3, Издатель№524, Константинополь",
            "4, Издатель№14, Санкт-Петербург",
            "5, Издатель№52, Москва"
    })
    void save(Long id, String title, String city) {
        Publisher publisher = new Publisher(id, title, new City(1L, city));
        
        when(publisherRepo.save(publisher)).thenReturn(publisher);
        
        Publisher result = publisherService.save(publisher);
        
        assertEquals(publisher, result);
    }

    /**
     * Метод для тестирования обновления автора в базе
     * @param title - название издателя
     * @param city - город издателя
     */
    @ParameterizedTest
    @CsvSource({
            "Издатель№1, Омск",
            "Издатель№151, Волгоград",
            "Издатель№524, Константинополь",
            "Издатель№14, Санкт-Петербург",
            "Издатель№52, Москва"
    })
    void update(String title, String city) {
        Publisher publisher = new Publisher(1L, title, new City(1L, city));
        
        publisherService.update(publisher);
        
        verify(publisherRepo, times(1)).save(publisher);
    }

    /**
     * Метод для тестирования удаления издателя из базы
     * @param id - идентификатор издателя
     * @param title - название издателя
     * @param city - город издателя
     */
    @ParameterizedTest
    @CsvSource({
            "1, Издатель№1, Омск",
            "2, Издатель№151, Волгоград",
            "3, Издатель№524, Константинополь",
            "4, Издатель№14, Санкт-Петербург",
            "5, Издатель№52, Москва"
    })
    void deleteById(Long id, String title, String city) {
        Publisher publisher = new Publisher(id, title, new City(1L, city));
        
        publisherService.save(publisher);
        
        publisherService.deleteById(id);
        
        assertFalse(publisherRepo.existsById(id));
    }
}
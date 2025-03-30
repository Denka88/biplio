package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.repo.GenreRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepo genreRepo;

    @InjectMocks
    private GenreServiceImpl genreService;

    /**
     * Метод для тестирования поиска всех жанров
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource({
            "Романтика",
            "Ужас",
            "Детектив",
            "Фантастика",
            "Сказки"
    })
    void findAllGenres(String title) {
        Genre genre1 = new Genre(1L, title);
        Genre genre2 = new Genre(1L, title);
        
        List<Genre> genreList = Arrays.asList(genre1, genre2);
        
        when(genreRepo.findAll()).thenReturn(genreList);
        
        List<Genre> result = genreService.findAllGenres();
        
        assertEquals(genreList, result);
    }

    /**
     * Метод для тестирования поиска жанра по id
     * @param id - идентификатор жанра
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource({
            "1, Романтика",
            "2, Ужас",
            "3, Детектив",
            "4, Фантастика",
            "5, Сказки"
    })
    void findById(Long id, String title) {
        Genre genre = new Genre(id, title);
        
        when(genreRepo.findById(id)).thenReturn(Optional.of(genre));
        
        Optional<Genre> result = genreService.findById(id);
        
        assertTrue(result.isPresent());
        assertEquals(genre, result.get());
        verify(genreRepo, times(1)).findById(id);
    }

    /**
     * Метод для тестирования сохранения жанра в базу
     * @param id - идентификатор жанра
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource({
            "1, Романтика",
            "2, Ужас",
            "3, Детектив",
            "4, Фантастика",
            "5, Сказки"
    })
    void save(Long id, String title) {
        Genre genre = new Genre(id, title);
        
        when(genreRepo.save(genre)).thenReturn(genre);
        
        Genre result = genreService.save(genre);
        
        assertEquals(genre, result);
    }

    /**
     * Метод для тестирования обновления жанра в базе
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource({
            "Романтика",
            "Ужас",
            "Детектив",
            "Фантастика",
            "Сказки"
    })
    void update(String title) {
        Genre genre = new Genre(1L, title);
        
        genreService.update(genre);
        
        verify(genreRepo, times(1)).save(genre);
    }

    /**
     * Метод для тестирования удаления жанра из базы
     * @param id - идентификатор жанра
     * @param title - название жанра
     */
    @ParameterizedTest
    @CsvSource({
            "1, Романтика",
            "2, Ужас",
            "3, Детектив",
            "4, Фантастика",
            "5, Сказки"
    })
    void deleteById(Long id, String title) {
        Genre genre = new Genre(id, title);
        
        genreService.save(genre);
        
        genreService.deleteById(id);
        
        verify(genreRepo, times(1)).deleteById(id);
    }
}
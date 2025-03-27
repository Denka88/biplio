package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.repo.AuthorRepo;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepo authorRepo;

    @InjectMocks
    private AuthorServiceImpl authorService;
    
    /**
     * Метод для тестирования поиска всех авторов
     * @param lastName1 - Отчество автора 1
     * @param name1 - Имя автора 1
     * @param surname1 - Фамилия автора 1
     * @param lastName2 - Отчество автора 2
     * @param name2 - Имя автора 2
     * @param surname2 - Фамилия автора 2
     */
    @ParameterizedTest
    @CsvSource({
            "Иванович, Иван, Иванов, Петрович, Иван, Иванов",
            "Владленович, Иван, Иванов, Головач, Лена, Иванов",
            "Алексеевич, Ибрагим, Кандибобер, Родионович, Глеб, Степанов",
            "Емельянов, Сергей, Александрович, Лукич, Игорь, Демидов",
            "Матвеев, Мирон, Александрович, Павлов, Артур, Артемович"
    })
    void findAllAuthors(String lastName1, String name1, String surname1, String lastName2, String name2, String surname2) {
        Author author1 = new Author(1L, lastName1, name1, surname1);
        Author author2 = new Author(2L, lastName2, name2, surname2);

        List<Author> authorList = Arrays.asList(author1, author2);        
        
        when(authorRepo.findAll()).thenReturn(authorList);
        
        List<Author> result = authorService.findAllAuthors();
        
        assertEquals(authorList, result);
    }

    /**
     * Метод для тестирования поиска автора по id
     * @param id - идентификатор автора
     * @param lastName - фамилия автора
     * @param name - имя автора
     * @param surName - отчество автора
     */
    @ParameterizedTest
    @CsvSource({
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов",
            "5, Алексеевич, Ибрагим, Кандибобер"
    })
    void findById(Long id, String lastName, String name, String surName) {
        Author author = new Author(id, lastName, name, surName);

        when(authorRepo.findById(id)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(author.getName(), result.get().getName());
        verify(authorRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения автора в базу
     * @param id - идентификатор автора
     * @param name - имя автора
     * @param surName - фамилия автора
     * @param lastName - отчество автора
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, Иванович, Иван, Иванов",
            "2, Петрович, Иван, Иванов",
            "3, Владленович, Иван, Иванов",
            "4, Головач, Лена, Иванов",
            "5, Алексеевич, Ибрагим, Кандибобер"
    })
    void save(Long id, String lastName, String name, String surName) {
        Author author = new Author(id, lastName, name, surName);

        when(authorRepo.save(author)).thenReturn(author);

        Author result = authorService.save(author);

        assertEquals(result, author);
    }


    /**
     * Метод для тестирования обновления автора в бд
     * @param lastName - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     */
    @ParameterizedTest
    @CsvSource({
            "Иванович, Иван, Иванов",
            "Владленович, Иван, Иванов",
            "Алексеевич, Ибрагим, Кандибобер",
            "Емельянов, Сергей, Александрович",
            "Матвеев, Мирон, Александрович"
    })
    void update(String lastName, String name, String surname){
        Author author = new Author(1L, lastName, name, surname);

        authorService.update(author);

        verify(authorRepo, times(1)).save(author);
    }

    /**
     * Метод для тестирования удаления автора из бд
     * @param id - Идентификатор автора
     * @param lastName - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     */
    @ParameterizedTest
    @CsvSource({
            "1, Иванович, Иван, Иванов",
            "2, Владленович, Иван, Иванов",
            "3, Алексеевич, Ибрагим, Кандибобер",
            "4, Емельянов, Сергей, Александрович",
            "5, Матвеев, Мирон, Александрович"
    })
    void deleteById(Long id, String lastName, String name, String surname){
        Author author = new Author(id, lastName, name, surname);

        authorService.save(author);

        authorService.deleteById(id);

        assertFalse(authorRepo.existsById(id));
    }
}

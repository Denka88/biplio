package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.repo.AuthorRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepo authorRepo;

    @InjectMocks
    private AuthorServiceImpl authorService;

    void setList() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1L, "Иванович", "Иван", "Иванов"));
        authors.add(new Author(2L, "Петрович", "Иван", "Иванов"));
        authors.add(new Author(3L, "Владленович", "Иван", "Иванов"));
        authors.add(new Author(4L, "Головач", "Лена", "Иванов"));
    }

    @Test
    void findAllAuthors() {

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
            "4, Головач, Лена, Иванов"
    })
    void findById(Long id, String lastName, String name, String surName) {
        Author author = new Author(id, lastName, name, surName);

        when(authorRepo.findById(id)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.findById(id);
        System.out.printf("Результат: %s %s %s", result.get().getName(), result.get().getSurName(), result.get().getLastName());

        assertTrue(result.isPresent());
        assertEquals("Петр", result.get().getName());
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
            "4, Головач, Лена, Иванов"
    })
    void save(Long id, String name, String surName, String lastName) {
        Author author = new Author(id, lastName, name, surName);

        when(authorRepo.save(author)).thenReturn(author);

        Author result = authorService.save(author);

        assertEquals(result, author);
    }

}

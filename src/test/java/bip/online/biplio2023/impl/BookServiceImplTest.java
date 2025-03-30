package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.*;
import bip.online.biplio2023.repo.BookRepo;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {    
    
    @Mock
    private BookRepo bookRepo;
    
    @InjectMocks
    private BookServiceImpl bookService;


    /**
     * Метод для тестирования поиска всех книг
     * @param lastname - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     * @param publisher - Название издателя
     * @param city - Город издателя
     * @param genre - Название жанра книги
     * @param year - Год выпуска книги
     */
    @ParameterizedTest
    @CsvSource({
            "Олегович, Олег, Олегов, Издатель№1, Омск, Романтика, 2021",
            "Иванович, Иван, Иванов, Издатель№15, Лондон, Детектив, 1954",
            "Алексеевич, Ибрагим, Кандибобер, Издатель№162, Москва, Фантастика, 2015",
            "Емельянов, Сергей, Александрович, Издатель№63, Париж, Сказки, 2001",
            "Матвеев, Мирон, Александрович, Издатель№144, Краснодар, Анекдоты, 2025"
    })
    void findAllBooks(String lastname, String name, String surname, String publisher, String city, String genre, String year) {
        Book book1 = new Book(1L, 
                new Author(1L, lastname, name, surname),
                new Publisher(1L, publisher, new City(1L, city)),
                new Genre(1L, genre), year);
        
        Book book2 = new Book(1L,
                new Author(1L, lastname, name, surname),
                new Publisher(1L, publisher, new City(1L, city)),
                new Genre(1L, genre), year);

        List<Book> bookList = Arrays.asList(book1, book2);
        
        when(bookRepo.findAll()).thenReturn(bookList);
        
        List<Book> result = bookService.findAllBooks();
        
        assertEquals(bookList, result);
    }

    /**
     * Метод для тестирования поиска книги по id
     * @param id - идентификатор книги
     * @param lastname - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     * @param publisher - Название издателя
     * @param city - Город издателя
     * @param genre - Название жанра книги
     * @param year - Год выпуска книги
     */
    @ParameterizedTest
    @CsvSource({
            "1, Олегович, Олег, Олегов, Издатель№1, Омск, Романтика, 2021",
            "2, Иванович, Иван, Иванов, Издатель№15, Лондон, Детектив, 1954",
            "3, Алексеевич, Ибрагим, Кандибобер, Издатель№162, Москва, Фантастика, 2015",
            "4, Емельянов, Сергей, Александрович, Издатель№63, Париж, Сказки, 2001",
            "5, Матвеев, Мирон, Александрович, Издатель№144, Краснодар, Анекдоты, 2025"
    })
    void findById(Long id, String lastname, String name, String surname, String publisher, String city, String genre, String year) {
        Book book = new Book(id,
                new Author(1L, lastname, name, surname),
                new Publisher(1L, publisher, new City(1L, city)),
                new Genre(1L, genre), year);
        
        when(bookRepo.findById(id)).thenReturn(Optional.of(book));
        
        Optional<Book> result = bookService.findById(id);
        assertTrue(result.isPresent());
        assertEquals(book, result.get());
        verify(bookRepo, times(1)).findById(id);
    }


    /**
     * Метод для тестирования сохранения книги в базу
     * @param id - идентификатор книги
     * @param lastname - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     * @param publisher - Название издателя
     * @param city - Город издателя
     * @param genre - Название жанра книги
     * @param year - Год выпуска книги
     */
    @ParameterizedTest
    @CsvSource({
            "1, Олегович, Олег, Олегов, Издатель№1, Омск, Романтика, 2021",
            "2, Иванович, Иван, Иванов, Издатель№15, Лондон, Детектив, 1954",
            "3, Алексеевич, Ибрагим, Кандибобер, Издатель№162, Москва, Фантастика, 2015",
            "4, Емельянов, Сергей, Александрович, Издатель№63, Париж, Сказки, 2001",
            "5, Матвеев, Мирон, Александрович, Издатель№144, Краснодар, Анекдоты, 2025"
    })
    void save(Long id, String lastname, String name, String surname, String publisher, String city, String genre, String year) {
        Book book = new Book(id,
                new Author(1L, lastname, name, surname),
                new Publisher(1L, publisher, new City(1L, city)),
                new Genre(1L, genre), year);
        
        when(bookRepo.save(book)).thenReturn(book);
        
        Book result = bookService.save(book);
        
        assertEquals(book, result);
    }

    /**
     * Метод для тестирования обновления книги в базе
     * @param lastname - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     * @param publisher - Название издателя
     * @param city - Город издателя
     * @param genre - Название жанра книги
     * @param year - Год выпуска книги
     */
    @ParameterizedTest
    @CsvSource({
            "Олегович, Олег, Олегов, Издатель№1, Омск, Романтика, 2021",
            "Иванович, Иван, Иванов, Издатель№15, Лондон, Детектив, 1954",
            "Алексеевич, Ибрагим, Кандибобер, Издатель№162, Москва, Фантастика, 2015",
            "Емельянов, Сергей, Александрович, Издатель№63, Париж, Сказки, 2001",
            "Матвеев, Мирон, Александрович, Издатель№144, Краснодар, Анекдоты, 2025"
    })
    void update(String lastname, String name, String surname, String publisher, String city, String genre, String year) {
        Book book = new Book(1L,
                new Author(1L, lastname, name, surname),
                new Publisher(1L, publisher, new City(1L, city)),
                new Genre(1L, genre), year);
        
        bookService.update(book);
        verify(bookRepo, times(1)).save(book);
    }

    /**
     * Метод для тестирования удаления книги из базы
     * @param id - идентификатор книги
     * @param lastname - Отчество автора
     * @param name - Имя автора
     * @param surname - Фамилия автора
     * @param publisher - Название издателя
     * @param city - Город издателя
     * @param genre - Название жанра книги
     * @param year - Год выпуска книги
     */
    @ParameterizedTest
    @CsvSource({
            "1, Олегович, Олег, Олегов, Издатель№1, Омск, Романтика, 2021",
            "2, Иванович, Иван, Иванов, Издатель№15, Лондон, Детектив, 1954",
            "3, Алексеевич, Ибрагим, Кандибобер, Издатель№162, Москва, Фантастика, 2015",
            "4, Емельянов, Сергей, Александрович, Издатель№63, Париж, Сказки, 2001",
            "5, Матвеев, Мирон, Александрович, Издатель№144, Краснодар, Анекдоты, 2025"
    })
    void deleteById(Long id, String lastname, String name, String surname, String publisher, String city, String genre, String year) {
        Book book = new Book(id,
                new Author(1L, lastname, name, surname),
                new Publisher(1L, publisher, new City(1L, city)),
                new Genre(1L, genre), year);
        
        bookService.save(book);
        
        bookService.deleteById(id);
        
        assertFalse(bookRepo.existsById(id));
    }
}
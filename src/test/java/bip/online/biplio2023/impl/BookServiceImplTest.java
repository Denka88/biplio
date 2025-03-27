package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Book;
import bip.online.biplio2023.repo.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {    
    
    @Mock
    private BookRepo bookRepo;
    
    @InjectMocks
    private BookServiceImpl bookService;
    
    @ParameterizedTest
    @CsvSource({
            ""
    })
    void findAllBooks() {
        Book book1 = new Book(1L, );
        Book book2 = new Book();
        
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}
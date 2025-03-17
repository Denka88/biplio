package bip.online.biplio2023.service;

import bip.online.biplio2023.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks();

    Optional<Book> findById(Long id);

    Book save(Book data);

    void update(Book data);
    
    void delete(Book data);
}

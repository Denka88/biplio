package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Book;
import bip.online.biplio2023.repo.BookRepo;
import bip.online.biplio2023.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepo.findById(Math.toIntExact(id));
    }

    @Override
    public Book save(Book data) {
        return bookRepo.save(data);
    }

    @Override
    public void update(Book data) {
        bookRepo.save(data);
    }
}

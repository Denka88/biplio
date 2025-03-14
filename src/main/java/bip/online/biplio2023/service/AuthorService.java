package bip.online.biplio2023.service;

import bip.online.biplio2023.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAllAuthors();

    Optional<Author> findById(Long id);

    Author save(Author data);

    void update(Author data);

    void deleteById(Long id);

}

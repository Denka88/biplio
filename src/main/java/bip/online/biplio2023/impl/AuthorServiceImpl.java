package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Author;
import bip.online.biplio2023.repo.AuthorRepo;
import bip.online.biplio2023.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepo.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public void update(Author author) {
        authorRepo.save(author);
    }

    @Override
    public void delete(Author data) {
        authorRepo.delete(data);
    }
}

package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Genre;
import bip.online.biplio2023.repo.GenreRepo;
import bip.online.biplio2023.service.GenreService;

import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepo.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepo.findById(Math.toIntExact(id));
    }

    @Override
    public Genre save(Genre data) {
        return genreRepo.save(data);
    }

    @Override
    public void update(Genre data) {
        genreRepo.save(data);
    }
}

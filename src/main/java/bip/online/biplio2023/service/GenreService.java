package bip.online.biplio2023.service;

import bip.online.biplio2023.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> findAllGenres();

    Optional<Genre> findById(Long id);

    Genre save(Genre data);

    void update(Genre data);

    void delete(Genre data);
}

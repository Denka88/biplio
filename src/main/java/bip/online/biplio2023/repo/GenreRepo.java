package bip.online.biplio2023.repo;

import bip.online.biplio2023.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}

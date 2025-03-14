package bip.online.biplio2023.repo;

import bip.online.biplio2023.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {

}

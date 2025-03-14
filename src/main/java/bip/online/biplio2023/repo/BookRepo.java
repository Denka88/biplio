package bip.online.biplio2023.repo;

import bip.online.biplio2023.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {

}

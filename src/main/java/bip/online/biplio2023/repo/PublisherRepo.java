package bip.online.biplio2023.repo;

import bip.online.biplio2023.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}

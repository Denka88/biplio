package bip.online.biplio2023.service;

import bip.online.biplio2023.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    List<Publisher> findAllPublishers();

    Optional<Publisher> findById(Long id);

    Publisher save(Publisher data);

    void update(Publisher data);

}

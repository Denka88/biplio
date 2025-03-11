package bip.online.biplio2023.service;

import bip.online.biplio2023.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    List<Publisher> findAllPublisher();

    Optional<Publisher> findPublisherById(int id);

    Publisher save(Publisher data);

    void update(Publisher data);

}

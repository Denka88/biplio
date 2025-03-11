package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.repo.PublisherRepo;
import bip.online.biplio2023.service.PublisherService;

import java.util.List;
import java.util.Optional;

public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherServiceImpl(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Publisher> findAllPublisher() {
        return publisherRepo.findAll();
    }

    @Override
    public Optional<Publisher> findPublisherById(int id) {
        return publisherRepo.findById(id);
    }

    @Override
    public Publisher save(Publisher data) {
        return publisherRepo.save(data);
    }

    @Override
    public void update(Publisher data) {
        publisherRepo.save(data);
    }
}

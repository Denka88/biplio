package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.Publisher;
import bip.online.biplio2023.repo.PublisherRepo;
import bip.online.biplio2023.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherServiceImpl(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Publisher> findAllPublishers() {
        return publisherRepo.findAll();
    }

    @Override
    public Optional<Publisher> findById(Long id) {
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

    @Override
    public void delete(Publisher data) {
        publisherRepo.delete(data);
    }


}

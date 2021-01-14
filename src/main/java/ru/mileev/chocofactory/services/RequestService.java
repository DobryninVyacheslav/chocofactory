package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Request;
import ru.mileev.chocofactory.repositories.RequestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repository;

    public Request create(Request request) {
        return repository.save(request);
    }

    public List<Request> readAll() {
        return repository.findAll();
    }

    public List<Request> readAllByIngredients(String ingredients) {
        return repository.findAllByIngredients(ingredients);
    }
}

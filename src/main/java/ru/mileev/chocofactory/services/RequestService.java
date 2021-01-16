package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Request;
import ru.mileev.chocofactory.repositories.RequestRepository;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repository;

    public Request create(Request request) {
        return repository.save(request);
    }

    public List<Request> findByIngredients(String ingredients) {
        if (isNullOrEmpty(ingredients)) {
            return repository.findAll();
        } else {
            return repository.findAllByIngredients(ingredients);
        }
    }

    public List<Request> readAll() {
        return repository.findAll();
    }
}

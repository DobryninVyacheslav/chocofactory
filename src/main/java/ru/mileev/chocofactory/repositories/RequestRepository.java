package ru.mileev.chocofactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mileev.chocofactory.domain.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByIngredients(String ingredients);

}

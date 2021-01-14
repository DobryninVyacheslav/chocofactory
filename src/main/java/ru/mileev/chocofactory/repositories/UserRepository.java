package ru.mileev.chocofactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mileev.chocofactory.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
}

package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Role;
import ru.mileev.chocofactory.domain.User;
import ru.mileev.chocofactory.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final MailSender mailSender;

    public User save(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public boolean addUser(User user) {
        User userFromDb = repository.findFirstByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        repository.save(user);

        String email = user.getEmail();

        if (email != null && !email.isEmpty()) {
            String message = String.format("Добрый день, %s!\n" +
                    "Добро пожаловать на шоколадную фабрику.\n" +
                    "Пожалуйста, перейдите по ссылке: http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findFirstByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = repository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        repository.save(user);

        return true;
    }
}

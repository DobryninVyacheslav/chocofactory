package ru.mileev.chocofactory.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mileev.chocofactory.domain.Role;
import ru.mileev.chocofactory.domain.User;
import ru.mileev.chocofactory.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final MailSender mailSender;

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

        sendMsg(user);

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

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        List<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toList());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        repository.save(user);
    }

    public void updateProfile(User user, String email, String password) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = Objects.equals(email, userEmail);

        if (isEmailChanged) {
            user.setEmail(email);

            if (!isNullOrEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!isNullOrEmpty(password)) {
            user.setPassword(password);
        }

        repository.save(user);

        if (isEmailChanged) {
            sendMsg(user);
        }
    }

    private void sendMsg(User user) {
        if (!isNullOrEmpty(user.getEmail())) {
            String message = String.format("Добрый день, %s!\n" +
                            "Добро пожаловать на шоколадную фабрику.\n" +
                            "Пожалуйста, перейдите по ссылке: http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }
}

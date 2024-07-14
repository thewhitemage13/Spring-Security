package org.thewhitemage13.springsecurityexample.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thewhitemage13.springsecurityexample.model.Application;
import org.thewhitemage13.springsecurityexample.model.MyUser;
import org.thewhitemage13.springsecurityexample.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applications = new ArrayList<Application>();
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> getApplications() {
        return applications;
    }

    public Application applicationById(int id) {
        return applications.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }
}

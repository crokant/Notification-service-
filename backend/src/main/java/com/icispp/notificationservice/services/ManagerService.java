package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.Manager;
import com.icispp.notificationservice.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ManagerService(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Manager> findByUsername(String username) {
        return managerRepository.findByUsername(username);
    }

    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    public Optional<Manager> findByEmail(String email) {
        return managerRepository.findByEmail(email);
    }

    public Manager registerManager(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return managerRepository.save(manager);
    }
    /*
    public String createRegistrationLink() {
        String token = java.util.UUID.randomUUID().toString(); // Генерация уникального токена
        User user = new User();
        user.setRegistrationToken(token);
        userRepository.save(user);
        return "http://your-app.com/register?token=" + token; // Формирование ссылки
    }
     */
}

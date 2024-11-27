package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.Manager;
import com.icispp.notificationservice.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
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

    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }
}

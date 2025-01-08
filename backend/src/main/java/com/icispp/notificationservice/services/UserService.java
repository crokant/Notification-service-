package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.repositories.SubscriptionRepository;
import com.icispp.notificationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления пользователями в системе.
 * Предоставляет методы для регистрации пользователей, поиска и валидации паролей.
 */
@Service
public class UserService {

    private final UserRepository userRepository; // Репозиторий для работы с данными пользователей
    private final SubscriptionRepository subscriptionRepository;
    private final PasswordEncoder passwordEncoder; // Кодировщик паролей для безопасного хранения
    /**
     * Конструктор класса UserService.
     *
     * @param userRepository Репозиторий для работы с пользователями
     * @param passwordEncoder Кодировщик паролей
     */
    @Autowired
    public UserService(UserRepository userRepository, SubscriptionRepository subscriptionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Находит пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя
     * @return Опциональный объект пользователя, если найден, иначе пустой объект
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Регистрирует нового пользователя с использованием объекта User.
     * Пароль пользователя кодируется перед сохранением.
     *
     * @param user Объект пользователя, который нужно зарегистрировать
     * @return Сохраненный объект пользователя
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Регистрирует нового пользователя, используя его имя, email и пароль.
     * Пароль кодируется перед сохранением.
     *
     * @param username Имя пользователя
     * @param email Email пользователя
     * @param password Пароль пользователя
     */

    public void registerUser(String username, String email, String password) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    /**
     * Проверяет, использовалось ли указанное имя пользователя.
     *
     * @param username Имя пользователя для проверки
     * @return true, если имя пользователя уже используется, иначе false
     */
    public boolean wasUsernameUsed(String username) {
        return userRepository.findByName(username).isPresent();
    }

    /**
     * Находит пользователя по его имени.
     *
     * @param name Имя пользователя
     * @return Опциональный объект пользователя, если найден, иначе пустой объект
     */
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Получает подписки текущего пользователя.
     *
     * @param name Имя пользователя
     * @return Список подписок пользователя
     */
    public List<Subscription> getUserSubscriptions(String name) {
        return userRepository.getSubscriptionsByName(name);
    }

    /**
     * Проверяет, соответствует ли введенный пароль закодированному паролю.
     *
     * @param rawPassword Введенный пароль
     * @param encodedPassword Закодированный пароль
     * @return true, если пароли совпадают, иначе false
     */
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}

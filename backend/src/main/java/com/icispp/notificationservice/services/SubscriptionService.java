package com.icispp.notificationservice.services;

import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import com.icispp.notificationservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Сервисный класс для управления подписками в сервисе уведомлений.
 * Этот класс предоставляет методы для создания подписок, добавления пользователей в подписки
 * и поиска подписок по их идентификатору.
 *
 * <p>
 * {@link SubscriptionService} взаимодействует с {@link SubscriptionRepository}
 * для выполнения операций CRUD над сущностями подписок.
 * </p>
 *
 * <p>
 * Этот сервис аннотирован {@link Service}, что указывает на то, что он является компонентом
 * сервиса Spring.
 * </p>
 */
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * Конструктор для создания нового SubscriptionService с указанным SubscriptionRepository.
     *
     * @param subscriptionRepository репозиторий, используемый для выполнения операций над подписками
     */
    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Находит подписку по ее идентификатору.
     *
     * @param id идентификатор подписки для поиска
     * @return Optional, содержащий найденную подписку, или пустой Optional, если подписка не найдена
     */
    public Optional<Subscription> findById(Long id) {
        return subscriptionRepository.findById(id);
    }

    /**
     * Добавляет пользователя в список подписчиков подписки.
     *
     * @param user пользователь, который будет добавлен в подписку
     * @param subscription подписка, в которую будет добавлен пользователь
     * @return обновленная подписка после добавления пользователя
     */
    public Subscription addUserToSubscription(User user, Subscription subscription) {
        subscription.getSubscribers().add(user);
        return subscriptionRepository.save(subscription);
    }

    /**
     * Создает новую подписку с указанным именем и создателем.
     *
     * @param name имя новой подписки
     * @param creator пользователь, который создает подписку
     * @return созданная подписка
     */
    public Subscription createSubscription(String name, User creator) {
        Subscription subscription = Subscription.builder()
                .name(name)
                .creator(creator)
                .build();
        return subscriptionRepository.save(subscription);
    }
}

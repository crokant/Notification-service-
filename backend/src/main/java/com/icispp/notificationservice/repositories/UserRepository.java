package com.icispp.notificationservice.repositories;

import com.icispp.notificationservice.models.Subscription;
import com.icispp.notificationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    @Query("SELECT u.subscriptions FROM User u WHERE u.name = :name")
    List<Subscription> getSubscriptionsByName(@Param("name") String name);
}

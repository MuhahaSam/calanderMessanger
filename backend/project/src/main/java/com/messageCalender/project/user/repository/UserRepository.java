package com.messageCalender.project.user.repository;

import com.messageCalender.project.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.messengers WHERE u.id = :id")
    Optional<UserEntity> findByIdWithMessengers(@Param("id") BigInteger id);
}
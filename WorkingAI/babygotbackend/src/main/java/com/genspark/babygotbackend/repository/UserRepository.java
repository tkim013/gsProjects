package com.genspark.babygotbackend.repository;

import com.genspark.babygotbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = ?1")
    Optional<User> findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE LOWER(u.email) = ?1")
    void deleteUserByEmail(String email);
}

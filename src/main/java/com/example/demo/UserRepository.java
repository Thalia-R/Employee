package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsByEmail(String email);

}

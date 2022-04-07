package com.secondhand.springbootsecondhand.repository;

import com.secondhand.springbootsecondhand.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@ComponentScan(basePackages = "{com.secondhand.springbootsecondhand.repository}")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String mail);
}

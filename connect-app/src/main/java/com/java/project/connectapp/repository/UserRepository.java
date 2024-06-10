package com.java.project.connectapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.project.connectapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String userName);

    User findById(int id);
    
    User findByUsernameAndPassword(String username, String password);
    
    List<User> findByUsernameContainingIgnoreCase(String query);
    
}

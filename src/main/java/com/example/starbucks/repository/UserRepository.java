package com.example.starbucks.repository;

import com.example.starbucks.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCustom, Integer> {
    UserCustom findByUserId(String userId);

}

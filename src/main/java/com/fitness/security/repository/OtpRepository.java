package com.fitness.security.repository;

import com.fitness.security.entity.Otp;
import com.fitness.security.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {
   List<Otp> findAllByUser(User user);
}
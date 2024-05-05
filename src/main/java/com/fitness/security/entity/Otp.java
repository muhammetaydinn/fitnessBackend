package com.fitness.security.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "otp")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
  // id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String otp;
  // ONE TO ONE EMAIL USER
  @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  private boolean is_used = false;
  // expiration date
  private long expiration_date;
}

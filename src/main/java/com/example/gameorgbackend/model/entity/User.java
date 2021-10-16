package com.example.gameorgbackend.model.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long userId;

  @Column(nullable = false, length = 50, unique = true)
  private String username;

  @Column(nullable = false, length = 16)
  @JsonIgnore
  private String password;

  @Column(nullable = false, unique = true)
  @JsonIgnore
  private String email;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 50)
  private String surname;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Contact contact;

  @OneToMany(mappedBy="user")
  private Set<GameAccount> gameAccounts = new HashSet<>();

  @Column(nullable = false)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> Roles = new HashSet<>();
}

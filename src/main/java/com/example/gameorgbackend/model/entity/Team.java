package com.example.gameorgbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long teamId;

  @Column(length = 20)
  private String teamName;

  @JsonIgnoreProperties({"teams", "organizer"})
  @ManyToOne(fetch = FetchType.LAZY)
  private Tournament tournament;

  @JsonIgnoreProperties({"teams","tournaments"})
  @ManyToMany(mappedBy = "teams", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private Set<User> players = new HashSet<>();
}

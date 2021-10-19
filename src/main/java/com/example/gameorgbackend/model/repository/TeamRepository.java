package com.example.gameorgbackend.model.repository;

import com.example.gameorgbackend.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
  boolean existsByTeamName(String name);
}

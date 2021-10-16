package com.example.gameorgbackend.model.repository;

import com.example.gameorgbackend.model.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
  boolean existsByName(String name);
}

package com.example.gameorgbackend.model.repository;

import com.example.gameorgbackend.model.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TournamentRepository extends JpaRepository<Tournament, Long>,
    JpaSpecificationExecutor<Tournament> {
  boolean existsByName(String name);
}

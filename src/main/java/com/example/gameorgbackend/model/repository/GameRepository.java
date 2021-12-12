package com.example.gameorgbackend.model.repository;

import com.example.gameorgbackend.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}

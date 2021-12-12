package com.example.gameorgbackend.model.repository;

import com.example.gameorgbackend.model.entity.GameAccount;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAccountRepository extends JpaRepository<GameAccount, Long> {
  boolean existsByInGameNameAndGameName(String inGameName, String gameName);

  boolean existsByGameNameAndUserUserId(String gameName, Long userId);

  Collection<GameAccount> findByUserUserId(Long userId);
}

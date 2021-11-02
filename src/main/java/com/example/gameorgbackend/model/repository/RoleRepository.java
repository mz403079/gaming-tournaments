package com.example.gameorgbackend.model.repository;

import com.example.gameorgbackend.model.entity.ERole;
import com.example.gameorgbackend.model.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

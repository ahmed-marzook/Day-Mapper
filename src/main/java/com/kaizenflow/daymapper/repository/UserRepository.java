package com.kaizenflow.daymapper.repository;

import com.kaizenflow.daymapper.entities.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserGuid(UUID userGuid);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}

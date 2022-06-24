package com.genspark.backend.Repository;

import com.genspark.backend.Entity.ERole;
import com.genspark.backend.Entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Logger logger = LoggerFactory.getLogger(RoleRepository.class);
  Optional<Role> findByName(ERole type);
}

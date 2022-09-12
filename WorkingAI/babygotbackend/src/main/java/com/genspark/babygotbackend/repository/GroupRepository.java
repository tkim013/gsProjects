package com.genspark.babygotbackend.repository;

import com.genspark.babygotbackend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByCode(String code);
}

package com.genspark.warriorsofchaos.Dao;

import com.genspark.warriorsofchaos.Entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDao extends JpaRepository<Unit, Integer> {
}

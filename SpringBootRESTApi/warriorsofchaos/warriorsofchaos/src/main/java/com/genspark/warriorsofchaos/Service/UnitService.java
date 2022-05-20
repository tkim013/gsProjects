package com.genspark.warriorsofchaos.Service;

import com.genspark.warriorsofchaos.Entity.Unit;

import java.util.List;

public interface UnitService {
    List<Unit> getAllUnits();
    Unit getUnitById(Integer unitID);
    Unit addUnit(Unit unit);
    Unit updateUnit(Unit unit);
    String deleteUnitById(Integer unitID);
}

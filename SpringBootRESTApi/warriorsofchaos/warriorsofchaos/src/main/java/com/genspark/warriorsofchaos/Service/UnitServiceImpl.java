package com.genspark.warriorsofchaos.Service;

import com.genspark.warriorsofchaos.Dao.UnitDao;
import com.genspark.warriorsofchaos.Entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    UnitDao unitDao;
    @Override
    public List<Unit> getAllUnits() {
        return this.unitDao.findAll();
    }

    @Override
    public Unit getUnitById(Integer unitID) {
        Optional<Unit> c = this.unitDao.findById(unitID);
        Unit unit = null;
        if (c.isPresent())
        {
            unit = c.get();

        } else {
            throw new RuntimeException("Unit not found for id : " + unitID);
        }
        return unit;
    }

    @Override
    public Unit addUnit(Unit unit) {
        return this.unitDao.save(unit);
    }

    @Override
    public Unit updateUnit(Unit unit) {
        return this.unitDao.save(unit);
    }

    @Override
    public String deleteUnitById(Integer unitID) {
        this.unitDao.deleteById(unitID);

        return "Deleted Successfully";
    }
}

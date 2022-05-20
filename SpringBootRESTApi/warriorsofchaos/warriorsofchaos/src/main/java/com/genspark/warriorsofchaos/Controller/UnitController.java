package com.genspark.warriorsofchaos.Controller;

import com.genspark.warriorsofchaos.Entity.Unit;
import com.genspark.warriorsofchaos.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnitController {

    @Autowired
    UnitService unitService;

    @GetMapping("/")
    public String home()
    {
        return "This is here.";
    }

    @GetMapping("/units")
    public List<Unit> getUnits()
    {
        return this.unitService.getAllUnits();
    }

    @GetMapping("/units/{unitID}")
    public Unit getUnit(@PathVariable String unitID)
    {
        return this.unitService.getUnitById(Integer.parseInt(unitID));
    }

    @PostMapping("/units")
    public Unit addUnits(@RequestBody Unit unit)
    {
        return this.unitService.addUnit(unit);
    }

    @PutMapping("/units")
    public Unit updateUnit(@RequestBody Unit unit)
    {
        return this.unitService.updateUnit(unit);
    }

    @DeleteMapping("/units/{unitID}")
    public String deleteUnit(@PathVariable String unitID)
    {
        return this.unitService.deleteUnitById(Integer.parseInt(unitID));
    }
}

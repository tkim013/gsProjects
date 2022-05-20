package com.genspark.warriorsofchaos.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_units")
public class Unit {

    @Id
    @Column(name = "unit_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer unitID;
    private String unitName;
    private Integer armor;
    private Integer leadership;
    private Integer speed;
    private Integer meleeAttack;
    private Integer meleeDefense;
    private Integer weaponStrength;
    private Integer chargeBonus;

    public Unit() {
    }

    public Unit(String unitName,
                Integer armor,
                Integer leadership,
                Integer speed,
                Integer meleeAttack,
                Integer meleeDefense,
                Integer weaponStrength,
                Integer chargeBonus) {
        this.unitName = unitName;
        this.armor = armor;
        this.leadership = leadership;
        this.speed = speed;
        this.meleeAttack = meleeAttack;
        this.meleeDefense = meleeDefense;
        this.weaponStrength = weaponStrength;
        this.chargeBonus = chargeBonus;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getLeadership() {
        return leadership;
    }

    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getMeleeAttack() {
        return meleeAttack;
    }

    public void setMeleeAttack(Integer meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public Integer getMeleeDefense() {
        return meleeDefense;
    }

    public void setMeleeDefense(Integer meleeDefense) {
        this.meleeDefense = meleeDefense;
    }

    public Integer getWeaponStrength() {
        return weaponStrength;
    }

    public void setWeaponStrength(Integer weaponStrength) {
        this.weaponStrength = weaponStrength;
    }

    public Integer getChargeBonus() {
        return chargeBonus;
    }

    public void setChargeBonus(Integer chargeBonus) {
        this.chargeBonus = chargeBonus;
    }
}

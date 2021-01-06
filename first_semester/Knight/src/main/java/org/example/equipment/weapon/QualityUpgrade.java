package org.example.equipment.weapon;

public class QualityUpgrade extends WeaponDecorator {

  public QualityUpgrade(Weapon weapon) {
    super(weapon);
  }

  @Override
  public String getName() {
    return weapon.getName() + ", quality upgrade";
  }

  @Override
  public Integer getPrice() {
    return weapon.getPrice() + 40;
  }

  @Override
  public Integer getWeight() {
    return weapon.getWeight() + 20;
  }

}

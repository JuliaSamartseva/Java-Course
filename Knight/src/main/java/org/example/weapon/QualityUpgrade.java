package org.example.weapon;

public class QualityUpgrade extends WeaponDecorator {

  public QualityUpgrade(Weapon weapon) {
    super(weapon);
  }

  @Override
  public String getName() {
    return weapon.getName() + ", quality upgrade";
  }

  @Override
  public Integer getCost() {
    return weapon.getCost() + 40;
  }

  @Override
  public Integer getWeight() {
    return weapon.getWeight() + 20;
  }

}

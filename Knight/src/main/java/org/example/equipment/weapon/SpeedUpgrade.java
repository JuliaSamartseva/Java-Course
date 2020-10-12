package org.example.equipment.weapon;

public class SpeedUpgrade extends WeaponDecorator {

  public SpeedUpgrade(Weapon weapon) {
    super(weapon);
  }

  @Override
  public String getName() {
    return weapon.getName() + ", speed upgrade";
  }

  @Override
  public Integer getPrice() {
    return weapon.getPrice() + 50;
  }

  @Override
  public Integer getWeight() {
    return weapon.getWeight() + 10;
  }

}

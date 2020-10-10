package org.example.weapon;

public class WeaponDecorator implements Weapon {
  Weapon weapon;

  public WeaponDecorator(Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public String getName() {
    return weapon.getName();
  }

  @Override
  public Integer getCost() {
    return weapon.getCost();
  }

  @Override
  public Integer getWeight() {
    return weapon.getWeight();
  }

  @Override
  public void setName(String name) {
    weapon.setName(name);
  }

  @Override
  public void setCost(Integer cost) {
    weapon.setCost(cost);
  }

  @Override
  public void setWeight(Integer weight) {
    weapon.setWeight(weight);
  }
}

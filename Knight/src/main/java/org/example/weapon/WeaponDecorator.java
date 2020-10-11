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
  public Integer getPrice() {
    return weapon.getPrice();
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
  public void setPrice(Integer price) {
    weapon.setPrice(price);
  }

  @Override
  public void setWeight(Integer weight) {
    weapon.setWeight(weight);
  }
}

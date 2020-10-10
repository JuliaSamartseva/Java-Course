package org.example.weapon;

public interface Weapon {
  String getName();
  Integer getCost();
  Integer getWeight();
  void setName(String name);
  void setCost(Integer cost);
  void setWeight(Integer weight);
}

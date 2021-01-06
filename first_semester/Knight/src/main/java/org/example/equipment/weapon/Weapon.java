package org.example.equipment.weapon;

import org.example.equipment.Equipment;

public interface Weapon extends Equipment {
  String getName();
  Integer getPrice();
  Integer getWeight();
  void setName(String name);
  void setPrice(Integer price);
  void setWeight(Integer weight);
}

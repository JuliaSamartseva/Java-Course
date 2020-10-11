package org.example.weapon;

import org.example.Equipment;

public interface Weapon extends Equipment {
  String getName();
  Integer getPrice();
  Integer getWeight();
  void setName(String name);
  void setPrice(Integer price);
  void setWeight(Integer weight);
}

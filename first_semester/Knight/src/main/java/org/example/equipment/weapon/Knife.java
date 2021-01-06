package org.example.equipment.weapon;

public class Knife implements Weapon {

  private String name = "Knife";
  private Integer price = 100;
  private Integer weight = 100;

  public Knife() {}

  public Knife(String name, Integer price, Integer weight) {
    this.name = name;
    this.price = price;
    this.weight = weight;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Integer getPrice() {
    return price;
  }

  @Override
  public void setPrice(Integer price) {
    this.price = price;
  }

  @Override
  public Integer getWeight() {
    return weight;
  }

  @Override
  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}

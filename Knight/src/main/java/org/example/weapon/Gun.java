package org.example.weapon;

public class Gun implements Weapon {

  private String name = "Gun";
  private Integer price = 200;
  private Integer weight = 250;

  public Gun() {}

  public Gun(String name, Integer price, Integer weight) {
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

package org.example.weapon;

public class Gun implements Weapon {

  private String name = "Gun";
  private Integer cost = 200;
  private Integer weight = 250;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Integer getCost() {
    return cost;
  }

  @Override
  public void setCost(Integer cost) {
    this.cost = cost;
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

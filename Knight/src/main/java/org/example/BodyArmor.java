package org.example;

public class BodyArmor implements Equipment {
  private String description;
  private Integer price = 150;
  private Integer weight = 60;

  public BodyArmor() {}

  public BodyArmor(String description, Integer price, Integer weight) {
    this.description = description;
    this.price = price;
    this.weight = weight;
  }

  @Override
  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}

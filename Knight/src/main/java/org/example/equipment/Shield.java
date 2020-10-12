package org.example.equipment;

public class Shield implements Equipment {
  private String description;
  private Integer price = 50;
  private Integer weight = 10;

  public Shield() {}

  public Shield(String description, Integer price, Integer weight) {
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

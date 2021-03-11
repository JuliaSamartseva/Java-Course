package data;

public class Product {
  private final Long id;
  private String name;
  private int price;
  private String description;
  private ProductType type;

  public Product(Long id, String name, int price, String description, ProductType type) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public ProductType getType() {
    return type;
  }

  public void setType(ProductType type) {
    this.type = type;
  }
}

package webapp.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private int id;

  @Size(min = 3, max = 20, message = "min = 3, max = 20 symbols")
  @NotNull(message = "Name cannot be null")
  private String name;

  @Range(min=0)
  @NotNull
  private int price;

  private String description;

  @ManyToOne
  @JoinColumn(name = "product_type_id", nullable = false)
  private ProductType type;

  public Product() {}

  public Product(int id, String name, int price, String description, ProductType type) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductType getType() {
    return type;
  }

  public void setType(ProductType type) {
    this.type = type;
  }
}

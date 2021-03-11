package data;

import java.util.ArrayList;

public class Client extends Member {
  private ArrayList<Product> shoppingCart;

  public Client(Long id, String name) {
    super(id, name);
    shoppingCart = new ArrayList<>();
  }

  public ArrayList<Product> getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ArrayList<Product> shoppingCart) {
    this.shoppingCart = shoppingCart;
  }
}

package data;

import java.util.ArrayList;

public class Client extends User {
  private ArrayList<Product> shoppingCart;

  public Client(Long id, String name) {
    super(id, name, UserType.CLIENT);
    shoppingCart = new ArrayList<>();
  }

  public ArrayList<Product> getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ArrayList<Product> shoppingCart) {
    this.shoppingCart = shoppingCart;
  }
}

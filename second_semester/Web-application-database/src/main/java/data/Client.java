package data;

import java.util.ArrayList;

public class Client extends User {
  private ArrayList<Product> shoppingCart;

  public Client(int id, String name, String password) {
    super(id, name, password, UserType.CLIENT, false);
    shoppingCart = new ArrayList<>();
  }
  public Client(int id, String name, String password, boolean blocked) {
    super(id, name, password, UserType.CLIENT, blocked);
    shoppingCart = new ArrayList<>();
  }

  public ArrayList<Product> getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ArrayList<Product> shoppingCart) {
    this.shoppingCart = shoppingCart;
  }
}

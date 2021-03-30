package webapp.entity;

import java.util.ArrayList;

public class Client extends User {
  private ArrayList<Product> shoppingCart;

  public Client(int id, String name, String password) {
    super(id, UserType.CLIENT, name, password, false);
    shoppingCart = new ArrayList<>();
  }

  public Client(int id, String name, String password, boolean blocked) {
    super(id, UserType.CLIENT, name, password, blocked);
    shoppingCart = new ArrayList<>();
  }

  public ArrayList<Product> getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ArrayList<Product> shoppingCart) {
    this.shoppingCart = shoppingCart;
  }
}

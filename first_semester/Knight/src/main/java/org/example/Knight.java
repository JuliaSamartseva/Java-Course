package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import org.example.equipment.BodyArmor;
import org.example.equipment.Equipment;
import org.example.equipment.Shield;
import org.example.equipment.weapon.Weapon;

public class Knight {
  private final String order;
  private final Weapon weapon;
  private final BodyArmor bodyArmor;
  private final Optional<Shield> shield;

  Knight(Builder builder) {
    this.order = builder.order;
    this.weapon = builder.weapon;
    this.bodyArmor = builder.bodyArmor;
    this.shield = builder.shield;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer getTotalCost() {
    // Shield cost equals to zero if there is no object available.
    int shieldCost = Optional.ofNullable(shield).isPresent() ? shield.get().getPrice() : 0;
    return weapon.getPrice() + shieldCost;
  }

  public ArrayList<Equipment> getSortedEquipmentByWeight() {
    ArrayList<Equipment> result = getAllEquipment();
    result.sort(Comparator.comparingInt(Equipment::getWeight));
    return result;
  }

  public ArrayList<Equipment> getEquipmentWithinPriceRange(int startPrice, int endPrice) {
    ArrayList<Equipment> result = getAllEquipment();
    // Removing all equipment chunks with prices not in the certain range.
    result.removeIf(n -> (n.getPrice() > endPrice || n.getPrice() < startPrice));
    return result;
  }

  private ArrayList<Equipment> getAllEquipment() {
    ArrayList<Equipment> result = new ArrayList<Equipment>(
        Arrays.asList(weapon, bodyArmor));
    if (Optional.ofNullable(shield).isPresent()) result.add(shield.get());
    return result;
  }

  public String getOrder() {
    return order;
  }

  public Optional<Shield> getShield() {
    return shield;
  }

  public BodyArmor getBodyArmor() {
    return bodyArmor;
  }

  public static class Builder {
    private String order;
    private Weapon weapon;
    private Optional<Shield> shield = null;
    private BodyArmor bodyArmor;

    private Builder(String order) {
      this.order = order;
    }

    private Builder() { }

    public Builder order(String order) {
      this.order = order;
      return this;
    }

    public Builder weapon(Weapon weapon) {
      this.weapon = weapon;
      return this;
    }

    public Builder shield(Shield shield) {
      this.shield = Optional.of(shield);
      return this;
    }

    public Builder bodyArmor(BodyArmor bodyArmor) {
      this.bodyArmor = bodyArmor;
      return this;
    }

    public Knight build() {
      return new Knight(this);
    }
  }
}

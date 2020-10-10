package org.example;

import java.util.Optional;
import org.example.weapon.Weapon;

public class Knight {
  private final String order;
  private final Weapon weapon;
  private final Optional<Shield> shield;

  Knight(Builder builder) {
    this.order = builder.order;
    this.weapon = builder.weapon;
    this.shield = builder.shield;
  }

  public Integer getTotalCost() {
    return weapon.getCost();
  }

  public String getOrder() {
    return order;
  }

  public Optional<Shield> getShield() {
    return shield;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    String order;
    Weapon weapon;
    Optional<Shield> shield = null;

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

    public Knight build() {
      return new Knight(this);
    }
  }
}

package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.example.weapon.Gun;
import org.example.weapon.QualityUpgrade;
import org.example.weapon.Weapon;
import org.junit.Test;

public class AppTest {
  @Test
  public void weaponUpgrades() {
    // GIVEN
    Weapon gunWeapon = new Gun();
    Weapon gunWeaponWithQualityUpgrade = new QualityUpgrade(new Gun());

    // THEN
    assertEquals(gunWeapon.getCost().intValue(), 200);
    assertEquals(gunWeapon.getName(), "Gun");
    assertEquals(gunWeaponWithQualityUpgrade.getCost().intValue(), 240);
    assertEquals(gunWeaponWithQualityUpgrade.getName(), "Gun, quality upgrade");
  }

  @Test
  public void knightOptionalShield() {
    Knight knightWithShield = Knight.builder()
        .order("Knights of the Cross")
        .weapon(new Gun())
        .shield(new Shield())
        .build();

    assertEquals(knightWithShield.getOrder(), "Knights of the Cross");
    assertNotNull(knightWithShield.getShield());

    Knight knightWithoutShield = Knight.builder()
        .order("Knights of the Cross")
        .weapon(new Gun())
        .build();

    assertNull(knightWithoutShield.getShield());
  }
}

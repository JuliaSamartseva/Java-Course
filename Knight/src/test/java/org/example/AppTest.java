package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.example.equipment.BodyArmor;
import org.example.equipment.Equipment;
import org.example.equipment.Shield;
import org.example.equipment.weapon.Gun;
import org.example.equipment.weapon.Knife;
import org.example.equipment.weapon.QualityUpgrade;
import org.example.equipment.weapon.Weapon;
import org.junit.Test;

public class AppTest {
  @Test
  public void weaponUpgrades() {
    // GIVEN
    Weapon gunWeapon = new Gun();
    Weapon gunWeaponWithQualityUpgrade = new QualityUpgrade(new Gun());

    // THEN
    assertEquals(gunWeapon.getPrice().intValue(), 200);
    assertEquals(gunWeapon.getName(), "Gun");
    assertEquals(gunWeaponWithQualityUpgrade.getPrice().intValue(), 240);
    assertEquals(gunWeaponWithQualityUpgrade.getName(), "Gun, quality upgrade");
  }

  @Test
  public void knightOptionalShield() {
    // GIVEN
    Knight knightWithShield = Knight.builder()
        .order("Knights of the Cross")
        .weapon(new Gun())
        .shield(new Shield())
        .bodyArmor(new BodyArmor())
        .build();

    Knight knightWithoutShield = Knight.builder()
        .order("Order of Saint George")
        .weapon(new Gun())
        .bodyArmor(new BodyArmor())
        .build();

    // THEN
    assertEquals(knightWithShield.getOrder(), "Knights of the Cross");
    assertNotNull(knightWithShield.getShield());
    assertNull(knightWithoutShield.getShield());
  }

  @Test
  public void knightFullPrice() {
    // GIVEN
    Weapon knifeWeapon = new Knife();
    Shield shield = new Shield();

    // WHEN
    knifeWeapon.setPrice(100);
    shield.setPrice(50);
    Knight knight= Knight.builder()
        .order("Order of Saint George")
        .weapon(knifeWeapon)
        .shield(shield)
        .bodyArmor(new BodyArmor())
        .build();

    // THEN
    assertEquals(knight.getOrder(), "Order of Saint George");
    assertEquals(knight.getTotalCost().intValue(), 150);
  }

  @Test
  public void sortedEquipmentByWeight() {
    // GIVEN
    Weapon gunWeapon = new Gun();
    Shield shield = new Shield();
    BodyArmor armor = new BodyArmor();

    // WHEN
    gunWeapon.setWeight(100);
    shield.setWeight(50);
    armor.setWeight(20);
    Knight knight= Knight.builder()
        .order("Order of Saint George")
        .weapon(gunWeapon)
        .shield(shield)
        .bodyArmor(new BodyArmor())
        .build();

    ArrayList<Equipment> sortedEquipment = knight.getSortedEquipmentByWeight();

    // THEN
    assertTrue(isSortedByWeight(sortedEquipment));
  }

  @Test
  public void equipmentWithinPriceRange() {
    // GIVEN
    Weapon gunWeapon = new Gun();
    Shield shield = new Shield();
    BodyArmor armor = new BodyArmor();

    // WHEN
    gunWeapon.setPrice(200);
    shield.setPrice(60);
    armor.setPrice(140);
    Knight knight= Knight.builder()
        .order("Order of Saint George")
        .weapon(gunWeapon)
        .shield(shield)
        .bodyArmor(armor)
        .build();

    ArrayList<Equipment> equipmentWithinPriceRange = knight.getEquipmentWithinPriceRange(100, 150);

    // THEN
    assertTrue(equipmentWithinPriceRange.contains(armor));
  }

  public static boolean isSortedByWeight(ArrayList<Equipment> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).getWeight() > list.get(i + 1).getWeight()) {
        return false;
      }
    }
    return true;
  }
}

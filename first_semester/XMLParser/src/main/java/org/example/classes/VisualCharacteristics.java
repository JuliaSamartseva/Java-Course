package org.example.classes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"length", "width", "bladeMaterial", "handleMaterial", "hasBloodStream"})
public class VisualCharacteristics implements Serializable {

  private static final long serialVersionUID = 1L;

  @XmlElement(required = true)
  protected Length length;

  @XmlElement(required = true)
  protected Width width;

  @XmlElement(required = true)
  protected String bladeMaterial;

  @XmlElement(required = true)
  protected String handleMaterial;

  protected boolean hasBloodStream;

  /**
   * Gets the value of the length property.
   *
   * @return possible object is {@link Length }
   */
  public Length getLength() {
    return length;
  }

  /**
   * Sets the value of the length property.
   *
   * @param value allowed object is {@link Length }
   */
  public void setLength(Length value) {
    this.length = value;
  }

  /**
   * Gets the value of the width property.
   *
   * @return possible object is {@link Width }
   */
  public Width getWidth() {
    return width;
  }

  /**
   * Sets the value of the width property.
   *
   * @param value allowed object is {@link com.common.dto.Knives.Knife.VisualCharacteristics.Width }
   */
  public void setWidth(Width value) {
    this.width = value;
  }

  /**
   * Gets the value of the bladeMaterial property.
   *
   * @return possible object is {@link String }
   */
  public String getBladeMaterial() {
    return bladeMaterial;
  }

  /**
   * Sets the value of the bladeMaterial property.
   *
   * @param value allowed object is {@link String }
   */
  public void setBladeMaterial(String value) {
    this.bladeMaterial = value;
  }

  /**
   * Gets the value of the handleMaterial property.
   *
   * @return possible object is {@link String }
   */
  public String getHandleMaterial() {
    return handleMaterial;
  }

  /**
   * Sets the value of the handleMaterial property.
   *
   * @param value allowed object is {@link String }
   */
  public void setHandleMaterial(String value) {
    this.handleMaterial = value;
  }

  /** Gets the value of the hasBloodStream property. */
  public boolean isHasBloodStream() {
    return hasBloodStream;
  }

  /** Sets the value of the hasBloodStream property. */
  public void setHasBloodStream(boolean value) {
    this.hasBloodStream = value;
  }

  @Override
  public String toString() {
    return "\n  Length: "
        + length.getValue()
        + length.getUnit()
        + "\n  Width: "
        + width.getValue()
        + width.getUnit()
        + "\n  Blade material: "
        + bladeMaterial
        + "\n  Handle material: "
        + handleMaterial
        + "\n  Blood stream: "
        + hasBloodStream;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
      name = "",
      propOrder = {"value"})
  public static class Length implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlValue protected int value;

    @XmlAttribute(name = "unit")
    protected String unit;

    /** Gets the value of the value property. */
    public int getValue() {
      return value;
    }

    /** Sets the value of the value property. */
    public void setValue(int value) {
      this.value = value;
    }

    /**
     * Gets the value of the unit property.
     *
     * @return possible object is {@link String }
     */
    public String getUnit() {
      if (unit == null) {
        return "cm";
      } else {
        return unit;
      }
    }

    /**
     * Sets the value of the unit property.
     *
     * @param value allowed object is {@link String }
     */
    public void setUnit(String value) {
      this.unit = value;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
      name = "",
      propOrder = {"value"})
  public static class Width implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlValue protected int value;

    @XmlAttribute(name = "unit")
    protected String unit;

    /** Gets the value of the value property. */
    public int getValue() {
      return value;
    }

    /** Sets the value of the value property. */
    public void setValue(int value) {
      this.value = value;
    }

    /**
     * Gets the value of the unit property.
     *
     * @return possible object is {@link String }
     */
    public String getUnit() {
      if (unit == null) {
        return "mm";
      } else {
        return unit;
      }
    }

    /**
     * Sets the value of the unit property.
     *
     * @param value allowed object is {@link String }
     */
    public void setUnit(String value) {
      this.unit = value;
    }
  }
}

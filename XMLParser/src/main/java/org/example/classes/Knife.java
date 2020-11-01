package org.example.classes;

import com.common.dto.Knives;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"type", "handy", "origin", "visualCharacteristics", "isValuable"})
public class Knife implements Serializable {

  private static final long serialVersionUID = 1L;

  @XmlElement(required = true)
  protected String type;

  @XmlElement(required = true)
  protected String handy;

  @XmlElement(required = true)
  protected String origin;

  @XmlElement(required = true)
  protected com.common.dto.Knives.Knife.VisualCharacteristics visualCharacteristics;

  protected boolean isValuable;

  @XmlAttribute(name = "id", required = true)
  @XmlSchemaType(name = "positiveInteger")
  protected BigInteger id;

  /**
   * Gets the value of the type property.
   *
   * @return possible object is {@link String }
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is {@link String }
   */
  public void setType(String value) {
    this.type = value;
  }

  /**
   * Gets the value of the handy property.
   *
   * @return possible object is {@link String }
   */
  public String getHandy() {
    return handy;
  }

  /**
   * Sets the value of the handy property.
   *
   * @param value allowed object is {@link String }
   */
  public void setHandy(String value) {
    this.handy = value;
  }

  /**
   * Gets the value of the origin property.
   *
   * @return possible object is {@link String }
   */
  public String getOrigin() {
    return origin;
  }

  /**
   * Sets the value of the origin property.
   *
   * @param value allowed object is {@link String }
   */
  public void setOrigin(String value) {
    this.origin = value;
  }

  /**
   * Gets the value of the visualCharacteristics property.
   *
   * @return possible object is {@link com.common.dto.Knives.Knife.VisualCharacteristics }
   */
  public com.common.dto.Knives.Knife.VisualCharacteristics getVisualCharacteristics() {
    return visualCharacteristics;
  }

  /**
   * Sets the value of the visualCharacteristics property.
   *
   * @param value allowed object is {@link com.common.dto.Knives.Knife.VisualCharacteristics }
   */
  public void setVisualCharacteristics(com.common.dto.Knives.Knife.VisualCharacteristics value) {
    this.visualCharacteristics = value;
  }

  /** Gets the value of the isValuable property. */
  public boolean isIsValuable() {
    return isValuable;
  }

  /** Sets the value of the isValuable property. */
  public void setIsValuable(boolean value) {
    this.isValuable = value;
  }

  /**
   * Gets the value of the id property.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setId(BigInteger value) {
    this.id = value;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(
      name = "",
      propOrder = {"length", "width", "bladeMaterial", "handleMaterial", "hasBloodStream"})
  public static class VisualCharacteristics implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement(required = true)
    protected com.common.dto.Knives.Knife.VisualCharacteristics.Length length;

    @XmlElement(required = true)
    protected com.common.dto.Knives.Knife.VisualCharacteristics.Width width;

    @XmlElement(required = true)
    protected String bladeMaterial;

    @XmlElement(required = true)
    protected String handleMaterial;

    protected boolean hasBloodStream;

    /**
     * Gets the value of the length property.
     *
     * @return possible object is {@link com.common.dto.Knives.Knife.VisualCharacteristics.Length }
     */
    public com.common.dto.Knives.Knife.VisualCharacteristics.Length getLength() {
      return length;
    }

    /**
     * Sets the value of the length property.
     *
     * @param value allowed object is {@link
     *     com.common.dto.Knives.Knife.VisualCharacteristics.Length }
     */
    public void setLength(com.common.dto.Knives.Knife.VisualCharacteristics.Length value) {
      this.length = value;
    }

    /**
     * Gets the value of the width property.
     *
     * @return possible object is {@link com.common.dto.Knives.Knife.VisualCharacteristics.Width }
     */
    public com.common.dto.Knives.Knife.VisualCharacteristics.Width getWidth() {
      return width;
    }

    /**
     * Sets the value of the width property.
     *
     * @param value allowed object is {@link com.common.dto.Knives.Knife.VisualCharacteristics.Width
     *     }
     */
    public void setWidth(Knives.Knife.VisualCharacteristics.Width value) {
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
}

package org.example.classes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

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
  protected VisualCharacteristics visualCharacteristics;

  protected boolean isValuable;

  @XmlAttribute(name = "id", required = true)
  @XmlSchemaType(name = "positiveInteger")
  protected Integer id;

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
   * @return possible object is {@link VisualCharacteristics }
   */
  public VisualCharacteristics getVisualCharacteristics() {
    return visualCharacteristics;
  }

  /**
   * Sets the value of the visualCharacteristics property.
   *
   * @param value allowed object is {@link VisualCharacteristics }
   */
  public void setVisualCharacteristics(VisualCharacteristics value) {
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
   * @return possible object is {@link Integer }
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   * @param value allowed object is {@link Integer }
   */
  public void setId(Integer value) {
    this.id = value;
  }

  @Override
  public String toString() {
    return "\n\nType: "
        + type
        + "\nId: "
        + id
        + "\nHandy: "
        + handy
        + "\nOrigin: "
        + origin
        + "\nVisual characteristics:"
        + visualCharacteristics
        + "\nValuable: "
        + isValuable;
  }
}

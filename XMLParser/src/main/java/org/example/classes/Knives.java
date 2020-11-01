package org.example.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "",
    propOrder = {"knife"})
@XmlRootElement(name = "knives")
public class Knives implements Serializable {

  private static final long serialVersionUID = 1L;

  @XmlElement(required = true)
  protected List<Knife> knife;

  /**
   * Gets the value of the knife property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the knife property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   *    getKnife().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link Knife }
   */
  public List<Knife> getKnife() {
    if (knife == null) {
      knife = new ArrayList<Knife>();
    }
    return this.knife;
  }
}

package org.example.parsers;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.example.classes.Knife;
import org.example.classes.Knives;
import org.example.classes.VisualCharacteristics;
import org.example.classes.VisualCharacteristics.Length;
import org.example.classes.VisualCharacteristics.Width;
import org.example.validator.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CustomDOMParser {
  private static final String KNIFE = "knife";
  private static final String TYPE = "type";
  private static final String HANDY = "handy";
  private static final String ORIGIN = "origin";
  private static final String VISUALCHARACTERISTICS = "visualCharacteristics";
  private static final String LENGTH = "length";
  private static final String WIDTH = "width";
  private static final String BLADEMATERIAL = "bladeMaterial";
  private static final String HANDLEMATERIAL = "handleMaterial";
  private static final String BLOODSTREAM = "hasBloodStream";
  private static final String VALUABLE = "isValuable";

  public static Knives parseXML(String xsdPath, String xmlPath) {
    /// Validating the XML firstly by XSD schema.
    Validator.validateXMLSchema(xsdPath, xmlPath);
    Knives knives = new Knives();

    try {
      File inputFile = new File(xmlPath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      NodeList nList = doc.getElementsByTagName("knife");
      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
        Knife knife = new Knife();
        VisualCharacteristics visualCharacteristics = new VisualCharacteristics();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          knife.setId(Integer.parseInt(eElement.getAttribute("id")));

          knife.setType(eElement
              .getElementsByTagName(TYPE)
              .item(0)
              .getTextContent());

          knife.setHandy(eElement
              .getElementsByTagName(HANDY)
              .item(0)
              .getTextContent());

          knife.setOrigin(eElement
              .getElementsByTagName(ORIGIN)
              .item(0)
              .getTextContent());

          Length length = new Length();
          length.setValue(Integer.parseInt(eElement
              .getElementsByTagName(LENGTH)
              .item(0)
              .getTextContent()));
          visualCharacteristics.setLength(length);

          Width width = new Width();
          width.setValue(Integer.parseInt(eElement
              .getElementsByTagName(WIDTH)
              .item(0)
              .getTextContent()));
          visualCharacteristics.setWidth(width);

          visualCharacteristics.setBladeMaterial(eElement
              .getElementsByTagName(BLADEMATERIAL)
              .item(0)
              .getTextContent());

          visualCharacteristics.setHandleMaterial(eElement
              .getElementsByTagName(HANDLEMATERIAL)
              .item(0)
              .getTextContent());

          visualCharacteristics.setHasBloodStream(Boolean.parseBoolean(eElement
              .getElementsByTagName(BLOODSTREAM)
              .item(0)
              .getTextContent()));

          knife.setIsValuable(Boolean.parseBoolean(eElement
              .getElementsByTagName(VALUABLE)
              .item(0)
              .getTextContent()));

          knife.setVisualCharacteristics(visualCharacteristics);
        }
        knives.getKnives().add(knife);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return knives;
  }
}

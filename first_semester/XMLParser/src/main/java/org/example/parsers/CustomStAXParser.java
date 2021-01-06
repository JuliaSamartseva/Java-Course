package org.example.parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.example.classes.Knife;
import org.example.classes.Knives;
import org.example.classes.VisualCharacteristics;
import org.example.classes.VisualCharacteristics.Length;
import org.example.classes.VisualCharacteristics.Width;

public final class CustomStAXParser {
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

  private static Knives knives;
  private static Knife knife;
  private static VisualCharacteristics visualCharacteristics;

  private static String elementValue = null;

  public static Knives parseXML(String xsdPath, String xmlPath)
      throws XMLStreamException, FileNotFoundException {
    Knives knives = new Knives();
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(xmlPath));
    while (reader.hasNext()) {
      int event = reader.next();
      // TODO: get id attribute

      switch (event) {
        case XMLStreamConstants.START_ELEMENT:
          switch (reader.getLocalName()) {
            case KNIFE:
              knife = new Knife();
              knife.setId(Integer.valueOf(reader.getAttributeValue(0)));
              break;
            case VISUALCHARACTERISTICS:
              visualCharacteristics = new VisualCharacteristics();
              break;
          }
          break;

        case XMLStreamConstants.CHARACTERS:
          elementValue = reader.getText().trim();
          break;

        case XMLStreamConstants.END_ELEMENT:
          switch (reader.getLocalName()) {
            case KNIFE:
              knives.getKnives().add(knife);
              break;
            case TYPE:
              knife.setType(elementValue);
              break;
            case HANDY:
              knife.setHandy(elementValue);
              break;
            case ORIGIN:
              knife.setOrigin(elementValue);
              break;
            case VISUALCHARACTERISTICS:
              knife.setVisualCharacteristics(visualCharacteristics);
              break;
            case LENGTH:
              Length length = new Length();
              length.setValue(Integer.parseInt(elementValue));
              visualCharacteristics.setLength(length);
              break;
            case WIDTH:
              Width width = new Width();
              width.setValue(Integer.parseInt(elementValue));
              visualCharacteristics.setWidth(width);
              break;
            case BLADEMATERIAL:
              visualCharacteristics.setBladeMaterial(elementValue);
              break;
            case HANDLEMATERIAL:
              visualCharacteristics.setHandleMaterial(elementValue);
              break;
            case BLOODSTREAM:
              visualCharacteristics.setHasBloodStream(Boolean.parseBoolean(elementValue));
              break;
            case VALUABLE:
              knife.setIsValuable(Boolean.parseBoolean(elementValue));
              break;
          }
          break;

        case XMLStreamConstants.START_DOCUMENT:
          knives = new Knives();
          break;
      }
    }

    return knives;
  }
}

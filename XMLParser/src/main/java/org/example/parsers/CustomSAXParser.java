package org.example.parsers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.example.classes.Knife;
import org.example.classes.Knives;
import org.example.classes.VisualCharacteristics;
import org.example.classes.VisualCharacteristics.Length;
import org.example.classes.VisualCharacteristics.Width;
import org.example.validator.Validator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class CustomSAXParser {

  public static Knives parseXML(String xsdPath, String xmlPath)
      throws ParserConfigurationException, SAXException, IOException {
    /// Validating the XML firstly by XSD schema.
    Validator.validateXMLSchema(xsdPath, xmlPath);

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser = factory.newSAXParser();

    XMLHandler handler = new XMLHandler();
    parser.parse(new File(xmlPath), handler);

    return handler.getKnives();
  }

  private static class XMLHandler extends DefaultHandler {
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

    private Knives knives;
    private Knife knife;
    private VisualCharacteristics visualCharacteristics;

    private String elementValue;

    public Knives getKnives() {
      return knives;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
      knives = new Knives();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr)
        throws SAXException {
      switch (qName) {
        case KNIFE:
          knife = new Knife();
          knife.setId(Integer.valueOf(attr.getValue("id")));
          break;
        case VISUALCHARACTERISTICS:
          visualCharacteristics = new VisualCharacteristics();
          break;
      }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      switch (qName) {
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
    }
  }
}

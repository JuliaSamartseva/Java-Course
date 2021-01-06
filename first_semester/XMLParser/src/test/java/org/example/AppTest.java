package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.example.classes.Knives;
import org.example.parsers.CustomDOMParser;
import org.example.parsers.CustomSAXParser;
import org.example.parsers.CustomStAXParser;
import org.example.validator.Validator;
import org.junit.Test;
import org.xml.sax.SAXException;

public class AppTest {
  @Test
  public void testValidator() {
    assertTrue(Validator.validateXMLSchema(
            "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml"));
    assertFalse(Validator.validateXMLSchema(
        "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\KnifeBroken.xml"));
  }


  @Test
  public void testSAXParserWithKnives() throws IOException, SAXException, ParserConfigurationException {
    Knives result =
        CustomSAXParser.parseXML(
            "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml");
    testKnifeResult(result);
  }

  @Test
  public void testStAXParserWithKnives()
      throws IOException, XMLStreamException {
    Knives result =
        CustomStAXParser.parseXML(
            "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml");
    testKnifeResult(result);
  }

  @Test
  public void testDOMParserWithKnives() {
    Knives result =
        CustomDOMParser.parseXML(
            "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml");
    testKnifeResult(result);
  }

  void testKnifeResult(Knives result) {
    assertEquals(result.getKnives().size(), 7);
    assertEquals(result.getKnives().get(0).getId().intValue(), 1);
    assertEquals(result.getKnives().get(0).getHandy(), "one");
    assertEquals(result.getKnives().get(0).getOrigin(), "USA");
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().getBladeMaterial(), "steel");
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().getHandleMaterial(), "wood");
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().isHasBloodStream(), true);
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().getWidth().getValue(), 10);
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().getLength().getValue(), 18);
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().getWidth().getUnit(), "mm");
    assertEquals(result.getKnives().get(0).getVisualCharacteristics().getLength().getUnit(), "cm");

  }
}

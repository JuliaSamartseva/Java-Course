package org.example;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.example.classes.Knives;
import org.example.parsers.CustomDOMParser;
import org.example.parsers.CustomSAXParser;
import org.example.parsers.CustomStAXParser;
import org.example.validator.Validator;
import org.xml.sax.SAXException;

public class App {
  public static void main(String[] args)
      throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
    //System.out.println(
    //    Validator.validateXMLSchema(
    //        "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml"));
    Knives result = CustomStAXParser
        .parseXML("src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml");
    System.out.println(result);
  }
}

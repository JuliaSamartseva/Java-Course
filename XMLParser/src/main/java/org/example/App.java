package org.example;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.example.classes.Knives;
import org.example.parsers.CustomStAXParser;

public class App {
  public static void main(String[] args) throws IOException, XMLStreamException {
    Knives result =
        CustomStAXParser.parseXML(
            "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml");
    System.out.println(result);
  }
}

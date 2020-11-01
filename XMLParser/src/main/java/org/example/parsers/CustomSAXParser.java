package org.example.parsers;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.example.validator.Validator;
import org.xml.sax.SAXException;

public final class CustomSAXParser {

  public static void parseXML(String xsdPath, String xmlPath)
      throws ParserConfigurationException, SAXException, IOException {
    /// Validating the XML firstly by XSD schema.
    Validator.validateXMLSchema(xsdPath, xmlPath);

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    //    XMLHandler handler = new XMLHandler();
    //    saxParser.parse(new File(xmlPath), handler);
  }

  //  private static class XMLHandler extends DefaultHandler {
  //    private String name, job, lastElementName;
  //
  //    @Override
  //    public void startElement(String uri, String localName, String qName, Attributes attributes)
  // throws SAXException {
  //      lastElementName = qName;
  //    }
  //
  //    @Override
  //    public void characters(char[] ch, int start, int length) throws SAXException {
  //      String information = new String(ch, start, length);
  //
  //      information = information.replace("\n", "").trim();
  //
  //      if (!information.isEmpty()) {
  //        if (lastElementName.equals("name"))
  //          name = information;
  //        if (lastElementName.equals("job"))
  //          job = information;
  //      }
  //    }
  //
  //    @Override
  //    public void endElement(String uri, String localName, String qName) throws SAXException {
  //      if ( (name != null && !name.isEmpty()) && (job != null && !job.isEmpty()) ) {
  //        employees.add(new Employee(name, job));
  //        name = null;
  //        job = null;
  //      }
  //    }
  //  }
}

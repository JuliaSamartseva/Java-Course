package org.example;

import org.example.validator.Validator;

public class App {
  public static void main(String[] args) {
    System.out.println(
        Validator.validateXMLSchema(
            "src\\main\\resources\\Knife.xsd", "src\\main\\resources\\Knife.xml"));
  }
}

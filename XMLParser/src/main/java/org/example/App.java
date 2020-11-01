package org.example;

import org.example.validator.Validator;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println(Validator.validateXMLSchema("src\\Knife.xsd","src\\Knife.xml"));
    }
}

package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassDescription {
  Class cls;

  public ClassDescription(Class cls) {
    this.cls = cls;
  }

  public void printDescription() {
    printFieldsInformation();
    printConstructorsInformation();
    printMethodsInformation();
    printInterfacesInformation();
  }

  public void printConstructorsInformation() {
    System.out.println("\nConstructors of the class:");
    for (Constructor constructor : cls.getDeclaredConstructors()) {
      System.out.println(constructor);
    }
  }

  public void printMethodsInformation() {
    System.out.println("\nMethods of the class:");
    for (Method method : cls.getDeclaredMethods()) {
      System.out.println(method);
    }
  }

  public void printFieldsInformation() {
    System.out.println("\nDeclared fields of the class:");

    for (Field field : cls.getDeclaredFields()) {
      System.out.println(field);
    }
  }

  public void printInterfacesInformation() {
    System.out.println("\nInterfaces of the class:");
    for (Class iface : cls.getInterfaces()) {
      System.out.println(iface);
    }
  }
}

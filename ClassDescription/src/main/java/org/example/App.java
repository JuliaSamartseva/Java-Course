package org.example;

import java.util.logging.LogManager;
import org.example.loader.CustomClassLoader;

public class App {
  public static void main(String[] args) throws ClassNotFoundException {
    LogManager.getLogManager().reset();

    CustomClassLoader loader = new CustomClassLoader(App.class.getClassLoader());
    Class testClass = loader.loadClass("org.example.SampleClass");
    ClassDescription description = new ClassDescription(testClass);
    description.printDescription();
  }
}

package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

class SampleClass extends ArrayList {
  public int variable;
  private final String finalVariable;

  public SampleClass(int variable, String finalVariable) {
    this.variable = variable;
    this.finalVariable = finalVariable;
  }

  public String getFinalVariable() {
    return finalVariable;
  }

  private void exampleFunction() {
    System.out.println("Hello world");
  }

  public <T> T sampleGenericFunction(T type) throws IllegalAccessException {
    System.out.println(type);
    return type;
  }

  public <T extends RuntimeException> void exceptionExample() throws T {}

  public int getVariable() {
    return variable;
  }

  public void setVariable(int variable) {
    this.variable = variable;
  }
}

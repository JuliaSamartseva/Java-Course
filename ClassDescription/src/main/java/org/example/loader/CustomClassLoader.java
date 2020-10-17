package org.example.loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }

  public Class loadClass(String name) throws ClassNotFoundException {
    System.out.println("Class Loading Started for " + name);
    return this.getClass(name);
  }

  private Class getClass(String name) throws ClassNotFoundException {
    String file = name.replace('.', File.separatorChar) + ".class";
    System.out.println("Name of File" + file);
    Object var3 = null;

    try {
      byte[] byteArr = this.loadClassData(file);
      System.out.println("Size of byte array " + byteArr.length);
      Class c = this.defineClass(name, byteArr, 0, byteArr.length);
      this.resolveClass(c);
      return c;
    } catch (IOException var5) {
      var5.printStackTrace();
      return null;
    }
  }

  private byte[] loadClassData(String name) throws IOException {
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream(name);
    if (stream != null) {
      int size = stream.available();
      byte[] buff = new byte[size];
      DataInputStream in = new DataInputStream(stream);
      in.readFully(buff);
      in.close();
      return buff;
    } else {
      return null;
    }
  }
}

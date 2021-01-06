package org.example.loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.example.App;

public class CustomClassLoader extends ClassLoader {
  private static Logger log = Logger.getLogger(CustomClassLoader.class.getName());

  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }

  public Class loadClass(String name) throws ClassNotFoundException {
    log.info("Class loading started for " + name);
    return getClass(name);
  }

  private Class getClass(String name) throws ClassNotFoundException {
    String file = name.replace('.', File.separatorChar) + ".class";
    log.info("Name of loaded class: " + file);
    if (file.startsWith("java"))
      return App.class.getClassLoader().loadClass(name);

    try {
      byte[] byteArr = loadClassData(file);
      log.info("Size of byte array " + byteArr.length);
      Class c = defineClass(name, byteArr, 0, byteArr.length);
      resolveClass(c);
      return c;
    } catch (IOException e) {
      e.printStackTrace();
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

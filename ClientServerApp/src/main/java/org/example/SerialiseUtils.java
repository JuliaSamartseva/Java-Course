package org.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

public class SerialiseUtils {
  private static Logger log = Logger.getLogger(SerialiseUtils.class.getName());

  public static byte[] serialise(Serializable value) throws IOException {
    log.info("Serializing the object.");
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream outputStream = new ObjectOutputStream(out);
    outputStream.writeObject(value);
    return out.toByteArray();
  }

  public static Album deserialise(byte[] data) throws IOException, ClassNotFoundException {
    log.info("Deserializing the object.");
    ByteArrayInputStream bis = new ByteArrayInputStream(data);
    return (Album) new ObjectInputStream(bis).readObject();
  }
}

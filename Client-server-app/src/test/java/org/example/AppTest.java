package org.example;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest {
  static Server server;
  static Client client;

  @BeforeClass
  public static void startServerTest(){
    Thread serverThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          server = new Server();
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    });
    serverThread.start();
  }

  @Test
  public void serialization() throws IOException, ClassNotFoundException {
    Album album = new Album("Title", "Artist", 1990);
    byte[] serializedObject = SerialiseUtils.serialise(album);
    Album deserializedObject = SerialiseUtils.deserialise(serializedObject);
    assertEquals(deserializedObject, album);
  }

  @Test
  public void testServerConnection() throws IOException, InterruptedException {
    Album firstAlbum = new Album("First Title", "Artist", 1980);
    client = new Client();
    client.sendMessage(firstAlbum);
  }

}

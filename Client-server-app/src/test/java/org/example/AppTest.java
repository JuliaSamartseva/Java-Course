package org.example;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

  Server server;
  Client client;

  @Before
  public void startServerTest(){
    Thread serverThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          server = new Server();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    serverThread.start();
  }

  @Test
  public void shouldAnswerWithTrue() throws IOException {
    client = new Client();

  }
}

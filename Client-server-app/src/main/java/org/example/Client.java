package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Client {
  private static SocketChannel client;
  private static ByteBuffer buffer;
  private static Client instance;

  private GsonBuilder builder = new GsonBuilder();
  private Gson gson = builder.create();

  private static Logger log = Logger.getLogger(Server.class.getName());

  public Client() throws IOException {
    client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
    buffer = ByteBuffer.allocate(1024);
    log.info("The client is opened.");
  }

  public static Client start() throws IOException {
    if (instance == null)
      instance = new Client();

    return instance;
  }

  public void disconnect() throws IOException {
    client.close();
    buffer = null;
    System.out.println("Client connection closed");
  }

  public void sendMessage(String msg) throws IOException {

    buffer = ByteBuffer.wrap(msg.getBytes());
    String response = null;
    client.write(buffer);
    buffer.clear();
  }

}

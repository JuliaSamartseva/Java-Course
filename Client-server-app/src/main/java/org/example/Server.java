package org.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
  private ServerSocketChannel server;
  private static Selector selector = null;

  private static Logger log = Logger.getLogger(Server.class.getName());

  public Server() throws IOException {
    log.info("Starting the server.");
    selector = Selector.open();
    ServerSocketChannel socket = ServerSocketChannel.open();
    ServerSocket serverSocket = socket.socket();
    serverSocket.bind(new InetSocketAddress("localhost", 8089));
    socket.configureBlocking(false);
    socket.register(selector, socket.validOps(), null);
    while (true) {
      selector.select();
      Set<SelectionKey> selectedKeys = selector.selectedKeys();
      Iterator<SelectionKey> i = selectedKeys.iterator();

      while (i.hasNext()) {
        SelectionKey key = i.next();

        if (key.isAcceptable()) {
          handleAccept(socket, key);
        } else if (key.isReadable()) {
          handleRead(key);
        }
        i.remove();
      }
    }
  }

  private static void handleAccept(ServerSocketChannel mySocket,
      SelectionKey key) throws IOException {
    SocketChannel client = mySocket.accept();
    client.configureBlocking(false);
    client.register(selector, SelectionKey.OP_READ);
    log.info("Connection is accepted.");
  }

  private static void handleRead(SelectionKey key) throws IOException {
    log.info("Reading info.");
    SocketChannel client = (SocketChannel) key.channel();

    ByteBuffer buffer = ByteBuffer.allocate(1024);
    client.read(buffer);

    String data = new String(buffer.array()).trim();
    if (data.length() > 0) {
      log.info("Received data from client.");
      if (data.equalsIgnoreCase("exit")) {
        client.close();
        log.info("Closing connection.");
      }
    }
  }

}

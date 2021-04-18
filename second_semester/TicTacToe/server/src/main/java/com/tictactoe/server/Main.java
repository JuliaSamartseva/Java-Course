package com.tictactoe.server;

import com.tictactoe.core.gamelogic.TicTacToeGame;
import com.tictactoe.core.gamelogic.TicTacToeGameImpl;
import com.tictactoe.server.connector.JMSServerConnector;

public class Main {
  public static void main(String[] args) throws Exception {
    TicTacToeGame game = new TicTacToeGameImpl();
    JMSServerConnector jmsServerConnector =
        new JMSServerConnector("tcp://localhost:61617", new RequestProcessorImpl(game));
    Thread.sleep(1200 * 1000);
    jmsServerConnector.close();
  }
}

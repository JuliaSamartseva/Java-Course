package com.tictactoe.client.view.game;

import com.tictactoe.client.connector.JMSClientConnector;

import javax.jms.JMSException;
import javax.swing.*;

public class Game {
  private JLabel gameState;
  private JTextField XTextField;
  private JTextField YTextField;
  private final JMSClientConnector connector;

  public Game(JMSClientConnector connector) {
    this.connector = connector;
  }

  private void createAndShowGUI() {
    XTextField = new JTextField();
    YTextField = new JTextField();
    gameState = new JLabel("Game started");
    JLabel XLabel = new JLabel("X: ");
    JLabel YLabel = new JLabel("Y: ");
    JButton moveButton = new JButton("Move");
    JButton getStateButton = new JButton("Get state");

    // Configure elements
    moveButton.addActionListener(actionEvent -> move());
    getStateButton.addActionListener(actionEvent -> {
      try {
        updateState();
      } catch (JMSException e) {
        e.printStackTrace();
      }
    });

    // Create and set up the window
    JFrame frame = new JFrame("Game view");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    GroupLayout layout = new GroupLayout(frame.getContentPane());
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    frame.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout
            .createSequentialGroup()
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(XLabel)
                    .addComponent(YLabel)
                    .addComponent(moveButton)
                    .addComponent(gameState)
                    .addComponent(getStateButton)
            )
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(YTextField, 100, 150, 200)
                    .addComponent(XTextField, 100, 150, 200)));
    layout.setVerticalGroup(
        layout
            .createSequentialGroup()
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(XLabel)
                    .addComponent(
                        XTextField,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(YLabel)
                    .addComponent(
                        YTextField,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
            .addComponent(moveButton)
            .addComponent(gameState)
            .addComponent(getStateButton)
    );

    // Display the window
    frame.pack();
    frame.setVisible(true);
  }

  private void updateState() throws JMSException {
    switch (connector.getState()) {
      case OPPONENTS_TURN:
        gameState.setText("Opponents turn");
        break;
      case WON:
        gameState.setText("Won");
        break;
      case LOST:
        gameState.setText("Lost");
        break;
      case YOUR_TURN:
        gameState.setText("Your turn");
        break;
      case WAITING_FOR_SECOND_PLAYER:
        gameState.setText("Waiting for the second player");
        break;
    }
  }

  private void move() {
    int x = Integer.parseInt(XTextField.getText());
    int y = Integer.parseInt(YTextField.getText());
    try {
      if (x >= 0 && x < 3 && y >= 0 && y < 3) {
        connector.mark(x, y);
        XTextField.setText("");
        YTextField.setText("");
      }
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  public void show() {
    SwingUtilities.invokeLater(this::createAndShowGUI);
  }
}

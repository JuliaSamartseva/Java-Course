package com.tictactoe.client.view.main;

import com.tictactoe.client.connector.ClientConnector;
import com.tictactoe.client.connector.JMSClientConnector;
import com.tictactoe.client.view.game.Game;

import javax.jms.JMSException;
import javax.swing.*;

public class MainMenu {
  private JTextField loginTextField;
  private JTextField serverAddressTextField;

  private void createAndShowGUI() {
    loginTextField = new JTextField();
    serverAddressTextField = new JTextField();
    JLabel loginLabel = new JLabel("Login");
    JLabel serverAddressLabel = new JLabel("Server address");
    JButton connectButton = new JButton("Connect");

    // Configure elements
    connectButton.addActionListener(actionEvent -> connectButtonActionPerformed());

    // Create and set up the window
    JFrame frame = new JFrame("Main menu");
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
                    .addComponent(loginLabel)
                    .addComponent(serverAddressLabel)
                    .addComponent(connectButton))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(serverAddressTextField, 100, 150, 200)
                    .addComponent(loginTextField, 100, 150, 200)));
    layout.setVerticalGroup(
        layout
            .createSequentialGroup()
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(
                        loginTextField,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(serverAddressLabel)
                    .addComponent(
                        serverAddressTextField,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
            .addComponent(connectButton));

    // Display the window
    frame.pack();
    frame.setVisible(true);
  }

  private void connectButtonActionPerformed() {
    try {
      JMSClientConnector clientConnector = new JMSClientConnector(serverAddressTextField.getText(), loginTextField.getText());
      new Game(clientConnector).show();
    } catch (JMSException e) {
      e.printStackTrace();
    }


  }

  public void show() {
    SwingUtilities.invokeLater(this::createAndShowGUI);
  }
}

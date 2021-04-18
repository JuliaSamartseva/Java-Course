package com.tictactoe.client.connector;

import com.tictactoe.core.beans.requests.TTTConnectionRequest;
import com.tictactoe.core.beans.requests.TTTMarkRequest;
import com.tictactoe.core.beans.requests.TTTRequest;
import com.tictactoe.core.beans.requests.TTTStateRequest;
import com.tictactoe.core.beans.responses.TTTResponse;
import com.tictactoe.core.beans.responses.TTTStateResponse;
import com.tictactoe.core.utils.TTTUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

public class JMSClientConnector implements ClientConnector {

  private static final long REPLY_MAX_WAIT = 5000;

  private final Connection connection;
  private final Session session;
  private final MessageProducer producer;
  private final MessageConsumer replyConsumer;
  private final String login;
  private final TemporaryQueue replyQueue;

  public JMSClientConnector(String uri, String login) throws JMSException {
    this.login = login;
    ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(uri);
    connFactory.setTrustedPackages(Collections.singletonList("com.tictactoe"));
    connection = connFactory.createConnection();
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    replyQueue = session.createTemporaryQueue();
    producer = session.createProducer(session.createQueue(TTTUtils.QUEUE_NAME));
    replyConsumer = session.createConsumer(replyQueue);
    connection.start();
    sendRequest(new TTTConnectionRequest(login));
  }

  @Override
  public void close() throws IOException {
    try {
      connection.stop();
      producer.close();
      replyConsumer.close();
      replyQueue.delete();
      session.close();
      connection.close();
    } catch (JMSException e) {
      throw new IOException(e.getMessage());
    }
  }

  @Override
  public void mark(int x, int y) throws JMSException {
    TTTResponse response = sendRequest(new TTTMarkRequest(login, x, y));
    if (response.getStatus() == TTTResponse.Status.ERROR) {
      throw new JMSException("Mark request error");
    }
  }

  @Override
  public TTTStateResponse.PlayerState getState() throws JMSException {
    TTTStateResponse response = (TTTStateResponse) sendRequest(new TTTStateRequest(login));
    if (response.getStatus() != TTTResponse.Status.OK) {
      throw new JMSException("State request error");
    }

    return response.getPlayerState();
  }

  private String generateJMSCorrelationID(String login) {
    return login + new Random().nextInt();
  }

  private TTTResponse sendRequest(TTTRequest request) throws JMSException {
    ObjectMessage message = new ActiveMQObjectMessage();
    message.setJMSReplyTo(replyQueue);
    message.setJMSCorrelationID(generateJMSCorrelationID(login));
    message.setObject(request);
    producer.send(message);
    Message replyMessage = replyConsumer.receive(REPLY_MAX_WAIT);
    if (!(replyMessage instanceof ObjectMessage)
        || !(((ObjectMessage) replyMessage).getObject() instanceof TTTResponse)) {
      throw new JMSException("Received incorrect reply");
    }
    return (TTTResponse) ((ObjectMessage) replyMessage).getObject();
  }
}

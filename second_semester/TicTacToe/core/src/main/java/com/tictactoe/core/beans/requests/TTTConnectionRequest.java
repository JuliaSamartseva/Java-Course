package com.tictactoe.core.beans.requests;

public class TTTConnectionRequest extends TTTRequest {

  public TTTConnectionRequest(String login) {
    super(login);
    this.requestType = RequestType.CONNECTION;
  }
}

package com.tictactoe.core.beans.requests;

public class TTTStateRequest extends TTTRequest {
  public TTTStateRequest(String login) {
    super(login);
    this.requestType = RequestType.STATE;
  }
}

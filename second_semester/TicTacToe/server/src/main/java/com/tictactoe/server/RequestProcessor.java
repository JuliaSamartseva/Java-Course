package com.tictactoe.server;

import com.tictactoe.core.beans.requests.TTTRequest;
import com.tictactoe.core.beans.responses.TTTResponse;

public interface RequestProcessor {
  TTTResponse processRequest(TTTRequest request);
}

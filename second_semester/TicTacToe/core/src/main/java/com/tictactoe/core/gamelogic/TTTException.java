package com.tictactoe.core.gamelogic;

public class TTTException extends Exception {

  public static String SQUARE_NOT_EXISTS = "The spot you want to mark does not exist";
  static String WRONG_TURN = "Trying to mark on someone else's turn";
  static String ALREADY_MARKED = "The spot you want to mark is already marked";
  static String GAME_ENDED = "The game has already ended";

  public TTTException(String message) {
    super(message);
  }
}

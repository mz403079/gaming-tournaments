package com.example.gameorgbackend.exceptions;

public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException() {
    super("Data not found");
  }
}



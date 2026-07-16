package com.adx.ReadingLog.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() { super("Usuário já cadastrado"); }

    public UserAlreadyExistsException(String message) { super(message); }

}

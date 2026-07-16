package com.adx.ReadingLog.exceptions;

public class BookException extends RuntimeException {

    public BookException() { super("Livro não encontrado"); }

    public BookException(String message) {
        super(message);
    }

}

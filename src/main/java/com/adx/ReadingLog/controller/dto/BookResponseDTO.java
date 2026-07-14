package com.adx.ReadingLog.controller.dto;

import com.adx.ReadingLog.model.Book;

import java.util.UUID;

public record BookResponseDTO (
    UUID uuid,
    String title,
    String description,
    boolean completed
){

    public BookResponseDTO(Book book){
        this(book.getId(), book.getTitle(), book.getDescription(), book.isCompleted());
    }

}

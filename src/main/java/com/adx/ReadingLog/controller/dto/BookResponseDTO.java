package com.adx.ReadingLog.controller.dto;

import com.adx.ReadingLog.model.Book;

import java.util.UUID;

public record BookResponseDTO (
    UUID uuid,
    String title,
    String author,
    String description,
    String imgUrl,
    boolean completed
){

    public BookResponseDTO(Book book){
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getImgUrl(), book.isCompleted());
    }

}

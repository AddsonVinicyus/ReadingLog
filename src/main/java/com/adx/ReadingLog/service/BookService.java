package com.adx.ReadingLog.service;

import com.adx.ReadingLog.controller.dto.BookRequestDTO;
import com.adx.ReadingLog.controller.dto.BookResponseDTO;
import com.adx.ReadingLog.model.Book;
import com.adx.ReadingLog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<BookResponseDTO> getAllBooks(){
        return repository.findAll().stream().map(BookResponseDTO::new).toList();
    }


    public void addBook(BookRequestDTO bookDTO) {
        Book book = new Book(bookDTO);
        repository.save(book);
    }
}

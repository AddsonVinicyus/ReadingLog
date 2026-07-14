package com.adx.ReadingLog.controller;


import com.adx.ReadingLog.controller.dto.BookRequestDTO;
import com.adx.ReadingLog.controller.dto.BookResponseDTO;
import com.adx.ReadingLog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getBooks(){
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody BookRequestDTO book){
        service.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

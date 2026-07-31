package com.adx.ReadingLog.controller;


import com.adx.ReadingLog.controller.dto.BookRequestDTO;
import com.adx.ReadingLog.controller.dto.BookResponseDTO;
import com.adx.ReadingLog.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> getBooks(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(service.getBooksByUser(userDetails.getUsername()), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@Valid @RequestBody BookRequestDTO book,
                                        @AuthenticationPrincipal UserDetails userDetails){
        service.addBook(book, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable UUID id, @Valid
            @RequestBody BookRequestDTO book, @AuthenticationPrincipal UserDetails userDetails){
        service.updateBook(id, book, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id,
                                           @AuthenticationPrincipal UserDetails userDetails){
        service.deleteBook(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

}

package com.adx.ReadingLog.service;

import com.adx.ReadingLog.controller.dto.BookRequestDTO;
import com.adx.ReadingLog.controller.dto.BookResponseDTO;
import com.adx.ReadingLog.exceptions.BookException;
import com.adx.ReadingLog.exceptions.BookOwnershipException;
import com.adx.ReadingLog.model.Book;
import com.adx.ReadingLog.model.User;
import com.adx.ReadingLog.repository.BookRepository;
import com.adx.ReadingLog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupabaseStorageService storageService;

    public List<BookResponseDTO> getBooksByUser(String username){
        User user = getUserByUsername(username);
        return bookRepository.findByUser(user).stream().map(BookResponseDTO::new).toList();
    }

    public BookResponseDTO getBookById(String username, UUID id) {
        try {
            Book book = bookRepository.getBookById(id);
            validateBookOwnership(book, username);

            return new BookResponseDTO(book);
        } catch (Exception e){
            throw new RuntimeException("Erro ao encontrar livro");
        }
    }


    public void addBook(BookRequestDTO bookDTO, String username) {
        User user = getUserByUsername(username);
        String imgUrl = "";

        if(bookDTO.image() != null && !bookDTO.image().isEmpty()){
            imgUrl = storageService.uploadFile(bookDTO.image());
        }

        Book book = new Book(bookDTO);

        book.setImgUrl(imgUrl);
        book.setUser(user);
        book.setStartedAt(LocalDateTime.now());
        bookRepository.save(book);
    }

    public void updateBook(UUID id, BookRequestDTO bookDTO, String username){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(BookException::new);

        validateBookOwnership(existingBook, username);

        existingBook.setTitle(bookDTO.title());
        existingBook.setDescription(bookDTO.description());
        existingBook.setCompleted(bookDTO.completed());

        if(bookDTO.completed())
            existingBook.setFinishedAt(LocalDateTime.now());
        else
            existingBook.setFinishedAt(null);

        bookRepository.save(existingBook);

    }

    public void deleteBook(UUID id, String username){
        Book book = bookRepository.findById(id)
                .orElseThrow(BookException::new);

        validateBookOwnership(book, username);

        String fileName = extractFileNameFromUrl(book.getImgUrl());

        if(fileName != null && !fileName.isEmpty()){
            storageService.deleteFile(fileName);
        }

        bookRepository.deleteById(id);
    }

    private String extractFileNameFromUrl(String url){
        if(url == null || !url.contains("/")){
            return null;
        }

        return url.substring(url.lastIndexOf("/") + 1);
    }

    private User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    private void validateBookOwnership(Book book, String username){
        if(!book.getUser().getUsername().equals(username))
            throw new BookOwnershipException("Acesso negado: Você não tem permissão para acessar este livro.");
    }

    private String uploadImg(MultipartFile file){
        return "";
    }

}

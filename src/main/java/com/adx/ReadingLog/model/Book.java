package com.adx.ReadingLog.model;

import com.adx.ReadingLog.controller.dto.BookRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "books")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String author;
    private String imgUrl;
    private boolean completed;
    private LocalDateTime startedAt = LocalDateTime.now();
    private LocalDateTime finishedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user = new User(UUID.fromString("d0f2df3d-0d9b-42a9-b06f-cabdb2336c52"), "adx", "12345" );

    public Book(BookRequestDTO bookDTO){
        this.title = bookDTO.title();
        this.description = bookDTO.description();
        this.author = bookDTO.author();
        this.completed = bookDTO.completed();
    }

}

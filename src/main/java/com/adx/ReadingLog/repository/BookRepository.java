package com.adx.ReadingLog.repository;

import com.adx.ReadingLog.model.Book;
import com.adx.ReadingLog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByUser(User user);
    List<Book> findByUserAndCompletedTrue(User user);
    int countByUser(User user);
    int countByUserAndCompletedTrue(User user);


}

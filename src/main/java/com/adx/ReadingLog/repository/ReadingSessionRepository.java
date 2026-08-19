package com.adx.ReadingLog.repository;

import com.adx.ReadingLog.model.Book;
import com.adx.ReadingLog.model.ReadingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ReadingSessionRepository extends JpaRepository<ReadingSession, UUID> {

    Integer countByBook(Book book);

    @Query("SELECT SUM(r.pagesRead) FROM ReadingSession r WHERE r.book = :book")
    Integer countPagesReadByBook(@Param("book") Book book);

    @Query("SELECT SUM(r.durationSeconds) FROM ReadingSession r WHERE r.book = :book")
    Integer countDurationSecondsByBook(@Param("book") Book book);

    @Query("SELECT r.endTime FROM ReadingSession r WHERE r.book = :book ORDER BY r.endTime DESC LIMIT 1")
    LocalDateTime findLastSessionDate(@Param("book") Book book);

}

package com.adx.ReadingLog.service;

import com.adx.ReadingLog.controller.dto.BookSummaryDTO;
import com.adx.ReadingLog.controller.dto.ProfileResponseDTO;
import com.adx.ReadingLog.model.User;
import com.adx.ReadingLog.model.UserProfile;
import com.adx.ReadingLog.repository.BookRepository;
import com.adx.ReadingLog.repository.ProfileRepository;
import com.adx.ReadingLog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public ProfileResponseDTO getProfile(String username){
        try{
            User user = getUserByUsername(username);
            UserProfile profile = profileRepository.findByUser(user);

            int totalBooks = bookRepository.countByUser(user);
            int completedBooksCount = bookRepository.countByUserAndCompletedTrue(user);

            List<BookSummaryDTO> completedBooksList = bookRepository.findByUserAndCompletedTrue(user)
                    .stream()
                    .map(book -> new BookSummaryDTO(book.getTitle(), book.getAuthor()))
                    .toList();

            return new ProfileResponseDTO(
                    user.getId(),
                    profile.getNameProfile(),
                    profile.getEmail(),
                    user.getUsername(),
                    totalBooks,
                    completedBooksCount,
                    completedBooksList

            );

        } catch (Exception e){
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    private User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}

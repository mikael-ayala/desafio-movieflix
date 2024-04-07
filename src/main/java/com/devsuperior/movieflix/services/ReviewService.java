package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public ReviewDTO insertReview(ReviewDTO reviewDTO) {
        User user = authService.authenticated();
        Movie movie = movieRepository.getReferenceById(reviewDTO.getMovieId());

        Review review = new Review();
        review.setText(reviewDTO.getText());
        review.setMovie(movie);
        review.setUser(user);
        review = reviewRepository.save(review);

        return new ReviewDTO(review);
    }
}

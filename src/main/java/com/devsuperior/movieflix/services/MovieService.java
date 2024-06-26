package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPaged(Pageable pageable, String genreId) {
        Long parsedGenreId = null;
        if (!genreId.equals("0")) parsedGenreId = Long.parseLong(genreId);
        Page<Movie> movies = movieRepository.findAllPaged(pageable, parsedGenreId);
        return movies.map(MovieCardDTO::new);
    }

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return new MovieDetailsDTO(movie);
    }
}

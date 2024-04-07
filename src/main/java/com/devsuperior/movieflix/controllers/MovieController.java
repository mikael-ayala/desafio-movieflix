package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> findAllPaged(Pageable pageable,
                                                        @RequestParam(value = "genreId", defaultValue = "0") String genreId) {
        Page<MovieCardDTO> movies = movieService.findAllPaged(pageable, genreId);
        return ResponseEntity.ok(movies);
    }

    @PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        MovieDetailsDTO movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }
}

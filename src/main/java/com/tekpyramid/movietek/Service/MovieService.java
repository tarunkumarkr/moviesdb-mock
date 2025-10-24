package com.tekpyramid.movietek.Service;

import com.tekpyramid.movietek.Entity.Movie;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    String save(Movie movie);

    String update(Movie movie, int moviesId);

    Movie findById(int id);


    String deleteMovieById(int id);

    List<Movie> searchMoviesByMovieTitle(String title);

    List<Movie> findAllMovies();
}

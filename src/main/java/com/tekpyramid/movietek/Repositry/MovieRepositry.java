package com.tekpyramid.movietek.Repositry;

import com.tekpyramid.movietek.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepositry extends JpaRepository<Movie,Integer> {

    List<Movie> findByMovieTitleContainingIgnoreCase(String movieName);
    Page<Movie> findAll(Pageable pageable);
}

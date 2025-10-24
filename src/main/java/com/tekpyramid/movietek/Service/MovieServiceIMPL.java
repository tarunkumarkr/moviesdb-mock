package com.tekpyramid.movietek.Service;

import com.tekpyramid.movietek.Entity.Actor;
import com.tekpyramid.movietek.Entity.Financial;
import com.tekpyramid.movietek.Entity.Language;
import com.tekpyramid.movietek.Entity.Movie;
import com.tekpyramid.movietek.Exception.DuplicateRecord;
import com.tekpyramid.movietek.Repositry.ActorRepositry;
import com.tekpyramid.movietek.Repositry.FinancialRepositry;
import com.tekpyramid.movietek.Repositry.LanguageRepository;
import com.tekpyramid.movietek.Repositry.MovieRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceIMPL implements MovieService {


    @Autowired
    public MovieRepositry movieRepositry;

    @Autowired
    public ActorRepositry actorRepositry;
    @Autowired
    public FinancialRepositry financialRepositry;
    @Autowired
    public LanguageRepository languageRepository;

    @Override
    public String save(Movie movies) {

        Language lang = movies.getLang();
        if (lang != null) {
            Optional<Language> existingLang = languageRepository.findByName(lang.getName());
            if (existingLang.isPresent()) {
                movies.setLang(existingLang.get());
            } else {
                languageRepository.save(lang);
                movies.setLang(lang);
            }

            Financial financials = movies.getFinancials();
            financials.setMovies(movies);
            List<Actor> actors = movies.getActors();


            for (Actor actor : actors) {
                List<Movie> moviesList = actor.getMoviesList();
                if (moviesList != null) {
                    moviesList.add(movies);
                } else {
                    actor.setMoviesList(new ArrayList<>(List.of(movies)));
                }
                actor.setMoviesList(moviesList);
            }
            int moviesId = movieRepositry.save(movies).getMoviesId();


        }
        return "Movie saved successfully" + movies.getMoviesId();
    }

    @Override
    public String update(Movie movie, int moviesId) {

        Optional<Movie> movie2 = movieRepositry.findById(moviesId);

        if(movie2.isPresent()){
            Movie movie1 = movie2.get();
            movie1.setMovieTitle(movie.getMovieTitle());
            movie1.setIndustry(movie.getIndustry());
            movie1.setReleaseYear(movie.getReleaseYear());
            movie1.setImdbRating(movie.getImdbRating());
            movie1.setStudio(movie.getStudio());
            movieRepositry.save(movie1);

        } else {
            throw new RuntimeException("id not found");
        }
        return "Movie updated successfully! ID: "+ movie.getMoviesId();


    }

    @Override
    public Movie findById(int id) {
        return movieRepositry.findById(id).orElseThrow(() -> new DuplicateRecord("Movie not found with ID: " + id));
    }

    @Override

    public String deleteMovieById(int movieId) {

        Movie movie = movieRepositry.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));
        Language language = movie.getLang();

        if (language != null) {

            movie.setLang(null);
            List<Movie> moviesWithLang = movieRepositry.findAll()
                    .stream()
                    .filter(m -> m.getLang() != null && m.getLang().getLanguageId() == language.getLanguageId())
                    .toList();

            if (moviesWithLang.isEmpty()) {
                languageRepository.delete(language);
            }
        }
        if (movie.getActors() != null) {

            for (Actor actor : movie.getActors()) {
                List<Movie> actorMovies = actor.getMoviesList();

                actorMovies.remove(movie);

                if (actorMovies.isEmpty()) {
                    actorRepositry.delete(actor);
                }
            }
            movie.getActors().clear();
        }

        Financial financial = movie.getFinancials();

        if (financial != null) {
            financial.setMovies(null);
            movie.setFinancials(null);
            financialRepositry.delete(financial);
        }
        movieRepositry.delete(movie);
        return "Movie deleted successfully with id: " + movieId;
    }

    @Override

    public List<Movie> searchMoviesByMovieTitle(String title) {

        return movieRepositry.findByMovieTitleContainingIgnoreCase(title);

    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepositry.findAll();
    }



}
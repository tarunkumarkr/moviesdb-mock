package com.tekpyramid.movietek.Controller;

import com.tekpyramid.movietek.Entity.Movie;
import com.tekpyramid.movietek.Response.SuccessResponse;
import com.tekpyramid.movietek.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/app/movies-mock")
@RestController
public class MovieController {

    @Autowired
    public MovieService movieService;
    @GetMapping("/hi")
    public String hi(){
        return "hi Tarun";
    }

    @PostMapping("/save-movie")
    public ResponseEntity<SuccessResponse> save(@RequestBody Movie movie){

        String save = movieService.save(movie);
        SuccessResponse successResponse = new SuccessResponse();

        successResponse.setMessage(save);
        successResponse.setHttpStatus(HttpStatus.ACCEPTED);
        successResponse.setData(null);
        successResponse.setError(false);
        return ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(successResponse);
    }

    @PutMapping("/update-movie/{id}")
    public  ResponseEntity<SuccessResponse> update(@RequestBody Movie movie,@PathVariable("id") int moviesId){
        String update =movieService.update(movie,moviesId);

        SuccessResponse successResponse = new SuccessResponse();

        successResponse.setMessage(update);
        successResponse.setHttpStatus(HttpStatus.ACCEPTED);
        successResponse.setData(null);
        successResponse.setError(false);
        return ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(successResponse);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<SuccessResponse> getMovieById(@PathVariable int id) {
        SuccessResponse successResponse = new SuccessResponse();
        Movie movie = movieService.findById(id);
        successResponse.setMessage("Movie found");
        successResponse.setData(movie);
        successResponse.setHttpStatus(HttpStatus.OK);
        successResponse.setError(false);

        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable int id){

        String message = movieService.deleteMovieById(id);
        SuccessResponse successResponse=new SuccessResponse();
        successResponse.setHttpStatus(HttpStatus.OK);
        successResponse.setError(false);
        successResponse.setMessage(message);
        successResponse.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/fetch/search")
    public List<Movie> searchMovies(@RequestParam("title") String title) {
        return movieService.searchMoviesByMovieTitle(title);
    }


    @GetMapping("/fetch-all")
    public ResponseEntity<SuccessResponse> getAllMovies() {
        List<Movie> movies = movieService.findAllMovies();

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("All movies fetched successfully");
        successResponse.setData(movies);
        successResponse.setHttpStatus(HttpStatus.OK);
        successResponse.setError(false);

        return ResponseEntity.ok(successResponse);
    }






}

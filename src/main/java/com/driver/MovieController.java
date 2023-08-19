package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie added successfully");
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return ResponseEntity.ok("Director added successfully");
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("q") String movieName, @RequestParam("q") String directorName) {
        movieService.addMovieDirectorPair(movieName, directorName);
        return ResponseEntity.ok("Movie-Director pair added successfully");
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name) {
        Movie movie = movieService.getMovieByName(name);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name) {
        Director director = movieService.getDirectorByName(name);
        if (director != null) {
            return ResponseEntity.ok(director);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director) {
        List<String> movies = movieService.getMoviesByDirectorName(director);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> getAllMovies() {
        List<String> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("q") String name) {
        movieService.deleteDirectorByName(name);
        return ResponseEntity.ok("Director and associated movies deleted successfully");
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return ResponseEntity.ok("All directors and their associated movies deleted successfully");
    }
}

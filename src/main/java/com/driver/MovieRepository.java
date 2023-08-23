package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String, Movie> movies = new HashMap<>();
    private HashMap<String, Director> directors = new HashMap<>();
    private HashMap<String, List<String>> directorMovieMapping = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        List<String> moviesForDirector = directorMovieMapping.computeIfAbsent(directorName, k -> new ArrayList<>());
        moviesForDirector.add(movieName);
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return directorMovieMapping.getOrDefault(director, new ArrayList<>());
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String name) {
        directors.remove(name);
        directorMovieMapping.remove(name);
    }

    public void deleteAllDirectors() {
        directors.clear();
        directorMovieMapping.clear();
    }
}

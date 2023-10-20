package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMapping = new HashMap<>();
    }
    public void addMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName(), director);
        directorMovieMapping.put(director.getName(),new ArrayList<>());
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
            List<String> movieList = directorMovieMapping.get(directorName);
            movieList.add(movieName);
            directorMovieMapping.put(directorName,movieList);
        }
    }

    public Movie getMovieByName(String name) {
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name) {
        return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
       List<String> movieList = new ArrayList<>();
       if(directorMovieMapping.containsKey(director)){
           movieList = directorMovieMapping.get(director);
       }
       return movieList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String name) {
        //removing movies of director
        List<String> movies = new ArrayList<>();
        if(directorMovieMapping.containsKey(name)){
            movies = directorMovieMapping.get(name);
            for(String movie : movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            directorMovieMapping.remove(name);
        }
        if(directorMap.containsKey(name)){
            directorMap.remove(name);
        }
    }

    public void deleteAllDirectors() {
        directorMap.clear();
        directorMovieMapping.clear();
        movieMap.clear();
    }
}

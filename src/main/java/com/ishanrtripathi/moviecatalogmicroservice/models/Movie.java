package com.ishanrtripathi.moviecatalogmicroservice.models;

public class Movie {
    String movieId;
    String movieName;

    public Movie() {
    }

    public Movie(String movieId, String movieName) {
        this.movieName = movieName;
        this.movieId= movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

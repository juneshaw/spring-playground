package com.galvanize;

import java.util.ArrayList;
import java.util.List;

public class ResponseWrapperMovie {


    private String wrapper;
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public ResponseWrapperMovie() {
    }
    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}

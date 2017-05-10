package com.galvanize;

import java.util.ArrayList;

public class ResponseWrapperMovie {

    private String wrapper;
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}

package com.galvanize;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseWrapperMovie {

    @JsonProperty("Search")
    private List<Movie> search;

    public List<Movie> getSearch() {
        return this.search;
    }

    public void setSearch(List movies) {
        this.search = movies;
    }
}

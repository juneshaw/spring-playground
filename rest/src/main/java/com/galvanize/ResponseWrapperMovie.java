package com.galvanize;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseWrapperMovie {

    public ResponseWrapperMovie() {}

    public ResponseWrapperMovie(
            List<Movie> movies,
            Integer totalResults,
            String response) {
        this.setSearch(movies);
        this.setTotalResults(totalResults);
        this.setResponse(response);
    }

//    @JsonProperty("Search")
    private List<Movie> search;

    Integer totalResults;

    @JsonProperty("Response")
    String response;

//    @JsonProperty("Search")
    public List<Movie> getSearch() {
        return this.search;
    }

//    @JsonProperty("Search")
    public void setSearch(List<Movie> movies) {
        this.search = movies;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

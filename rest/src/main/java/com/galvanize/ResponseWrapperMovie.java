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

    private List<Movie> search;

    Integer totalResults;

    String response;

    @JsonProperty("Search")
    public List<Movie> getSearch() {
        return this.search;
    }

    @JsonProperty("Search")
    public void setSearch(List<Movie> movies) {
        this.search = movies;
    }

    @JsonProperty("Results")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("Results")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("Response")
    public String getResponse() {
        return response;
    }

    @JsonProperty("Response")
    public void setResponse(String response) {
        this.response = response;
    }
}

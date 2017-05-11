package com.galvanize;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Year")
    String year;
    @JsonProperty("imdbID")
    String imdbId;
    @JsonProperty("Type")
    String type;
    @JsonProperty("Poster")
    String poster;

    public Movie() {
    }

    public Movie(
            String title,
            String year,
            String imdbId,
            String type,
            String poster
    ) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

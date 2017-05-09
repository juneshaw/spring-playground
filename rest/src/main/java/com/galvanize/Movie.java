package com.galvanize;

public class Movie {
    String title;
    String imdbId;
    String poster;
    String year;

    public Movie() {
    }

    public Movie(
            String title,
            String imdbId,
            String poster,
            String year
    ) {
        this.title = title;
        this.imdbId = imdbId;
        this.poster = poster;
        this.year = year;
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
}

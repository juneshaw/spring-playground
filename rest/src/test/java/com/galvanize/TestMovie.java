package com.galvanize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMovie {

    @Test
    public void getMovieGets() throws Exception {
        Movie movie = new Movie(
                "myTitle",
                "MyImdbId",
                "MyPoster",
                "MyYear");
        assertThat(movie.getTitle() == "myTitle");
        assertThat(movie.getImdbId() == "myImdbId");
        assertThat(movie.getPoster() == "myPoster");
        assertThat(movie.getYear() == "myYear");

        movie.setTitle("newTitle");
        movie.setImdbId("newId");
        movie.setPoster("newPoster");
        movie.setYear("newYear");
    }

    @Test
    public void getMovieSets() throws Exception {
        Movie movie = new Movie(
                "myTitle",
                "MyImdbId",
                "MyPoster",
                "MyYear");

        assertThat(movie.getTitle() == " newTitle");
        assertThat(movie.getImdbId() == "newImdbId");
        assertThat(movie.getPoster() == "newPoster");
        assertThat(movie.getYear() == "newYear");
    }
}
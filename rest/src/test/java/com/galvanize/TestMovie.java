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
                "MyTitle",
                "MyImdbId",
                "MyPoster",
                "MyYear",
                "MyType");
        assertThat(movie.getTitle() == "MyTitle");
        assertThat(movie.getImdbId() == "MyImdbId");
        assertThat(movie.getPoster() == "MyPoster");
        assertThat(movie.getYear() == "MyYear");
        assertThat(movie.getYear() == "MyType");

        movie.setTitle("newTitle");
        movie.setImdbId("newId");
        movie.setPoster("newPoster");
        movie.setYear("newYear");
        movie.setYear("newType");
    }

    @Test
    public void getMovieSets() throws Exception {
        Movie movie = new Movie(
                "MyTitle",
                "MyImdbId",
                "MyPoster",
                "MyYear",
                "MyType");

        assertThat(movie.getTitle() == " newTitle");
        assertThat(movie.getImdbId() == "newImdbId");
        assertThat(movie.getPoster() == "newPoster");
        assertThat(movie.getYear() == "newYear");
        assertThat(movie.getYear() == "newType");
    }
}
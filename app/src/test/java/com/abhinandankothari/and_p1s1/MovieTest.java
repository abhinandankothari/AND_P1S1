package com.abhinandankothari.and_p1s1;

import com.abhinandankothari.and_p1s1.contract.Movie;

import org.junit.Test;

import java.text.ParseException;

import static junit.framework.Assert.assertEquals;

public class MovieTest {
    @Test
    public void shouldHaveBlankFormattedDateIfReleasedateIsNotDefined() throws ParseException {
        Movie movie = new Movie(0, null, null, null, null, null);
        assertEquals("", movie.getYearFromReleaseDate());
    }

    @Test
    public void shouldHaveDefinedYearIfDateIsPresent() throws ParseException {
        Movie movie = new Movie(0, null, null, null, null, "2015-01-01");
        assertEquals("2015", movie.getYearFromReleaseDate());
    }

    @Test
    public void shouldHaveBlankFormattedDateIfReleasedateIsNotPresent() throws ParseException {
        Movie movie = new Movie(0, null, null, null, null, "");
        assertEquals("", movie.getYearFromReleaseDate());
    }
}
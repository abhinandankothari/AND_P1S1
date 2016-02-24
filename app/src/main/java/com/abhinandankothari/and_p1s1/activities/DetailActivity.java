package com.abhinandankothari.and_p1s1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhinandankothari.and_p1s1.R;
import com.abhinandankothari.and_p1s1.contract.Movie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @Bind(R.id.movie_title)
    TextView movieTitle;
    @Bind(R.id.synopsis)
    TextView movieSynopsis;
    @Bind(R.id.rating)
    TextView movieRating;
    @Bind(R.id.release_date)
    TextView movieReleasDate;
    @Bind(R.id.img_thumb)
    ImageView movieThumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        Movie movie = bundle.getParcelable(Movie.TAG);
        movieTitle.setText(movie.getMovieTitle());
        movieRating.setText(movie.getMovieRating()+"/10");

        try {
            movieReleasDate.setText(movie.getYearFromReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        movieSynopsis.setText(movie.getMovieSynopsis());
        Picasso.with(this)
                .load(movie.getMoviePosterThumbUrl())
                .placeholder(R.drawable.poster)
                .into(movieThumb);
    }

}

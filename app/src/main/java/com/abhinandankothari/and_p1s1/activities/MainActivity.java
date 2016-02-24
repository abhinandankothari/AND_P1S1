package com.abhinandankothari.and_p1s1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abhinandankothari.and_p1s1.R;
import com.abhinandankothari.and_p1s1.adapters.RecyclerViewAdapter;
import com.abhinandankothari.and_p1s1.contract.Movie;
import com.abhinandankothari.and_p1s1.listners.MoviesViewTouchListener;
import com.abhinandankothari.and_p1s1.listners.OnMoviesTouchListener;
import com.abhinandankothari.and_p1s1.network.MoviesApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private GridLayoutManager gridLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView moviesView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        gridLayoutManager = new GridLayoutManager(this, 2);
        moviesView.setHasFixedSize(true);
        moviesView.setLayoutManager(gridLayoutManager);

        addTouchListenerToMoviesList();
    }

    private void addTouchListenerToMoviesList() {
        moviesView.addOnItemTouchListener(new MoviesViewTouchListener(this, new OnMoviesTouchListener() {
            @Override
            public void onItemClick(View childView, int position) {
                Intent movieIntent = new Intent(MainActivity.this, DetailActivity.class);
                movieIntent.putExtra(Movie.TAG, recyclerViewAdapter.get(position));
                startActivity(movieIntent);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FetchMoviesTask task = new FetchMoviesTask();
        task.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Movie> fetchMoviesList() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String sort = sharedPref.getString("sort", "Popularity");
        MoviesApi movies = new MoviesApi();

        List<Movie> allItems = new ArrayList<Movie>();
        for (Movie movie : movies.ListofMovies(sort)) {
            allItems.add(new Movie(movie.getId(), movie.getMovieTitle(), movie.getMoviePosterThumbUrl(), movie.getMovieSynopsis(),
                    movie.getMovieRating(), movie.getMovieReleaseDate()));
        }
        return allItems;
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        @Override
        protected void onPostExecute(List<Movie> movieList) {
            super.onPostExecute(movieList);
            recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), movieList);
            moviesView.setAdapter(recyclerViewAdapter);
        }

        @Override
        protected List<Movie> doInBackground(Void... params) {
            return fetchMoviesList();
        }
    }
}

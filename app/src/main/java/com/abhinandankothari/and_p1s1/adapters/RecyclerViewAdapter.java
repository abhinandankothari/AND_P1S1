package com.abhinandankothari.and_p1s1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinandankothari.and_p1s1.R;
import com.abhinandankothari.and_p1s1.contract.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<Movie> movies;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_grid, null);
        RecyclerViewHolders recyclerViewHolders = new RecyclerViewHolders(layoutView);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        Picasso.with(context)
                .load(movies.get(position).getMoviePosterThumbUrl())
                .placeholder(R.drawable.poster)
                .into(holder.movieThumb);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public Movie get(int position) {
        return this.movies.get(position);
    }
}

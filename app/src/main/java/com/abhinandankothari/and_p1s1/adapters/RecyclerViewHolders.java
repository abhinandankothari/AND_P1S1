package com.abhinandankothari.and_p1s1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.abhinandankothari.and_p1s1.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    @Bind(R.id.movie_thumb)
    ImageView movieThumb;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}


package com.abhinandankothari.and_p1s1.listners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MoviesViewTouchListener implements RecyclerView.OnItemTouchListener {
    private OnMoviesTouchListener listener;
    private GestureDetector gestureDetector;

    public MoviesViewTouchListener(Context context, OnMoviesTouchListener listener) {
        this.listener = listener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if ((childView != null) && (listener != null) && gestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

package com.vitorarantes.mymovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.vitorarantes.mymovies.R;
import com.vitorarantes.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends BaseAdapter
{
    private LayoutInflater layoutInflater;
    private List<Movie> data;
    private Context context;
    private ImageView viewIcon;

    public MovieListAdapter (Context context, List<Movie> data)
    {
        this.context   = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem (int i)
    {
        return data.get(i);
    }

    @Override
    public long getItemId (int i)
    {
        return i;
    }

    @Override
    public View getView (int i, View view, ViewGroup viewGroup)
    {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.movie_row_layout, null);
        }

        Movie v = data.get(i);

        TextView movieTitle = (TextView)view.findViewById(R.id.id_movie_title);
        movieTitle.setText(v.getTitle());

        TextView movieDirector = (TextView)view.findViewById(R.id.id_movie_director);
        movieDirector.setText(v.getDirector());

        ImageView seenImage = (ImageView) view.findViewById(R.id.logo_imageView);

        if (v.isSeen()) {
            seenImage.setVisibility(View.VISIBLE);
        } else {
            seenImage.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}

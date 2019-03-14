package com.vitorarantes.mymovies;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vitorarantes.mymovies.adapter.MovieListAdapter;
import com.vitorarantes.mymovies.model.Genre;
import com.vitorarantes.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesListFragment extends Fragment {

    private static final int MENU = 100;
    private List<Movie> movies = new ArrayList<>();
    private MovieListAdapter customAdapter;
    private ListView myList;

    private List<Genre> genres;

    public List<Genre> getGenres()
    {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(getResources().getString(R.string.horror_genre)));
        genres.add(new Genre(getResources().getString(R.string.scifi_genre)));
        genres.add(new Genre(getResources().getString(R.string.drama_genre)));
        genres.add(new Genre(getResources().getString(R.string.comedy_genre)));
        return genres;
    }

    public MoviesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list);

        genres = getGenres();

        movies.add(new Movie(1, true, "movie 1", "test 1", "test test 1", genres.get(3)));
        movies.add(new Movie(2, false, "movie 2", "test 2", "test test 2", genres.get(1)));
        movies.add(new Movie(3, true, "movie 3", "test 3", "test test 3", genres.get(2)));

        this.customAdapter = new MovieListAdapter(this, movies);
        this.myList = (ListView) findViewById(R.id.movies_list_id);
        this.myList.setAdapter(this.customAdapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        menu.add(0, MENU, 1, "AJOUTER");

        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId()) {
            case MENU:
                startActivityForResult(new Intent(MoviesListActivity.this, CreateMovieActivity.class), 99, null);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99) {
            if (resultCode == RESULT_OK) {
                Movie movie = data.getParcelableExtra(CreateMovieActivity.NEW_MOVIE);

                if (movie != null) {
                    movies.add(movie);
                    customAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}

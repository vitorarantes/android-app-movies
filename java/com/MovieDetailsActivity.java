package com.vitorarantes.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import com.vitorarantes.mymovies.model.Genre;
import com.vitorarantes.mymovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity
{
    public static final int REQUEST_CODE_CREATE_MOVIE = 99;

    private TextView movieNameEditText;
    private TextView movieDescriptionEditText;
    private TextView movieDirectorEditText;

    private CheckBox checkbox1, checkbox2, checkbox3, checkbox4;
    private RadioButton radioButtonSeen;
    private Button newMovieButton;

    private List<Movie> myMovies = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieNameEditText        = (TextView) findViewById(R.id.movie_name_editText);
        movieDescriptionEditText = (TextView) findViewById(R.id.movie_description_editText);
        movieDirectorEditText    = (TextView) findViewById(R.id.movie_director_editText);

        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);

        radioButtonSeen = (RadioButton) findViewById(R.id.seen_radioButton);
        newMovieButton  = (Button) findViewById(R.id.new_movie_button);

        newMovieButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                startActivityForResult(new Intent(MovieDetailsActivity.this, CreateMovieActivity.class), REQUEST_CODE_CREATE_MOVIE);
            }
        });
    }

    private void displayData (Movie movie)
    {
        if (null != movie) {
            setCheckStates(movie);
            movieNameEditText.setText(movie.getTitle());
            movieDescriptionEditText.setText(movie.getDescription());
            movieDirectorEditText.setText(movie.getDirector());
            radioButtonSeen.setChecked(movie.isSeen());
        }
    }

    private void setCheckStates (Movie movie)
    {
        for (Genre genre: movie.getGenres()) {
            if (genre.getName().equals(getResources().getString(R.string.horror_genre))) {
                checkbox1.setChecked(true);
            } else if (genre.getName().equals(getResources().getString(R.string.scifi_genre))) {
                checkbox2.setChecked(true);
            } else if (genre.getName().equals(getResources().getString(R.string.drama_genre))) {
                checkbox3.setChecked(true);
            } else if (genre.getName().equals(getResources().getString(R.string.comedy_genre))) {
                checkbox4.setChecked(true);
            }
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {
            case REQUEST_CODE_CREATE_MOVIE:
                if (data != null) {
                    Movie movie = (Movie)data.getParcelableExtra(CreateMovieActivity.NEW_MOVIE);

                    myMovies.add(movie);

                    Log.d(CreateMovieActivity.NEW_MOVIE, "Name: " + movie.getTitle());
                    Log.d(CreateMovieActivity.NEW_MOVIE, "Description: " + movie.getDescription());
                    Log.d(CreateMovieActivity.NEW_MOVIE, "Director: " + movie.getDirector());
                    Log.d(CreateMovieActivity.NEW_MOVIE, "Seen: " + movie.isSeen());
                    Log.d(CreateMovieActivity.NEW_MOVIE, "Genres: ");

                    for (Genre genre: movie.getGenres()) {
                        Log.d(CreateMovieActivity.NEW_MOVIE, genre.getName());
                    }

                    displayData(movie);
                }
                break;
            default:
                break;
        }
    }
}

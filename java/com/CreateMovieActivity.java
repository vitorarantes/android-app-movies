package com.vitorarantes.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.vitorarantes.mymovies.model.Genre;
import com.vitorarantes.mymovies.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class CreateMovieActivity extends AppCompatActivity
{
    public static final String NEW_MOVIE = "NEW_MOVIE_TAG";

    private List<Genre> movieGenres = new ArrayList<>();

    private EditText movieNameEditText;
    private EditText movieDescriptionEditText;
    private EditText movieDirectorEditText;

    private CheckBox checkbox1, checkbox2, checkbox3, checkbox4;
    private Button saveButton;
    private RadioButton radioButtonSeen;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_movie);

        movieNameEditText        = (EditText) findViewById(R.id.movie_name_editText);
        movieDescriptionEditText = (EditText) findViewById(R.id.movie_description_editText);
        movieDirectorEditText    = (EditText) findViewById(R.id.movie_director_editText);

        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);

        radioButtonSeen = (RadioButton) findViewById(R.id.seen_radioButton);
        saveButton      = (Button) findViewById(R.id.create_movie_button);

        saveButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                createMovie();
            }
        });

        movieGenres = getGenres();
    }

    private List<Genre> getGenres()
    {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(getResources().getString(R.string.horror_genre)));
        genres.add(new Genre(getResources().getString(R.string.scifi_genre)));
        genres.add(new Genre(getResources().getString(R.string.drama_genre)));
        genres.add(new Genre(getResources().getString(R.string.comedy_genre)));
        return genres;
    }

    private void createMovie()
    {
        String title = movieNameEditText.getText().toString();
        String director = movieDirectorEditText.getText().toString();
        String description = movieDescriptionEditText.getText().toString();
        boolean seen = radioButtonSeen.isChecked();

        List<Genre> genres = new ArrayList<>();

        if (this.checkbox1.isChecked()) {
            genres.add(movieGenres.get(0));
        }

        if (this.checkbox2.isChecked()) {
            genres.add(movieGenres.get(1));
        }

        if (this.checkbox3.isChecked()) {
            genres.add(movieGenres.get(2));
        }

        if (this.checkbox4.isChecked()) {
            genres.add(movieGenres.get(3));
        }

        Movie newMovie = new Movie(0, seen, description, director, title, genres);

        Intent data = new Intent();
        data.putExtra(NEW_MOVIE, newMovie);
        setResult(RESULT_OK, data);

        finish();
    }
}

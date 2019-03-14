package com.vitorarantes.mymovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable
{
    private int id;
    private String title;
    private String director;
    private String description;
    private List<Genre> genres;
    private boolean seen;

    public Movie (int id, boolean seen, String description, String director, String title, Genre genre) {
        this.genres = new ArrayList<>();
        this.id = id;
        this.seen = seen;
        this.description = description;
        this.director = director;
        this.title = title;
        this.genres.add(genre);
    }

    public Movie (int id, boolean seen, String description, String director, String title, List<Genre> genres) {
        this.genres = new ArrayList<>();
        this.id = id;
        this.seen = seen;
        this.description = description;
        this.director = director;
        this.title = title;
        this.genres.addAll(genres);
    }

    protected Movie(Parcel in)
    {
        id = in.readInt();
        title = in.readString();
        director = in.readString();
        description = in.readString();
        genres = in.createTypedArrayList(Genre.CREATOR);
        seen = in.readByte() != 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>()
    {
        @Override
        public Movie createFromParcel (Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray (int size) {
            return new Movie[size];
        }
    };

    @Override
    public void writeToParcel (Parcel parcel, int i)
    {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(director);
        parcel.writeString(description);
        parcel.writeTypedList(genres);
        parcel.writeByte((byte) (seen ? 1 : 0));
    }

    public int getId()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public List<Genre> getGenres()
    {
        return genres;
    }

    public boolean isSeen()
    {
        return seen;
    }

    public void setSeen (boolean seen)
    {
        this.seen = seen;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    public int describeContents()
    {
        return 5;
    }
}

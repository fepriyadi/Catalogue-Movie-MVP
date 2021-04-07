package com.dicoding.course.cataloguemoviempv.model;

import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MK-fepriyadi on 9/21/2017.
 */

public class Movie extends RealmObject implements Serializable
{
  @Ignore
  private String poster_path;
  @Ignore
  private boolean adult;
  @Ignore
  private String overview;
  @Ignore
  private String release_date;
  @Ignore
  private int[] genre_ids;
  @PrimaryKey
  private int id;
  @Ignore
  private String original_title;
  @Ignore
  private String original_language;
  @Ignore
  private String title;
  @Ignore
  private String backdrop_path;
  @Ignore
  private double popularity;
  @Ignore
  private int vote_count;
  @Ignore
  private boolean video;
  @Ignore
  private double vote_average;
  
  public Movie()
  {
  }
  
  public Movie(int id)
  {
    this.id = id;
  }
  
  public String getPoster_path()
  {
    return poster_path;
  }
  
  public void setPoster_path(String poster_path)
  {
    this.poster_path = poster_path;
  }
  
  public boolean isAdult()
  {
    return adult;
  }
  
  public void setAdult(boolean adult)
  {
    this.adult = adult;
  }
  
  public String getOverview()
  {
    return overview;
  }
  
  public void setOverview(String overview)
  {
    this.overview = overview;
  }
  
  public String getRelease_date()
  {
    return release_date;
  }
  
  public void setRelease_date(String release_date)
  {
    this.release_date = release_date;
  }
  
  public int[] getGenre_ids()
  {
    return genre_ids;
  }
  
  public void setGenre_ids(int[] genre_ids)
  {
    this.genre_ids = genre_ids;
  }
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getOriginal_title()
  {
    return original_title;
  }
  
  public void setOriginal_title(String original_title)
  {
    this.original_title = original_title;
  }
  
  public String getOriginal_language()
  {
    return original_language;
  }
  
  public void setOriginal_language(String original_language)
  {
    this.original_language = original_language;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getBackdrop_path()
  {
    return backdrop_path;
  }
  
  public void setBackdrop_path(String backdrop_path)
  {
    this.backdrop_path = backdrop_path;
  }
  
  public double getPopularity()
  {
    return popularity;
  }
  
  public void setPopularity(double popularity)
  {
    this.popularity = popularity;
  }
  
  public int getVote_count()
  {
    return vote_count;
  }
  
  public void setVote_count(int vote_count)
  {
    this.vote_count = vote_count;
  }
  
  public boolean isVideo()
  {
    return video;
  }
  
  public void setVideo(boolean video)
  {
    this.video = video;
  }
  
  public double getVote_average()
  {
    return vote_average;
  }
  
  public void setVote_average(double vote_average)
  {
    this.vote_average = vote_average;
  }
  
  public void onClick(View v, Movie p) {
    Toast.makeText(v.getContext(), p.getTitle(), Toast.LENGTH_LONG).show();
  }
}

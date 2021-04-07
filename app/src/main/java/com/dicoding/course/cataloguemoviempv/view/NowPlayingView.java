package com.dicoding.course.cataloguemoviempv.view;

import com.dicoding.course.cataloguemoviempv.model.Movie;

import java.util.List;

/**
 * Created by MK-fepriyadi on 1/7/2018.
 */

public interface NowPlayingView extends BaseView
{
  void displayMovies(List<Movie> movieList);
  void displayNoMovies();
  void diplayError();
}

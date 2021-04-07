package com.dicoding.course.cataloguemoviempv.view;

import com.dicoding.course.cataloguemoviempv.model.Movie;

import java.util.List;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public interface SearchView extends BaseView
{
  void displayMovies(List<Movie> movieList);
  void displayNoMovies();
  void displayError();
}

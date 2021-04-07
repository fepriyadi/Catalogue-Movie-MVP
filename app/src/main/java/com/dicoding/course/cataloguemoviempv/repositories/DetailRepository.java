package com.dicoding.course.cataloguemoviempv.repositories;

import com.dicoding.course.cataloguemoviempv.model.Movie;

import io.reactivex.Observable;

/**
 * Created by MK-fepriyadi on 1/31/2018.
 */

public interface DetailRepository
{
  Observable<Boolean> saveMovieFav(Movie movie);
  
  Observable<Boolean> getMovieFav(Movie movie);
  
  Observable<Boolean> deleteMovieFav(Movie movie);
}

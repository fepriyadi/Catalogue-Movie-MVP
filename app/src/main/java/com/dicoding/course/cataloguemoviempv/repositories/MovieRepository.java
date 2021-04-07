package com.dicoding.course.cataloguemoviempv.repositories;

import com.dicoding.course.cataloguemoviempv.model.Result;
import com.dicoding.course.cataloguemoviempv.model.ResultMovie;

import io.reactivex.Observable;

/**
 * Created by MK-fepriyadi on 1/21/2018.
 */

public interface MovieRepository
{
  Observable<ResultMovie> getMoviesNowPlaying();
  
  Observable<ResultMovie> getMoviesUpcoming();
  
  Observable<Result> getMoviesSearch(String query);
  
}

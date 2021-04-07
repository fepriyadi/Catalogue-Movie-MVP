package com.dicoding.course.cataloguemoviempv.repositories.impl;

import com.dicoding.course.cataloguemoviempv.BuildConfig;
import com.dicoding.course.cataloguemoviempv.model.Result;
import com.dicoding.course.cataloguemoviempv.model.ResultMovie;
import com.dicoding.course.cataloguemoviempv.network.NetworkService;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;

import io.reactivex.Observable;

/**
 * Created by MK-fepriyadi on 1/26/2018.
 */

public class MovieRepositoryImpl implements MovieRepository
{
  private NetworkService networkService;
  private Observable<ResultMovie> observable;
  
  public MovieRepositoryImpl(NetworkService networkService)
  {
    this.networkService = networkService;
  }
  
  @Override
  public Observable<ResultMovie> getMoviesNowPlaying()
  {
    try
    {
      return networkService.getNowPlaying(BuildConfig.API_KEY);
    }
    catch (Exception e)
    {
      throw new RuntimeException();
    }
  }
  
  @Override
  public Observable<ResultMovie> getMoviesUpcoming()
  {
    try
    {
      return networkService.getUpComing(BuildConfig.API_KEY);
    }
    catch (Exception e)
    {
      throw new RuntimeException();
    }
  }
  
  @Override
  public Observable<Result> getMoviesSearch(String query)
  {
    try
    {
      return networkService.searchMovie(BuildConfig.API_KEY, query);
    }
    catch (Exception e)
    {
      throw new RuntimeException();
    }
  }
  
}

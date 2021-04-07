package com.dicoding.course.cataloguemoviempv.util;

import com.dicoding.course.cataloguemoviempv.presenter.NowPlayingPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.NowPlayingView;

import io.reactivex.Scheduler;

/**
 * Created by MK-fepriyadi on 2/4/2018.
 */

public class NowPlayingPresenterFactoryImpl implements PresenterFactory
{
  private NowPlayingView view;
  private MovieRepository movieRepository;
  private Scheduler scheduler;
  
  public NowPlayingPresenterFactoryImpl(MovieRepository movieRepository, Scheduler scheduler)
  {
    this.movieRepository = movieRepository;
    this.scheduler = scheduler;
  }
  
  @Override
  public NowPlayingPresenter create()
  {
    return new NowPlayingPresenter(movieRepository, scheduler);
  }
}

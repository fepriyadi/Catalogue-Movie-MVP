package com.dicoding.course.cataloguemoviempv.presenter;

import com.dicoding.course.cataloguemoviempv.model.ResultMovie;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.NowPlayingView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MK-fepriyadi on 1/7/2018.
 */

public class NowPlayingPresenter
{
  private NowPlayingView view;
  private MovieRepository movieRepository;
  private Scheduler mainScheduler;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  
  public NowPlayingPresenter(MovieRepository movieRepository, Scheduler mainScheduler)
  {
    this.movieRepository = movieRepository;
    this.mainScheduler = mainScheduler;
  }
  
  public void onViewAttached(NowPlayingView view)
  {
    this.view = view;
  }
  
  public void dettachView()
  {
    this.view = null;
  }
  
  public void loadMoviesNowPlaying()
  {
    compositeDisposable.add(movieRepository.getMoviesNowPlaying()
      .subscribeOn(Schedulers.io())
      .observeOn(mainScheduler)
      .subscribeWith(new DisposableObserver<ResultMovie>()
      {
        @Override
        public void onNext(ResultMovie resultMovie)
        {
          System.out.println("Thread subcribe " + Thread.currentThread().getId());
          if (resultMovie.getResults().isEmpty())
            view.displayNoMovies();
          else
            view.displayMovies(resultMovie.getResults());
        }
        
        @Override
        public void onError(Throwable e)
        {
          view.diplayError();
        }
        
        @Override
        public void onComplete()
        {
          view.removeProgress();
        }
      })
    );
  }
  
  public void unsubscribeView()
  {
    compositeDisposable.clear();
  }
}

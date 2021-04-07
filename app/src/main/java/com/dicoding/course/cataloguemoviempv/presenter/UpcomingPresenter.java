package com.dicoding.course.cataloguemoviempv.presenter;

import com.dicoding.course.cataloguemoviempv.model.ResultMovie;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.UpcomingView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public class UpcomingPresenter
{
  private UpcomingView view;
  private MovieRepository movieRepository;
  private Scheduler mainScheduler;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  
  public UpcomingPresenter(UpcomingView view, MovieRepository movieRepository, Scheduler mainScheduler)
  {
    this.view = view;
    this.movieRepository = movieRepository;
    this.mainScheduler = mainScheduler;
  }
  
  public void loadUpcomingMovies()
  {
    compositeDisposable.add(movieRepository.getMoviesUpcoming()
      .subscribeOn(Schedulers.io())
      .observeOn(mainScheduler)
      .subscribeWith(new DisposableObserver<ResultMovie>()
      {
        @Override
        public void onNext(ResultMovie resultMovie)
        {
          if (resultMovie.getResults().isEmpty())
            view.displayNoMovies();
          else
            view.displayMovies(resultMovie.getResults());
        }
        
        @Override
        public void onError(Throwable e)
        {
          view.displayError();
        }
        
        @Override
        protected void onStart()
        {
          super.onStart();
          view.showProgress();
        }
        
        @Override
        public void onComplete()
        {
          view.removeProgress();
        }
      }));
  }
 
  public void unsubscribe()
  {
    compositeDisposable.clear();
  }
  
  
}

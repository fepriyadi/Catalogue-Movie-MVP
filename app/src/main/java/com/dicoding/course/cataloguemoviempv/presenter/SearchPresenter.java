package com.dicoding.course.cataloguemoviempv.presenter;

import com.dicoding.course.cataloguemoviempv.model.Result;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.SearchView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public class SearchPresenter
{
  private SearchView view;
  private MovieRepository movieRepository;
  private Scheduler mainScheduler;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  
  public SearchPresenter(SearchView view, MovieRepository movieRepository, Scheduler mainScheduler)
  {
    this.view = view;
    this.movieRepository = movieRepository;
    this.mainScheduler = mainScheduler;
  }
  
  public void loadMoviesSearch(String query)
  {
    compositeDisposable.add(movieRepository.getMoviesSearch(query)
      .subscribeOn(Schedulers.io())
      .observeOn(mainScheduler)
      .subscribeWith(new DisposableObserver<Result>()
      {
        @Override
        public void onNext(Result result)
        {
          if (result.getResults().isEmpty())
            view.displayNoMovies();
          else
            view.displayMovies(result.getResults());
        }
        
        @Override
        public void onError(Throwable e)
        {
          view.displayError();
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

package com.dicoding.course.cataloguemoviempv.presenter;

import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.repositories.DetailRepository;
import com.dicoding.course.cataloguemoviempv.view.DetailView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MK-fepriyadi on 1/31/2018.
 */

public class DetailPresenter
{
  private static final String TAG = DetailPresenter.class.getSimpleName();
  private DetailView view;
  private DetailRepository detailRepository;
  private Scheduler scheduler;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  
  public DetailPresenter(DetailView view, DetailRepository detailRepository, Scheduler scheduler)
  {
    this.view = view;
    this.detailRepository = detailRepository;
    this.scheduler = scheduler;
  }
  
  public void loadMovieFav(Movie movie)
  {
    compositeDisposable.add(detailRepository.getMovieFav(movie)
      .subscribeOn(Schedulers.io())
      .observeOn(scheduler)
      .subscribeWith(new DisposableObserver<Boolean>()
      {
        @Override
        public void onNext(Boolean isFav)
        {
          view.showProgress();
          if (isFav)
            view.displayIsFavourite();
          else
            view.displayNotFav();
        }
        
        @Override
        public void onError(Throwable e)
        {
          view.displayErrorLoad();
        }
        
        @Override
        public void onComplete()
        {
          view.removeProgress();
        }
      }));
  }
  
  public void saveFavMovie(Movie movie)
  {
    compositeDisposable.add(detailRepository.saveMovieFav(movie)
      .subscribeOn(Schedulers.io())
      .observeOn(scheduler)
      .subscribeWith(new DisposableObserver<Boolean>()
      {
        @Override
        public void onNext(Boolean aBoolean)
        {
          if (aBoolean)
            view.displaySuccessSave();
          else
            view.displayErrorSave();
        }
        
        @Override
        public void onError(Throwable e)
        {
          view.displayErrorSave();
        }
        
        @Override
        public void onComplete()
        {
          view.removeProgress();
        }
        
        @Override
        protected void onStart()
        {
          super.onStart();
          view.showProgress();
        }
      })
    );
  }
  
  public void deleteFavMovie(Movie movie)
  {
    compositeDisposable.add(detailRepository.deleteMovieFav(movie)
      .subscribeOn(Schedulers.io())
      .observeOn(scheduler)
      .subscribeWith(new DisposableObserver<Boolean>()
      {
        @Override
        public void onNext(Boolean aBoolean)
        {
          if (aBoolean)
            view.displaySuccessRemove();
          else
            view.displayErrorRemove();
        }
        
        @Override
        public void onError(Throwable e)
        {
          view.displayErrorRemove();
        }
        
        @Override
        public void onComplete()
        {
          view.removeProgress();
        }
      })
    );
  }
}

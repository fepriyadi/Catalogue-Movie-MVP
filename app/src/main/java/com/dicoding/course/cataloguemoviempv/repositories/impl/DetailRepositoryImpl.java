package com.dicoding.course.cataloguemoviempv.repositories.impl;

import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.repositories.DetailRepository;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by MK-fepriyadi on 1/31/2018.
 */

public class DetailRepositoryImpl implements DetailRepository
{
  private static final String TAG = DetailRepository.class.getSimpleName();
  
  @Override
  public Observable<Boolean> saveMovieFav(Movie movie)
  {
    return Observable.fromCallable(() ->
    {
      try
      {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(new Movie(movie.getId()));
        realm.commitTransaction();
        realm.close();
        return true;
      }
      catch (Exception e)
      {
        throw new RuntimeException();
      }
    });
  }
  
  @Override
  public Observable<Boolean> getMovieFav(Movie movie)
  {
    return Observable.fromCallable(() ->
    {
      try
      {
        boolean isFavourite = false;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Movie> results = realm.where(Movie.class).findAll();
        List<Movie> movieList = realm.copyFromRealm(results);
        
        for (Movie movie1 : movieList)
        {
          if (movie1.getId() == movie.getId())
            isFavourite = true;
        }
        realm.close();
        return isFavourite;
      }
      catch (Exception e)
      {
        throw new RuntimeException();
      }
    });
  }
  
  @Override
  public Observable<Boolean> deleteMovieFav(Movie movie)
  {
    return Observable.fromCallable(() ->
    {
      try
      {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final RealmResults<Movie> results = realm.where(Movie.class).findAll();
        Movie movie1 = results.where()
          .equalTo("id", movie.getId())
          .findFirst();
        movie1.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        return true;
      }
      catch (Exception e)
      {
        throw new RuntimeException();
      }
    });
  }
  
}

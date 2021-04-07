package com.dicoding.course.cataloguemoviempv.dagger;

import com.dicoding.course.cataloguemoviempv.App;
import com.dicoding.course.cataloguemoviempv.activity.DetailActivity;
import com.dicoding.course.cataloguemoviempv.fragment.FavouriteFragment;
import com.dicoding.course.cataloguemoviempv.fragment.NowPlayingFragment;
import com.dicoding.course.cataloguemoviempv.fragment.SearchFragment;
import com.dicoding.course.cataloguemoviempv.fragment.UpComingFragment;
import com.dicoding.course.cataloguemoviempv.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MK-fepriyadi on 1/26/2018.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent
{
  void inject(NowPlayingFragment nowPlayingFragment);
  
  void inject(UpComingFragment upComingFragment);
  
  void inject(App application);
  
  void inject(SearchFragment searchFragment);
  
  void inject(FavouriteFragment favouriteFragment);
  
  void inject(DetailActivity detailActivity);
  
  
}

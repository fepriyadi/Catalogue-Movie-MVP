package com.dicoding.course.cataloguemoviempv.dagger;

import android.content.Context;

import com.dicoding.course.cataloguemoviempv.App;
import com.dicoding.course.cataloguemoviempv.adapter.CardViewAdapter;
import com.dicoding.course.cataloguemoviempv.network.NetworkService;
import com.dicoding.course.cataloguemoviempv.repositories.DetailRepository;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.repositories.impl.DetailRepositoryImpl;
import com.dicoding.course.cataloguemoviempv.repositories.impl.MovieRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by MK-fepriyadi on 1/27/2018.
 */
@Module
public class AppModule
{
  private App app;
  
  public AppModule(App app)
  {
    this.app = app;
  }
  
  @Provides
  @Singleton
  @SuppressWarnings("unused")
  NetworkService providesNetworkService(Retrofit retrofit)
  {
    return retrofit.create(NetworkService.class);
  }
  
  @Provides
  Context providesContext()
  {
    return app.getApplicationContext();
  }
  
  @Provides
  @Singleton
  @SuppressWarnings("unused")
  MovieRepository providesService(NetworkService networkService)
  {
    return new MovieRepositoryImpl(networkService);
  }
  
  @Provides
  DetailRepository providesDetail()
  {
    return new DetailRepositoryImpl();
  }
  
  @Provides
  @Singleton
  CardViewAdapter providesCardViewAdapter(Context context)
  {
    return new CardViewAdapter(context);
  }
}

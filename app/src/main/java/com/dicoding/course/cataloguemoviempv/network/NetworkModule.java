package com.dicoding.course.cataloguemoviempv.network;

import com.dicoding.course.cataloguemoviempv.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by MK-fepriyadi on 1/16/2018.
 */
@Module
public class NetworkModule
{
  File cacheFile;
  
  public NetworkModule(File cacheFile)
  {
    this.cacheFile = cacheFile;
  }
  
  @Provides
  @Singleton
  Retrofit provideCall()
  {
    Cache cache = null;
    try
    {
      cache = new Cache(cacheFile, 10 * 1024 * 1024);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    // set your desired log level
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .addInterceptor(logging)
      .cache(cache)
      .build();
    
    return new Retrofit.Builder()
      .baseUrl(BuildConfig.MAIN_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addConverterFactory(ScalarsConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();
  }
}

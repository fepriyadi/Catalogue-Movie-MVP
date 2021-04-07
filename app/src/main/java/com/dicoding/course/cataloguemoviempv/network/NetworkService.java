package com.dicoding.course.cataloguemoviempv.network;

import com.dicoding.course.cataloguemoviempv.model.DetailMovie;
import com.dicoding.course.cataloguemoviempv.model.Result;
import com.dicoding.course.cataloguemoviempv.model.ResultMovie;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MK-fepriyadi on 1/16/2018.
 */

public interface NetworkService
{
  @GET("3/search/movie/")
  Observable<Result> searchMovie(@Query("api_key") String api_key,
                           @Query("query") String query);
  
  @GET("3/movie/{movie_id}")
  Single<DetailMovie> getDetail(@Path("movie_id") Integer movie_id,
                              @Query("api_key") String api_key);
  @GET("3/list/{list_id}")
  Single<DetailMovie> getDetails(@Path("list_id") Integer movie_id,
                               @Query("api_key") String api_key);
  
  @GET("3/movie/upcoming/")
  Observable<ResultMovie> getUpComing(@Query("api_key") String api_key);
  
  @GET("3/movie/now_playing/")
  Observable<ResultMovie> getNowPlaying(@Query("api_key") String api_key);
  
}

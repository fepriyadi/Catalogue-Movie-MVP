package com.dicoding.course.cataloguemoviempv.main;

import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.model.ResultMovie;
import com.dicoding.course.cataloguemoviempv.presenter.NowPlayingPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.NowPlayingView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MK-fepriyadi on 1/7/2018.
 */
public class NowPlayingPresenterTest
{
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  
  @Mock
  NowPlayingView view;
  
  @Mock
  MovieRepository repository;
  
  private NowPlayingPresenter presenter;
  
  private final List<Movie> MANY_MOVIES = Arrays.asList(new Movie(), new Movie(), new Movie());
  
  @Before
  public void setUp() throws Exception
  {
    presenter = new NowPlayingPresenter(repository, Schedulers.trampoline());
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
  }
  
  @After
  public void cleanUp()
  {
    RxJavaPlugins.reset();
  }
  
  @Test
  public void nowPlayingMoviesFound()
  {
    //given
    Mockito.when(repository.getMoviesNowPlaying()).thenReturn(Observable.just(new ResultMovie(MANY_MOVIES)));
  
    //when
    presenter.loadMoviesNowPlaying();
    
    //then
    Mockito.verify(view).displayMovies(MANY_MOVIES);
  }
  
  @Test
  public void nowPlayingNoMovies()
  {
    //given
    Mockito.when(repository.getMoviesNowPlaying()).thenReturn(Observable.just(new ResultMovie(Collections.emptyList())));
    
    //when
    presenter.loadMoviesNowPlaying();
  
    //then
    Mockito.verify(view).displayNoMovies();
  }
  
  @Test
  public void nowPlayingFailure()
  {
    Mockito.when(repository.getMoviesNowPlaying()).thenReturn(Observable.error(new RuntimeException("something's wrong")));
    
    presenter.loadMoviesNowPlaying();
    
    Mockito.verify(view).diplayError();
  }
}
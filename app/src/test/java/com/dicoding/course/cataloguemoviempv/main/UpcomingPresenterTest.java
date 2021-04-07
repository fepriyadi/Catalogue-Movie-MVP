package com.dicoding.course.cataloguemoviempv.main;

import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.model.ResultMovie;
import com.dicoding.course.cataloguemoviempv.presenter.UpcomingPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.UpcomingView;

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
 * Created by MK-fepriyadi on 1/28/2018.
 */
public class UpcomingPresenterTest
{
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  
  @Mock
  MovieRepository movieRepository;
  
  @Mock
  UpcomingView view;
  
  UpcomingPresenter presenter;
  private final List<Movie> MANY_MOVIES = Arrays.asList(new Movie(), new Movie(), new Movie());
  
  @Before
  public void setUp() throws Exception
  {
    presenter = new UpcomingPresenter(view, movieRepository, Schedulers.trampoline());
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
  }
  
  @After
  public void cleanUp() throws Exception
  {
    RxJavaPlugins.reset();
  }
  
  @Test
  public void upComingMoviesFound()
  {
    Mockito.when(movieRepository.getMoviesUpcoming()).thenReturn(Observable.just(new ResultMovie(MANY_MOVIES)));
    
    presenter.loadUpcomingMovies();
    
    Mockito.verify(view).displayMovies(MANY_MOVIES);
  }
  
  @Test
  public void upcomingNoMovies()
  {
    Mockito.when(movieRepository.getMoviesUpcoming()).thenReturn(Observable.just(new ResultMovie(Collections.emptyList())));
    
    presenter.loadUpcomingMovies();
    
    Mockito.verify(view).displayNoMovies();
  }
  
  @Test
  public void onFailure()
  {
    Mockito.when(movieRepository.getMoviesUpcoming()).thenReturn(Observable.error(new RuntimeException()));
    
    presenter.loadUpcomingMovies();
    
    Mockito.verify(view).displayError();
  }
  
  
}
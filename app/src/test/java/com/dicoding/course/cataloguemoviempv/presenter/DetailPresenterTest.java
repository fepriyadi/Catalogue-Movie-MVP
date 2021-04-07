package com.dicoding.course.cataloguemoviempv.presenter;

import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.repositories.DetailRepository;
import com.dicoding.course.cataloguemoviempv.view.DetailView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MK-fepriyadi on 2/1/2018.
 */
public class DetailPresenterTest
{
  private final Movie MOVIE = new Movie(128);
  private final RuntimeException exception = new RuntimeException("Error exeption");
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  
  @Mock
  DetailView view;
  
  @Mock
  DetailRepository detailRepository;
  
  DetailPresenter presenter;
  
  @Before
  public void setUp() throws Exception
  {
    presenter = new DetailPresenter(view, detailRepository, Schedulers.trampoline());
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
  }
  
  @After
  public void cleanUp()
  {
    RxJavaPlugins.reset();
  }
  
  @Test
  public void isFavourite()
  {
    Mockito.when(detailRepository.getMovieFav(MOVIE)).thenReturn(Observable.just(true));
    
    presenter.loadMovieFav(MOVIE);
    
    Mockito.verify(view).displayIsFavourite();
  }
  
  @Test
  public void isNotFavourite()
  {
    Mockito.when(detailRepository.getMovieFav(MOVIE)).thenReturn(Observable.just(false));
    
    presenter.loadMovieFav(MOVIE);
    
    Mockito.verify(view).displayNotFav();
  }
  
  @Test
  public void successToSave()
  {
    Mockito.when(detailRepository.saveMovieFav(MOVIE)).thenReturn(Observable.just(true));
    
    presenter.saveFavMovie(MOVIE);
    
    Mockito.verify(view).displaySuccessSave();
  }
  
  @Test
  public void failedToSave()
  {
    Mockito.when(detailRepository.saveMovieFav(MOVIE)).thenReturn(Observable.just(false));
    
    presenter.saveFavMovie(MOVIE);
    
    Mockito.verify(view).displayErrorSave();
  }
  
  @Test
  public void successToDelete()
  {
    Mockito.when(detailRepository.deleteMovieFav(MOVIE)).thenReturn(Observable.just(true));
    
    presenter.deleteFavMovie(MOVIE);
    
    Mockito.verify(view).displaySuccessRemove();
  }
  
  @Test
  public void failedToDelete()
  {
    Mockito.when(detailRepository.deleteMovieFav(MOVIE)).thenReturn(Observable.just(false));
    
    presenter.deleteFavMovie(MOVIE);
    
    Mockito.verify(view).displayErrorRemove();
  }
  
  @Test
  public void errorToGet()
  {
    Mockito.when(detailRepository.getMovieFav(MOVIE)).thenReturn(Observable.error(exception));
    
    presenter.loadMovieFav(MOVIE);
    
    Mockito.verify(view).displayErrorLoad();
  }
  
  @Test
  public void errorTosave()
  {
    Mockito.when(detailRepository.saveMovieFav(MOVIE)).thenReturn(Observable.error(exception));
    
    presenter.saveFavMovie(MOVIE);
    
    Mockito.verify(view).displayErrorSave();
  }
  
  @Test
  public void errorToDelete()
  {
    Mockito.when(detailRepository.deleteMovieFav(MOVIE)).thenReturn(Observable.error(exception));
    
    presenter.deleteFavMovie(MOVIE);
    
    Mockito.verify(view).displayErrorRemove();
  }
}
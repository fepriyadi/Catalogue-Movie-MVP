package com.dicoding.course.cataloguemoviempv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.dicoding.course.cataloguemoviempv.App;
import com.dicoding.course.cataloguemoviempv.R;
import com.dicoding.course.cataloguemoviempv.adapter.CardViewAdapter;
import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.presenter.SearchPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.SearchView;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by MK-fepriyadi on 1/27/2018.
 */

public class SearchFragment extends Fragment implements SearchView
{
  @Inject
  MovieRepository movieRepository;
  
  @Inject
  CardViewAdapter cardViewAdapter;
  
  @BindView(R.id.recyclerSearchResults)
  RecyclerView recyclerSearchResults;
  @BindView(R.id.searchView)
  FloatingSearchView searchView;
  Unbinder unbinder;

  private SearchPresenter presenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_search, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    
    ((App) getActivity().getApplication()).getAppComponent().inject(this);

    presenter = new SearchPresenter(this, movieRepository, AndroidSchedulers.mainThread());
    
    setupSearch();
  }
  
  @Override
  public void showProgress()
  {

  }

  @Override
  public void removeProgress()
  {

  }

  @Override
  public void noConnectionMsg()
  {

  }

  @Override
  public void displayMovies(List<Movie> movieList)
  {
    setupAdapter(movieList);
  }

  @Override
  public void displayNoMovies()
  {
    Toast.makeText(getContext(), R.string.movie_not_found, Toast.LENGTH_SHORT).show();
  }
  
  @Override
  public void displayError()
  {
  
  }
  
  private void setupAdapter(List<Movie> movieList)
  {
    recyclerSearchResults.setHasFixedSize(true);
    recyclerSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
    cardViewAdapter.setMovieList(movieList);
    recyclerSearchResults.setAdapter(cardViewAdapter);
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    unbinder.unbind();
    presenter.unsubscribe();
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
  }

  private void setupSearch()
  {
    searchView.setOnQueryChangeListener((oldQuery, newQuery) ->
    {
      if (!TextUtils.isEmpty(newQuery))
        presenter.loadMoviesSearch(newQuery);
    });
  }
}

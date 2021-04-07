package com.dicoding.course.cataloguemoviempv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.course.cataloguemoviempv.App;
import com.dicoding.course.cataloguemoviempv.R;
import com.dicoding.course.cataloguemoviempv.adapter.CardViewAdapter;
import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.presenter.UpcomingPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.view.UpcomingView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by MK-fepriyadi on 1/22/2018.
 */

public class UpComingFragment extends Fragment implements UpcomingView
{
  @Inject
  MovieRepository movieRepository;

  @Inject
  CardViewAdapter cardViewAdapter;
  
  @BindView(R.id.rv_upcoming)
  RecyclerView rvUpcoming;
  @BindView(R.id.progress_upcoming)
  ProgressBar progressUpcoming;
  Unbinder unbinder;

  private UpcomingPresenter presenter;

  public UpComingFragment()
  {
  }
  
  @Override
  public void onStart()
  {
    super.onStart();
    presenter.loadUpcomingMovies();
  }
  
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_upcoming, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);

    ((App) getActivity().getApplication()).getAppComponent().inject(this);

    presenter = new UpcomingPresenter(this, movieRepository, AndroidSchedulers.mainThread());
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    unbinder.unbind();
    presenter.unsubscribe();
  }
  
  @Override
  public void showProgress()
  {
    progressUpcoming.setVisibility(View.VISIBLE);
  }

  @Override
  public void removeProgress()
  {
    progressUpcoming.setVisibility(View.GONE);
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
    Toast.makeText(getActivity(), R.string.toast_no_movie_found, Toast.LENGTH_SHORT).show();
  }
  
  @Override
  public void displayError()
  {
    Toast.makeText(getContext(), "Error accessing data", Toast.LENGTH_SHORT).show();
  }
  
  private void setupAdapter(List<Movie> movieList)
  {
    rvUpcoming.setLayoutManager(new LinearLayoutManager(getContext()));
    rvUpcoming.setHasFixedSize(true);
    cardViewAdapter.setMovieList(movieList);
    rvUpcoming.setAdapter(cardViewAdapter);
  }
}

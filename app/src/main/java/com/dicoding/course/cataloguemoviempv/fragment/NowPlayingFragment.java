package com.dicoding.course.cataloguemoviempv.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.course.cataloguemoviempv.App;
import com.dicoding.course.cataloguemoviempv.R;
import com.dicoding.course.cataloguemoviempv.adapter.CardViewAdapter;
import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.presenter.NowPlayingPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.MovieRepository;
import com.dicoding.course.cataloguemoviempv.util.NowPlayingLoader;
import com.dicoding.course.cataloguemoviempv.util.NowPlayingPresenterFactoryImpl;
import com.dicoding.course.cataloguemoviempv.view.NowPlayingView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by MK-fepriyadi on 1/22/2018.
 */

public class NowPlayingFragment extends Fragment implements NowPlayingView
{
  private static final int LOADER_ID = 101;
  private static final String ARG_POSITION = "POSITION";
  @BindView(R.id.something_wrong)
  LinearLayout somethingWrong;
  @BindView(R.id.progress)
  ProgressBar progress;
  @BindView(R.id.rv_movies)
  RecyclerView rvMovies;
  Unbinder unbinder;
  
  @Inject
  MovieRepository movieRepository;
  
  @Inject
  CardViewAdapter cardViewAdapter;
  
  private static final String TAG = NowPlayingFragment.class.getSimpleName();
  private static final boolean DEBUG = true;
  private NowPlayingPresenter presenter;
  private int position;
  private ViewPager viewpager;
  
  public static NowPlayingFragment newInstance(int position)
  {
    Bundle args = new Bundle();
    args.putInt(ARG_POSITION, position);
    
    NowPlayingFragment fragment = new NowPlayingFragment();
    fragment.setArguments(args);
    return fragment;
  }
  
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    if (getArguments() != null)
    {
      position = getArguments().getInt(ARG_POSITION);
    }
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_now_playing , container, false);
  }
  
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    
    ((App) getActivity().getApplication()).getAppComponent().inject(this);
  }
  
  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    unbinder.unbind();
//    presenter.unsubscribeView();
  }
  
  @Override
  public void onStart()
  {
    super.onStart();
    presenter.onViewAttached(this);
    presenter.loadMoviesNowPlaying();
    Log.d(TAG, "onStart- is_presenter_null:" + String.valueOf(presenter == null));
  }
  
  @Override
  public void onResume()
  {
    super.onResume();
    Log.d(TAG, "onResume- is_presenter_null:" + String.valueOf(presenter == null));
  }
  
  @Override
  public void onStop()
  {
    super.onStop();
    presenter.dettachView();
//    presenter.unsubscribeView();
  }
  
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);
    
    Loader<NowPlayingPresenter> loader = getLoaderManager().getLoader(loaderId());
    if (loader == null)
    {
      initLoader();
    }
    else
    {
      this.presenter = ((NowPlayingLoader<NowPlayingPresenter>) loader).getPresenter();
      onPresenterCreated(presenter);
    }
  }
  
  private void initLoader()
  {
    getLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<NowPlayingPresenter>()
    {
      @Override
      public final Loader<NowPlayingPresenter> onCreateLoader(int id, Bundle args)
      {
        return new NowPlayingLoader<>(getContext(), getPresenterFactory());
      }
      
      @Override
      public final void onLoadFinished(Loader<NowPlayingPresenter> loader, NowPlayingPresenter presenter)
      {
        NowPlayingFragment.this.presenter = presenter;
        onPresenterRestored(presenter);
      }
      
      @Override
      public final void onLoaderReset(Loader<NowPlayingPresenter> loader)
      {
        NowPlayingFragment.this.presenter = null;
      }
    });
  }
  
  private void onPresenterRestored(NowPlayingPresenter presenter)
  {
    this.presenter = presenter;
  }
  
  private Integer tag()
  {
    return position;
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
  public void diplayError()
  {
    progress.setVisibility(View.GONE);
    somethingWrong.setVisibility(View.VISIBLE);
    Toast.makeText(getActivity(), R.string.msg_wrong_while_getting_data, Toast.LENGTH_SHORT).show();
  }
  
  private void setupAdapter(List<Movie> movieList)
  {
    rvMovies.setHasFixedSize(true);
    rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
    cardViewAdapter.setMovieList(movieList);
    rvMovies.setAdapter(cardViewAdapter);
  }
  
  @Override
  public void showProgress()
  {
    progress.setVisibility(View.VISIBLE);
  }
  
  @Override
  public void removeProgress()
  {
    progress.setVisibility(View.GONE);
  }
  
  @Override
  public void noConnectionMsg()
  {
  
  }
  
  protected int loaderId()
  {
    return LOADER_ID;
  }
  
  private NowPlayingPresenterFactoryImpl getPresenterFactory()
  {
    return new NowPlayingPresenterFactoryImpl(movieRepository, mainThread());
  }
  
  private void onPresenterCreated(@NonNull NowPlayingPresenter presenter)
  {
    this.presenter = presenter;
  }
}

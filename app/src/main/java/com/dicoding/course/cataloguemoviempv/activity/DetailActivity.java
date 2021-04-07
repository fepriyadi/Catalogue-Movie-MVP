package com.dicoding.course.cataloguemoviempv.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dicoding.course.cataloguemoviempv.App;
import com.dicoding.course.cataloguemoviempv.BuildConfig;
import com.dicoding.course.cataloguemoviempv.R;
import com.dicoding.course.cataloguemoviempv.model.Movie;
import com.dicoding.course.cataloguemoviempv.presenter.DetailPresenter;
import com.dicoding.course.cataloguemoviempv.repositories.DetailRepository;
import com.dicoding.course.cataloguemoviempv.view.DetailView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.dicoding.course.cataloguemoviempv.activity.MainActivity.BUNDE_MOVIE;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public class DetailActivity extends AppCompatActivity implements DetailView
{
  
  private static final String TAG = DetailActivity.class.getSimpleName();
  private static final boolean DEBUG = true;
  @BindView(R.id.img_movie)
  ImageView imgMovie;
  @BindView(R.id.tv_title)
  TextView tvTitle;
  @BindView(R.id.ck_fav)
  CheckBox ckFav;
  @BindView(R.id.tv_release)
  TextView tvRelease;
  @BindView(R.id.tv_Xoverview)
  TextView tvXoverview;
  @BindView(R.id.tv_overview)
  TextView tvOverview;
  @BindView(R.id.tv_vote_average)
  TextView tvVoteAverage;
  @BindView(R.id.tv_vote_count)
  TextView tvVoteCount;
  @BindView(R.id.lnr_detail)
  LinearLayout lnrDetail;
  @BindView(R.id.lnr_progress)
  LinearLayout lnrProgress;
  @BindView(R.id.activity_detail)
  LinearLayout activityDetail;
  @Nullable
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  
  @Inject
  DetailRepository detailRepository;
  
  private Bundle bundle = new Bundle();
  private Movie movie = new Movie();
  private DetailPresenter detailPresenter;
  
  @SuppressLint("RestrictedApi")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    if (DEBUG)
      Log.d(TAG, "ON CREATE ");
    
    init();
    
    detailPresenter = new DetailPresenter(this, detailRepository, AndroidSchedulers.mainThread());
    
    checkBundleData();
    
    ckFav.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        if (ckFav.isChecked())
          detailPresenter.saveFavMovie(movie);
        else
          detailPresenter.deleteFavMovie(movie);
      }
    });
  }
  
  @Override
  public boolean onSupportNavigateUp()
  {
    onBackPressed();
    return true;
  }
  
  @Override
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  private void init()
  {
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null)
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    
    ((App) getApplication()).getAppComponent().inject(this);
  }
  
  private void checkBundleData()
  {
    bundle = getIntent().getExtras();
    if (bundle != null && bundle.getSerializable(BUNDE_MOVIE) != null)
    {
      movie = (Movie) bundle.getSerializable(BUNDE_MOVIE);
      detailPresenter.loadMovieFav(movie);
      setData(movie);
    }
  }
  
  @Override
  public void showProgress()
  {
    lnrProgress.setVisibility(View.VISIBLE);
  }
  
  @Override
  public void removeProgress()
  {
    lnrProgress.setVisibility(View.GONE);
  }
  
  @Override
  public void noConnectionMsg()
  {
  
  }
  
  @Override
  public void displayIsFavourite()
  {
    ckFav.setChecked(true);
  }
  
  @Override
  public void displayNotFav()
  {
    ckFav.setChecked(false);
  }
  
  @Override
  public void displayErrorLoad()
  {
    Toast.makeText(this, "Error accessing data", Toast.LENGTH_SHORT).show();
    finish();
  }
  
  @Override
  public void displaySuccessSave()
  {
    Toast.makeText(this, "Success saving to favourite", Toast.LENGTH_SHORT).show();
  }
  
  @Override
  public void displayErrorSave()
  {
    ckFav.setChecked(false);
    Toast.makeText(this, "Error saving to favourite", Toast.LENGTH_SHORT).show();
  }
  
  @Override
  public void displaySuccessRemove()
  {
    Toast.makeText(this, "Success removing from favourite", Toast.LENGTH_SHORT).show();
  }
  
  @Override
  public void displayErrorRemove()
  {
    Toast.makeText(this, "Error removing from favourite", Toast.LENGTH_SHORT).show();
  }
  
  private void setData(Movie movie)
  {
    tvTitle.setText(movie.getTitle());
    tvOverview.setText(movie.getOverview());
    tvRelease.setText(movie.getRelease_date());
    tvVoteAverage.setText(String.valueOf(movie.getVote_average()));
    tvVoteCount.setText(String.valueOf(movie.getVote_count()));
    
    Glide.with(DetailActivity.this)
      .load(BuildConfig.IMAGE_URL + movie.getPoster_path())
      .dontTransform()
      .error(R.drawable.noimageplaceholder)
      .into(imgMovie);
  }
}

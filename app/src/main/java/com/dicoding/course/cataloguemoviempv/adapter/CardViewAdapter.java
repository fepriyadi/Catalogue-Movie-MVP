package com.dicoding.course.cataloguemoviempv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.course.cataloguemoviempv.BuildConfig;
import com.dicoding.course.cataloguemoviempv.R;
import com.dicoding.course.cataloguemoviempv.activity.DetailActivity;
import com.dicoding.course.cataloguemoviempv.activity.MainActivity;
import com.dicoding.course.cataloguemoviempv.model.Movie;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by MK-fepriyadi on 1/22/2018.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder>
{
  private static final String TAG = CardViewAdapter.class.getSimpleName();
  private Context context;
  private List<Movie> movieList;
  
  public CardViewAdapter(Context context)
  {
    this.context = context;
  }
  
  public void setMovieList(List<Movie> list)
  {
    this.movieList = list;
  }
  
  @Override
  public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
    return new CardViewHolder(view);
  }
  
  @Override
  public void onBindViewHolder(CardViewHolder holder, int position)
  {
    final Movie movie = movieList.get(position);
    
    Glide.with(context)
      .load(BuildConfig.IMAGE_URL + movie.getPoster_path())
      .override(350, 550)
      .into(holder.imageView);
    
    holder.tvName.setText(movie.getTitle());
    holder.tvOverView.setText(movie.getOverview());
    holder.tvDate.setText(movie.getRelease_date());
    holder.btnDetail.setOnClickListener(v ->
    {
      context.startActivity(new Intent(context, DetailActivity.class)
        .putExtra(MainActivity.BUNDE_MOVIE, movie)
        .addFlags(FLAG_ACTIVITY_NEW_TASK));
    });
  }
  
  @Override
  public int getItemCount()
  {
    return movieList != null ? movieList.size() : 0;
  }
  
  
  public class CardViewHolder extends RecyclerView.ViewHolder
  {
    ImageView imageView;
    TextView tvName, tvOverView, tvDate;
    Button btnDetail, btnShare;
    
    public CardViewHolder(View itemView)
    {
      super(itemView);
      
      imageView = itemView.findViewById(R.id.img_item_photo);
      tvName = itemView.findViewById(R.id.tv_item_name);
      tvOverView = itemView.findViewById(R.id.tv_item_review);
      tvDate = itemView.findViewById(R.id.tv_item_date_release);
      btnDetail = itemView.findViewById(R.id.btn_set_favorite);
      btnShare = itemView.findViewById(R.id.btn_set_share);
      
    }
  }
}

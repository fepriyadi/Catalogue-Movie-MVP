package com.dicoding.course.cataloguemoviempv.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public class MovieLikeAnimator extends RecyclerView.ItemAnimator
{
  @Override
  public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo)
  {
    return false;
  }
  
  @Override
  public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo)
  {
    return false;
  }
  
  @Override
  public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo)
  {
    return false;
  }
  
  @Override
  public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo)
  {
    return false;
  }
  
  @Override
  public void runPendingAnimations()
  {
  
  }
  
  @Override
  public void endAnimation(RecyclerView.ViewHolder item)
  {
  
  }
  
  @Override
  public void endAnimations()
  {
  
  }
  
  @Override
  public boolean isRunning()
  {
    return false;
  }
}

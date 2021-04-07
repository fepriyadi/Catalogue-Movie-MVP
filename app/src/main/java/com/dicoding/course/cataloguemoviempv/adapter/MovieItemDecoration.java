package com.dicoding.course.cataloguemoviempv.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public class MovieItemDecoration extends RecyclerView.ItemDecoration
{
  private int offset;
  
  public MovieItemDecoration(int offset)
  {
    this.offset = offset;
  }
  
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
  {
  
  }
}

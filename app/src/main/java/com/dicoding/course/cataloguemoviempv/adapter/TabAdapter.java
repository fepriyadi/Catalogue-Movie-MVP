package com.dicoding.course.cataloguemoviempv.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dicoding.course.cataloguemoviempv.fragment.FavouriteFragment;
import com.dicoding.course.cataloguemoviempv.fragment.NowPlayingFragment;
import com.dicoding.course.cataloguemoviempv.fragment.SearchFragment;
import com.dicoding.course.cataloguemoviempv.fragment.UpComingFragment;

/**
 * Created by MK-fepriyadi on 1/22/2018.
 */

public class TabAdapter extends FragmentPagerAdapter
{
  private Context context;
  private int pageCount;
  
  public TabAdapter(FragmentManager fragmentManager, int pageCount)
  {
    super(fragmentManager);
    this.pageCount = pageCount;
  }
  
  @Override
  public Fragment getItem(int position)
  {
    switch (position)
    {
      case 0:
        return NowPlayingFragment.newInstance(position);
      case 1:
        return new UpComingFragment();
      case 2:
        return new SearchFragment();
      case 3:
        return new FavouriteFragment();
    }
    return null;
  }
  
  @Override
  public int getCount()
  {
    return pageCount;
  }
}

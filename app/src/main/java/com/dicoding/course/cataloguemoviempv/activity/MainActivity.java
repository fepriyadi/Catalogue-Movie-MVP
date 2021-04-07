package com.dicoding.course.cataloguemoviempv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dicoding.course.cataloguemoviempv.R;
import com.dicoding.course.cataloguemoviempv.adapter.TabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
  public static final String BUNDE_MOVIE = "MOVIE";
  @BindView(R.id.viewPager)
  ViewPager viewPager;
  @BindView(R.id.navigationView)
  BottomNavigationView navigationView;
  
  private FragmentManager fragmentManager;
  private TabAdapter tabAdapter;
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    
    setupViewPager();
    
    setupNavigationBar();
    
  }
  
  
  private void setupNavigationBar()
  {
    navigationView.setOnNavigationItemSelectedListener(item ->
    {
      int itemSelected = 0;
      switch (item.getItemId())
      {
        case R.id.now_playing:
          itemSelected = 0;
          break;
        case R.id.up_coming:
          itemSelected = 1;
          break;
        case R.id.search:
          itemSelected = 2;
          break;
        case R.id.favourite:
          itemSelected = 3;
          break;
      }
      
      viewPager.setCurrentItem(itemSelected);
      return true;
    });
  }
  
  private void setupViewPager()
  {
    int menuSize = navigationView.getMenu().size();
    
    tabAdapter = new TabAdapter(getSupportFragmentManager(), menuSize);
    
    viewPager.setAdapter(tabAdapter);
    
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
      {
      
      }
      
      @Override
      public void onPageSelected(int position)
      {
        navigationView.getMenu().getItem(position).setChecked(true);
      }
      
      @Override
      public void onPageScrollStateChanged(int state)
      {
      
      }
    });
  }
  
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
  }
}

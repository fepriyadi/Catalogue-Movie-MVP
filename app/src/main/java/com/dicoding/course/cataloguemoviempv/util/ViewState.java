package com.dicoding.course.cataloguemoviempv.util;

/**
 * Created by MK-fepriyadi on 2/4/2018.
 */
public class ViewState
{
  private boolean loadingState;
  private boolean isViewLoadedAtLeastOnceWithValidData;
  
  public void setLoadingState(boolean loadingState)
  {
    this.loadingState = loadingState;
  }
  
  public boolean isLoadingState()
  {
    return loadingState;
  }
  public ViewState()
  {
    this.loadingState = false;
    this.isViewLoadedAtLeastOnceWithValidData = false;
  }
  
  public boolean isViewLoadedAtLeastOnceWithValidData()
  {
    return isViewLoadedAtLeastOnceWithValidData;
  }
  
  public void setViewLoadedAtLeastOnceWithValidData(boolean viewLoadedAtLeastOnceWithValidData)
  {
    isViewLoadedAtLeastOnceWithValidData = viewLoadedAtLeastOnceWithValidData;
  }
  
}

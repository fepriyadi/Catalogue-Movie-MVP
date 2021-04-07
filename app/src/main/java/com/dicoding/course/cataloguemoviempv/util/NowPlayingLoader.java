package com.dicoding.course.cataloguemoviempv.util;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by MK-fepriyadi on 1/28/2018.
 */

public class NowPlayingLoader<T> extends Loader<T>
{
  private final PresenterFactory<T> factory;
  private T presenter;
  
  public NowPlayingLoader(Context context, PresenterFactory<T> factory)
  {
    super(context);
    this.factory = factory;
  }
  
  @Override
  protected void onStartLoading()
  {
    if (presenter != null)
    {
      deliverResult(presenter);
      return;
    }
    forceLoad();
  }
  
  @Override
  public void deliverResult(T data)
  {
    super.deliverResult(data);
  }
  
  @Override
  public void forceLoad()
  {
    presenter = factory.create();
    deliverResult(presenter);
  }
  
  @Override
  protected void onReset() {
    if (presenter != null) {
      presenter = null;
    }
  }
  
  public T getPresenter() {
    return presenter;
  }
}

package com.dicoding.course.cataloguemoviempv.base;

import com.dicoding.course.cataloguemoviempv.network.NetworkService;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by MK-fepriyadi on 1/16/2018.
 */

public class BasePresenter<V>
{
  protected V view;
  protected NetworkService networkService;
  private CompositeDisposable composite;
  
  protected void attachView(V view)
  {
    this.view = view;
//    networkService = builder().create(NetworkService.class);
  }
  
   void dettachView()
   {
     this.view = null;
     if (composite != null)
       composite.clear();
   }
   
   protected void subscribe(Disposable disposable)
   {
     if (composite == null)
       composite = new CompositeDisposable();
     composite.add(disposable);
   }
}

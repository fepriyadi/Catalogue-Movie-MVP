package com.dicoding.course.cataloguemoviempv;

import android.app.Application;

import com.dicoding.course.cataloguemoviempv.dagger.AppComponent;
import com.dicoding.course.cataloguemoviempv.dagger.AppModule;
import com.dicoding.course.cataloguemoviempv.dagger.DaggerAppComponent;
import com.dicoding.course.cataloguemoviempv.network.NetworkModule;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.File;

import io.realm.Realm;

/**
 * Created by MK-fepriyadi on 1/27/2018.
 */

public class App extends Application
{
  private AppComponent appComponent;
  
  @Override
  public void onCreate()
  {
    super.onCreate();
    //Realm
    Realm.init(this);
    
    //Stetho
    Stetho.initialize(
      Stetho.newInitializerBuilder(this)
        .enableDumpapp(
          Stetho.defaultDumperPluginsProvider(this)
        )
        .enableWebKitInspector(
          RealmInspectorModulesProvider
            .builder(this)
            .build()
        )
        .build());
    
    //Dagger
    File cacheFile = new File(getCacheDir(), "responses");
    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
      .networkModule(new NetworkModule(cacheFile)).build();
    appComponent.inject(this);
    
  }
  
  public AppComponent getAppComponent()
  {
    return appComponent;
  }
}

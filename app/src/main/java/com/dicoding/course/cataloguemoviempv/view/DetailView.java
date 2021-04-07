package com.dicoding.course.cataloguemoviempv.view;

/**
 * Created by MK-fepriyadi on 1/31/2018.
 */

public interface DetailView extends BaseView
{
  void displayIsFavourite();
  void displayNotFav();
  void displayErrorLoad();
  void displaySuccessSave();
  void displayErrorSave();
  void displaySuccessRemove();
  void displayErrorRemove();
}

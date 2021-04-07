package com.dicoding.course.cataloguemoviempv.model;

import java.io.Serializable;

/**
 * Created by MK-fepriyadi on 10/8/2017.
 */

class Language implements Serializable
{
  private String iso_639_1;
  private String name;
  
  public String getIso_639_1()
  {
    return iso_639_1;
  }
  
  public void setIso_639_1(String iso_639_1)
  {
    this.iso_639_1 = iso_639_1;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
}

package com.dicoding.course.cataloguemoviempv.model;

import java.io.Serializable;

/**
 * Created by MK-fepriyadi on 10/8/2017.
 */

class Company implements Serializable
{
  private String name;
  private int id;
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
}

package com.dicoding.course.cataloguemoviempv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MK-fepriyadi on 10/8/2017.
 */

public class Waktu implements Serializable
{
  private Date maximum;
  
  public Date getMaximum()
  {
    return maximum;
  }
  
  public void setMaximum(Date maximum)
  {
    this.maximum = maximum;
  }
  
  public Date getMinimum()
  {
    return minimum;
  }
  
  public void setMinimum(Date minimum)
  {
    this.minimum = minimum;
  }
  
  private Date minimum;
}

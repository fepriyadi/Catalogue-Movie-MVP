package com.dicoding.course.cataloguemoviempv.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MK-fepriyadi on 1/26/2018.
 */

public class Response
{
  @SerializedName("status")
  public String status;
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public String getStatus() {
    return status;
  }
  
  @SuppressWarnings({"unused", "used by Retrofit"})
  public Response() {
  }
  
  public Response(String status) {
    this.status = status;
  }
}

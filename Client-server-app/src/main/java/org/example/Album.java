package org.example;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

class Album {
  public String title;
  public String message;
  public List errors = new ArrayList();
  public String total;
  public int total_pages;
  public int page;
  public String limit;
  List dataset = new ArrayList();
}

class AlbumImages {
  public String image_id;
  public String user_id;
  public String albumId;
}

class Dataset {
  public String album_id;
  public String album_title;
  @SerializedName("album_images")
  List<AlbumImages> images = new ArrayList<AlbumImages>();
}


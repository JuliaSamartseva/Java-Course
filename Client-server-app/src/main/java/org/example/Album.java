package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
  private String title;
  private String artist;
  private int year;

  public Album(String title, String artist, int year) {
    this.title = title;
    this.artist = artist;
    this.year = year;
  }

  @Override
  public boolean equals(Object album) {
    if (this == album) return true;
    if (album == null || getClass() != album.getClass()) return false;
    Album that = (Album) album;
    return title.equals(that.title) &&
        artist.equals(that.artist) &&
        year == that.year;
  }

  @Override
  public String toString() {
    return "Title: " + title + " artist: " + artist + " year: " + Integer.toString(year);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}

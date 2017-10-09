package com.jusenr.androidgithub.home.model.model;

import java.io.Serializable;

/**
 * Created by Bernat on 20/07/2014.
 */
public class ShaUrlModel implements Serializable {

  private static final int MAX_SHA_LENGHT = 8;
  public String sha;
  public String url;
  public String html_url;

  public String getSha() {
    return sha;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getHtml_url() {
    return html_url;
  }

  public void setHtml_url(String html_url) {
    this.html_url = html_url;
  }
}

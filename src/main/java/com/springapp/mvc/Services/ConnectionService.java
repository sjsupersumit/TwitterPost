package com.springapp.mvc.Services;

import org.json.simple.JSONObject;

/**
 * Created by sumit.jha on 5/19/16.
 */
public interface ConnectionService {

  public String executeGet(String webServiceUrl, String auth);
}

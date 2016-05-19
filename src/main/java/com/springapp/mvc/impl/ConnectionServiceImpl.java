package com.springapp.mvc.impl;

import com.springapp.mvc.Services.ConnectionService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sumit.jha on 5/19/16.
 */
@Service
public class ConnectionServiceImpl implements ConnectionService {

  @Override
  public String executeGet(String webServiceUrl, String auth) {
    if (webServiceUrl == null || webServiceUrl.isEmpty()) {
      return null;
    }

    JSONObject jsonResponse = null;
    StringBuilder response = new StringBuilder();

    try {
      URL url = new URL(webServiceUrl);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      if (auth != null && !auth.isEmpty()) {
        urlConnection.setRequestProperty("Authorization", auth);
      }
      int responseCode = urlConnection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;


        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        //print result
//        System.out.println(response.toString());
//        return response.toString();

      }

    } catch (MalformedURLException exp) {
      System.err.println(exp);
    } catch (IOException ioe) {
      System.err.println(ioe);
    } catch (Exception exp) {
      System.err.println(exp);
    }

    return response.toString();
  }

}

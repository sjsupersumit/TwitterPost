package com.springapp.mvc.impl;

import com.springapp.mvc.Services.ConnectionService;
import com.springapp.mvc.Services.TwitterService;
import com.springapp.mvc.constants.UrlConstants;
import org.hsqldb.lib.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sumit.jha on 5/18/16.
 */

@Service
public class TwitterServiceImpl implements TwitterService {

  @Autowired
  private ConnectionService connectionService;

  @Override
  public List<Date> getAllTweetsDateByUser(Long userId) {
    if (userId == null) {
      return null;
    }

    List<Date> allTweetsDateList = new ArrayList<Date>();
    String userTweetsUrl = UrlConstants.BASE_API_URL + "statuses/user_timeline.json?count=200&user_id=" + userId;

    String response = getConnectionService().executeGet(userTweetsUrl, UrlConstants.ACCESS_TOKEN);
    JSONParser parser = new JSONParser();
    try {
      JSONArray jsonArray = (JSONArray) parser.parse(response);
      Iterator itr = jsonArray.iterator();
      while (itr.hasNext()) {
        JSONObject obj = (JSONObject) itr.next();
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date = (Date) formatter.parse((String) obj.get("created_at"));
        allTweetsDateList.add(date);
      }

    } catch (Exception e) {
      System.err.println(e);
    }

    return allTweetsDateList;
  }

  @Override
  public List<Long> getAllFollowersByUserIdOrName(Long userId, String userName) {

    if (userId == null && userName == null) {
      return null;
    }

    List<Long> followerIdList = new ArrayList<Long>();

    String followersUrls = UrlConstants.BASE_API_URL + "followers/ids.json?cursor=-1&count=100";
    if (userId != null) {
      followersUrls = followersUrls + "&user_id=" + userId;
    } else {
      followersUrls = followersUrls + "&screen_name=" + userName;
    }

    try {
      JSONParser parser = new JSONParser();
      JSONObject jsonResponse = (JSONObject) parser.parse(getConnectionService().executeGet(followersUrls, UrlConstants.ACCESS_TOKEN));
      org.json.simple.JSONArray followerIds = (JSONArray) jsonResponse.get("ids");
      Iterator itr = followerIds.iterator();
      while (itr.hasNext()) {
        followerIdList.add((Long) itr.next());
      }
    } catch (Exception e) {
      System.err.println(e);
    }
    return followerIdList;
  }


  @SuppressWarnings("deprecation")
  @Override
  public String getBestTimeForPost(Long userId, String userName) {
    if (userId == null && StringUtil.isEmpty(userName)) {
      return null;
    }

    List<Long> followerIds = getAllFollowersByUserIdOrName(userId, userName);
    List<Date> tweetDateList = new ArrayList<Date>();
    if (followerIds != null && !followerIds.isEmpty()) {

      for (Long fId : followerIds) {
        List<Date> dtList = getAllTweetsDateByUser(fId);
        if (dtList != null && !dtList.isEmpty()) {
          tweetDateList.addAll(dtList);
        }

      }
    }

    //contains hour range with count
    Map<Integer, Integer> timeRangeMap = new HashMap<Integer, Integer>();
    Map<String, Integer> dayMap = new HashMap<String, Integer>();

    String bestTime = "";
    for (Date dt : tweetDateList) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(dt);
      Integer hour = dt.getHours();
      String day = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);

      if (timeRangeMap.get(hour) != null) {
        timeRangeMap.put(hour, timeRangeMap.get(hour) + 1);
      } else {
        timeRangeMap.put(hour, 1);
      }

      if (dayMap.get(day) != null) {
        dayMap.put(day, dayMap.get(day) + 1);
      } else {
        dayMap.put(day, 1);
      }


    }

    Integer maxHour = 0, maxCount = 0, dayCnt = 0;
    String day = "";

    for (Integer key : timeRangeMap.keySet()) {
      Integer cnt = timeRangeMap.get(key);
      if (cnt > maxCount) {
        maxCount = cnt;
        maxHour = key;
      }
    }
    Integer endHour = maxHour + 1;
    bestTime = maxHour + "-" + endHour + " hrs On ";
    maxCount = 0;

    for (String key : dayMap.keySet()) {
      Integer cnt = dayMap.get(key);
      if (cnt > dayCnt) {
        dayCnt = cnt;
        day = key;
      }
    }


    return bestTime + day;
  }


  public ConnectionService getConnectionService() {
    return connectionService;
  }
}


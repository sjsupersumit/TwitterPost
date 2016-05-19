package com.springapp.mvc.Services;

import java.util.Date;
import java.util.List;

/**
 * Created by sumit.jha on 5/18/16.
 */


public interface TwitterService {

  List<Date> getAllTweetsDateByUser(Long userId);

  List<Long> getAllFollowersByUserIdOrName(Long userId, String userName);

  String getBestTimeForPost(Long userId, String userName);


}

package com.springapp.mvc;

import com.springapp.mvc.Services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterController {

  @Autowired
  private TwitterService twitterService;
  private String bestTime;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String showWelcomePage(ModelMap model) {
    model.addAttribute("twitterUser", new TwitterUser());
    return "welcome";
  }

  @RequestMapping(value = "/time", method = RequestMethod.POST)
  public String getBestTimeForPost(@ModelAttribute("twitterUser") TwitterUser twitterUser, BindingResult result,
                                   ModelMap model) {

    Long userId = twitterUser.getUserId();
    String userName = twitterUser.getUserName();
    bestTime = getTwitterService().getBestTimeForPost(userId, userName);
    model.addAttribute("bestTime", bestTime);
    return "userTweetDetail";
  }


  public TwitterService getTwitterService() {
    return twitterService;
  }

  public String getBestTime() {
    return bestTime;
  }

  public void setBestTime(String bestTime) {
    this.bestTime = bestTime;
  }
}
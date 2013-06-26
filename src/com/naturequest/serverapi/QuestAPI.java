package com.naturequest.serverapi;

import android.util.Log;

import com.loopj.android.http.*;
import com.naturequest.Game;
import com.naturequest.User;

public class QuestAPI {
	

  private static final String BASE_URL = "http://nature-quest.herokuapp.com/api/";
  
  private static AsyncHttpClient client = new AsyncHttpClient();

  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	  Log.d("URL",getAbsoluteUrl(url));
	  
	  if(!url.equals("login.json") && !url.equals("signup.json")){
		  User currUser = Game.getGame().getCurrentUser();		  
		  params.put("auth_token", currUser.getToken());
	  }
	  
      client.get(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
      
	 
	  if(!url.equals("login.json") && !url.equals("signup.json")){
		  User currUser = Game.getGame().getCurrentUser();		  
		  params.put("auth_token", currUser.getToken());
	  }
	  client.post(getAbsoluteUrl(url), params, responseHandler);
  }

  private static String getAbsoluteUrl(String relativeUrl) {
      return BASE_URL + relativeUrl;
  }
}
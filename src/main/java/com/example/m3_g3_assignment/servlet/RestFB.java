package com.example.m3_g3_assignment.servlet;

import java.io.IOException;

import com.example.m3_g3_assignment.model.Facebook;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

public class RestFB {
    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(Facebook.FACEBOOK_LINK_GET_TOKEN, Facebook.FACEBOOK_APP_ID, Facebook.FACEBOOK_APP_SECRET, Facebook.FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject job = new Gson().fromJson(response, JsonObject.class);
        return job.get("access_token").toString().replaceAll("\"", "");
    }

    public static User getUserInfo(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Facebook.FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", User.class);
    }
}
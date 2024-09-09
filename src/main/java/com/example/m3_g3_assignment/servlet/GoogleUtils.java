package com.example.m3_g3_assignment.servlet;

import com.example.m3_g3_assignment.model.Goggle;
import com.example.m3_g3_assignment.model.GooglePojo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class GoogleUtils {
    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Goggle.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Goggle.GOOGLE_CLIENT_ID)
                        .add("client_secret", Goggle.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri",Goggle.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Goggle.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        return jobj.get("access_token").toString().replaceAll("\"", "");
    }

    public static GooglePojo getUserInfoGoogle(final String accessToken) throws ClientProtocolException, IOException {
        String link = Goggle.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
        System.out.println(googlePojo);
        return googlePojo;
    }
}

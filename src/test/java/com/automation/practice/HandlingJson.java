package com.automation.practice;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class HandlingJson {

    public static JSONObject HttpGetResponseAsJSON(String url, String auth) {
        JSONObject obj = null;

        try {
            HttpResponse<JsonNode> response = Unirest
                    .get(url)
                    .header("Authorization", auth)
                    .asJson();

            int responsecode = response.getStatus();

            if (responsecode == 200) {
                obj = response.getBody().getObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String HttpGetResponseAsString(String url, String auth) {
        String obj = null;

        try {
            HttpResponse<String> response = Unirest.get(url).header("Authorization", auth).asString();

            int responsecode = response.getStatus();

            if (responsecode == 200) {
                obj = response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String HttpPostResponseAsString(String url, String auth) {
        String obj = null;
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("Authorization", auth)
                    .header("Cache-Control", "no-cache").asString();

            int responsecode = response.getStatus();

            if (responsecode == 200) {
                obj = response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}

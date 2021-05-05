package com.coolcats.apicall.network;

import android.util.Log;

import com.coolcats.apicall.model.GitResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GitNetwork {

    public static List<GitResponse> getRepos(String userName) {

        try {
            URL url = new URL("https://api.github.com/users/" + userName + "/repos");
            HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String value;
            StringBuilder bldr = new StringBuilder();

            while((value = bufferedReader.readLine()) != null){
                bldr.append(value);
            }

            connection.disconnect();

            Gson gSon = new Gson();
            return Arrays.asList(gSon.fromJson(bldr.toString(), GitResponse[].class));

        } catch (Exception e) {
            Log.d("TAG_X", e.toString());
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}

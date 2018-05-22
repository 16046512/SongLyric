package com.example.a16046512.songlyric;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lyric extends AppCompatActivity {
    String apikey = "RweP5FaJjW9UVxbYurrbHgJZcu05WXAXyzByin13yQSoQQIyzgtWYdlGBYikNq3l";
    String artist,songname;
    TextView showlyric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        String[] info = i.getStringArrayExtra("info");
         artist = info[0];
         songname = info[1];

    }

    protected void onResume() {
        super.onResume();

        Log.i("ttt","https://orion.apiseeds.com/api/music/lyric/" + artist + "/" + songname + "?apikey=" + apikey);
        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("https://orion.apiseeds.com/api/music/lyric/" + artist + "/" + songname + "?apikey=" + apikey);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end

    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {


                    // process response here
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject result = jsonObject.getJSONObject("result");

                        JSONObject artist = result.getJSONObject("artist");
                        String artistname = artist.getString("name");

                        JSONObject track = result.getJSONObject("track");
                        String songname = track.getString("name");
                        String lyric = track.getString("text");

                        JSONObject lang = track.getJSONObject("lang");
                        String languagename = lang.getString("name");

                        String show = "Song Name: "+songname+"\nArtist Name: "+artistname+"\nLanguage: "+languagename+"\n\n"+lyric;
                        showlyric = (TextView) findViewById(R.id.showlyric);
                        showlyric.setText(show);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            };
}

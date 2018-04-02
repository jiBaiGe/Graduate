package com.example.ye.graduate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String USGS_REQUEST_URL =
            "https://free-api.heweather.com/s6/weather?location=shanghai&key=598506f88fe1497a9afbf6183d6968b8&lang=zh&unit=m&format=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView go = (ImageView ) findViewById(R.id.hello);
//
//// Set a click listener on that View
//        go.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers View is clicked on.
//            @Override
//            public void onClick(View view) {
//                Intent Intent = new Intent(MainActivity.this, list.class);
//                startActivity(Intent);
//            }
//        });

        TsunamiAsyncTask task = new TsunamiAsyncTask();
        task.execute();
//        TimerTask task = new TimerTask() {
//
//            @Override
//            public void run() {
//
//                Intent Intent = new Intent(MainActivity.this, list.class);
//                startActivity(Intent);
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(task, 1500);



    }


    private class TsunamiAsyncTask extends AsyncTask<URL, Void, City> {

        @Override
        protected City doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(USGS_REQUEST_URL);
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
            }
            City weather = extractFeatureFromJson(jsonResponse);
            return weather;
        }

        @Override
        protected void onPostExecute(City wether) {
            if (wether == null) {
                return;
            }
            gonext(wether);
        }

        private void gonext(City weather) {
            Intent Intent = new Intent(MainActivity.this, list.class);
            Intent.putExtra("tmp", weather.getTmp());
            Intent.putExtra("cond", weather.getCond());
            Intent.putExtra("name", weather.getName());
            Intent.putExtra("date", weather.getDate());
            startActivity(Intent);
        }

        }
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                return null;
            }
            return url;
        }
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
        private City extractFeatureFromJson(String earthquakeJSON) {
            try {
                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
                JSONArray featureArray = baseJsonResponse.getJSONArray("HeWeather6");

                // If there are results in the features array
                if (featureArray.length() > 0) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = featureArray.getJSONObject(0);
                    JSONObject properties = firstFeature.getJSONObject("now");

                    // Extract out the title, time, and tsunami values
                    String cond = properties.getString("cond_txt");
                    String tmp = properties.getString("tmp");
                    String date = properties.getString("cond_txt");


                    // Create a new {@link Event} object
                    return new City("上海", cond, tmp,date);
                }
            } catch (JSONException e) {
            }
            return null;
        }
}

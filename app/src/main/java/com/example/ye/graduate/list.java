package com.example.ye.graduate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Random;


public class list extends AppCompatActivity {
    ArrayList<City> cp;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i1 = getIntent();
        setContentView(R.layout.activity_list);
        Random random = new Random();
        final ArrayList<City> theCity = new ArrayList<City>();

            String nowCity = "上海";
            String nowDate = "明天";
            String nowTmp = i1.getStringExtra("tmp")+"°";
            String nowCond = i1.getStringExtra("cond");
//            String nowTmp = i1.getStringExtra("tmp");



        listView = (ListView) findViewById(R.id.list);

            final CityAdapter Adapter =
                    new CityAdapter(this, theCity);

            listView.setAdapter( Adapter );
            listView.setTextFilterEnabled(true);
//            cp =sousuo(theCity);
            theCity.add(  new City(nowCity , nowCond , nowTmp , nowDate)  );
            theCity.add(  new City("北京" , "雨" , "12°" , nowDate)  );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City phone =  theCity.get(position);
                String nname =  phone.getName();
                String nnumber = phone.getCond();
                String nkey = phone.getTmp();
                Intent Intent = new Intent(list.this,Detail.class);
                Intent.putExtra("clickname",nname);
                Intent.putExtra("details",nnumber);
                Intent.putExtra("keyv",nkey);
                startActivity(Intent);
            }
        });

    }


    public void search(View view){
        boolean isfind = false;
        EditText ex = (EditText)findViewById(R.id.search);
        String search = ex.getText().toString();
        for (int i = 0;i<cp.size();i++){
        City np = cp.get(i);
            if(np.getName().equals(search)){
                Toast.makeText(list.this,"在第"+(i+1)+"个",Toast.LENGTH_LONG).show();
                listView.smoothScrollToPositionFromTop(i,0);
                isfind = true;
                break;
            }


        }
        if (isfind == false)
        {Toast.makeText(list.this,"No exist!\nTry again",Toast.LENGTH_LONG).show();}
    }

    public ArrayList<City> sousuo(ArrayList<City> p){
        return p;
    }
}

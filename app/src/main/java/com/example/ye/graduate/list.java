package com.example.ye.graduate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class list extends AppCompatActivity {
    ArrayList<City> cp;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Random random = new Random();

        final ArrayList<City> theCity = new ArrayList<City>();

            String nowCity = "上海";
            String nowCond = "晴";
            String nowTmp = "19°";
            String nowDate = "明天";
            theCity.add(  new City(nowCity , nowCond , nowTmp , nowDate)  );
            theCity.add(  new City("北京" , "雨" , "12°" , nowDate)  );


            final CityAdapter Adapter =
                    new CityAdapter(this, theCity);

           listView = (ListView) findViewById(R.id.list);

            listView.setAdapter( Adapter );
            listView.setTextFilterEnabled(true);
        cp =sousuo(theCity);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City phone =  theCity.get(position);
                String nname =  phone.getName();
                String nnumber = phone.getCond();
                String nkey = phone.getTmp();
                Intent Intent = new Intent(list.this,Phone.class);
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
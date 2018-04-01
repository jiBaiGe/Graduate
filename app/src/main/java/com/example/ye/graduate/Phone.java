package com.example.ye.graduate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Phone extends AppCompatActivity {
String m ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i2 = getIntent();
        String s = i2.getStringExtra("clickname");
        String ss = i2.getStringExtra("details");
        String sss = i2.getStringExtra("keyv");

        TextView textView = (TextView) findViewById(R.id.name1);
        textView.setText(s);
        TextView textView1 = (TextView) findViewById(R.id.number1);
        textView1.setText(ss);
        m = sss;
//        TextView textView2 = (TextView) findViewById(R.id.key1);
//        textView2.setText(sss);
    }


    public void call(View view){
        TextView textView = (TextView) findViewById(R.id.key1);
        String key = textView.getText().toString();
        Uri uri = Uri.parse("tel:"+ key);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);

    }
            public void diskey(View view){
                Toast.makeText(Phone.this,get_key(m),Toast.LENGTH_LONG).show();
            }

            public String get_key(String key){
                return key;
            }


    public void mail(View view){
        TextView textView = (TextView) findViewById(R.id.key1);
        String id = textView.getText().toString();
        String name = textView.getText().toString();
        String ad[] = {id};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL,ad);
        intent.putExtra(Intent.EXTRA_SUBJECT, " name's call");
        intent.putExtra(Intent.EXTRA_TEXT,"calling");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    }


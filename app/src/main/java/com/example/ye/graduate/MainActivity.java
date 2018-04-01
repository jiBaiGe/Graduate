package com.example.ye.graduate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

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


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent Intent = new Intent(MainActivity.this, list.class);
                startActivity(Intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1500);



    }
}

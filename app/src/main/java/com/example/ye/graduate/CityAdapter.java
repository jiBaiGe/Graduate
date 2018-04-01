package com.example.ye.graduate;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ye on 2017/7/18.
 */

public class CityAdapter extends ArrayAdapter<City> {

    public CityAdapter(Activity context, ArrayList<City> androidFlavors) {

        super(context, 0, androidFlavors);
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.individual, parent, false);
        }


        City currentWord= getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);

        nameTextView.setText(currentWord.getName());


        TextView numberTextView = (TextView) listItemView.findViewById(R.id.cond);

        numberTextView.setText(currentWord.getCond());


        TextView tmpTextView = (TextView) listItemView.findViewById(R.id.tmp);

        tmpTextView.setText(currentWord.getTmp());


        TextView keyTextView = (TextView) listItemView.findViewById(R.id.date);

        keyTextView.setText(currentWord.getDate());

//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);

//        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());



        return listItemView;
    }

}

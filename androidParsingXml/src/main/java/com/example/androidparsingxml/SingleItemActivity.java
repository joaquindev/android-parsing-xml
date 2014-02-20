package com.example.androidparsingxml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by userbarna on 20/02/14.
 */
public class SingleItemActivity extends Activity{

    //XML node keys
    static final String KEY_NAME = "name";
    static final String KEY_CATEGORY = "category";
    static final String KEY_PUB = "published";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item);

        //Getting intent data
        Intent in = getIntent();

        //Get XML values from previous intent
        String name = in.getStringExtra(KEY_NAME);
        String cat = in.getStringExtra(KEY_CATEGORY);
        String publish = in.getStringExtra(KEY_PUB);

        //Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblCat = (TextView) findViewById(R.id.category_label);
        TextView lblPub = (TextView) findViewById(R.id.published_label);

        lblName.setText(name);
        lblCat.setText(cat);
        lblPub.setText(publish);
    }
}

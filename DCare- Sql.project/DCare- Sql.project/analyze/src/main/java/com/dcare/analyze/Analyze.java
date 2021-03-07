package com.dcare.analyze;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class Analyze extends Activity {


    ListView listView;
   // ArrayList<String> s= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zz);
        //s=getIntent().getExtras().getStringArrayList("Symptoms");

    }


}
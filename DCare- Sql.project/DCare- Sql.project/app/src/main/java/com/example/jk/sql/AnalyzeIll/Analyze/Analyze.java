package com.example.jk.sql.AnalyzeIll.Analyze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.jk.sql.AnalyzeIll.BodyParts.Arms;
import com.example.jk.sql.AnalyzeIll.BodyParts.Botom;
import com.example.jk.sql.AnalyzeIll.BodyParts.Chest;
import com.example.jk.sql.AnalyzeIll.BodyParts.Head;
import com.example.jk.sql.R;


public class Analyze extends Activity {


    ListView listView;
   // ArrayList<String> s= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zz);
        //s=getIntent().getExtras().getStringArrayList("Symptoms");

    }


    public void onClick(View v)

    {

        switch (v.getId())

        {


            case R.id.head:
                Intent i = new Intent(Analyze.this, Head.class);
               // i.putExtra("Symptoms", s);
                startActivity(i);

            break;
            case R.id.chest:
                Intent x = new Intent(Analyze.this, Chest.class);
              //  x.putExtra("Symptoms", s);
                startActivity(x);
            break;

            case R.id.lhand:
                Intent y= new Intent(Analyze.this, Arms.class);
            //    y.putExtra("Symptoms", s);
                startActivity(y);
                break;
            case R.id.rhand:
                Intent z= new Intent(Analyze.this, Arms.class);
            //    z.putExtra("Symptoms", s);
                startActivity(z);
                break;
            case R.id.bottom:
                Intent ab= new Intent(Analyze.this, Botom.class);
            //    ab.putExtra("Symptoms", s);
                startActivity(ab);
                break;

        }

    }
}
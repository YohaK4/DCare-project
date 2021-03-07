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

import java.util.ArrayList;


public class Analyze2 extends Activity {


    ListView listView;
   ArrayList<Integer> s= new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zz);
        s=getIntent().getExtras().getIntegerArrayList("Symptoms");

    }


    public void onClick(View v)

    {

        switch (v.getId())

        {


            case R.id.head:
                Intent i = new Intent(Analyze2.this, Head.class);
                i.putExtra("Symptoms", s);
                startActivity(i);

            break;
            case R.id.chest:
                Intent x = new Intent(Analyze2.this, Chest.class);
                x.putExtra("Symptoms", s);
                startActivity(x);
            break;

            case R.id.lhand:
                Intent y= new Intent(Analyze2.this, Arms.class);
                y.putExtra("Symptoms", s);
                startActivity(y);
                break;
            case R.id.rhand:
                Intent z= new Intent(Analyze2.this, Arms.class);
                z.putExtra("Symptoms", s);
                startActivity(z);
                break;
            case R.id.bottom:
                Intent ab= new Intent(Analyze2.this, Botom.class);
                ab.putExtra("Symptoms", s);
                startActivity(ab);
                break;

        }

    }
}
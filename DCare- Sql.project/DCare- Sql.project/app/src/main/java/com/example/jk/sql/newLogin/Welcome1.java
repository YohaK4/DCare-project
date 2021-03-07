package com.example.jk.sql.newLogin;

/**
 * Created by JK on 1/7/2015.
 */


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.jk.sql.AnalyzeIll.Analyze.Analyze;

import com.example.jk.sql.R;


public class Welcome1 extends ListActivity

{


    protected TextView eun;

    protected LoginDataBaseAdapter DB;

    protected Cursor cursor;

    protected Cursor c;

    protected ListAdapter adapter;

    protected TextView mUname;

    protected TextView mFname;

    protected TextView mLname;

    protected TextView mEmail;


    @Override

    public void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome1);


       DB = new LoginDataBaseAdapter(this);

        // searchText = (EditText) findViewById (R.id.searchText);


        eun = (TextView) findViewById(R.id.textV);

        Bundle bundle = getIntent().getExtras();


       String UName = bundle.getString("UserName");


        eun.setText(UName);


        Button button = (Button) findViewById(R.id.buttonA);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent vcv = new Intent(getBaseContext(), Analyze.class);
                startActivity(vcv);
                // Perform action on click
            }
        });


    }

    public void search(View view)

    {
        switch(view.getId())

        {


            case R.id.buttonSearch:

                cursor = DB.rawQuery("SELECT * FROM LOGIN WHERE username LIKE ?",
                        new String[]{"%" + eun.getText().toString() + "%"});
                //Toast.makeText(getApplicationContext(), cursor.getColumnCount(), Toast.LENGTH_SHORT).show();

                adapter = new SimpleCursorAdapter( this,R.layout.dtl, cursor,

                        new String[]{"_id", "USERNAME"},

                        new int[]{R.id.s1, R.id.s2});


                setListAdapter(adapter);
        }



    }



    @Override

    public void onBackPressed()

    {

        super.onBackPressed();

       Intent i = new Intent(Welcome1.this, Loginnn.class);

       startActivity(i);

    }

}



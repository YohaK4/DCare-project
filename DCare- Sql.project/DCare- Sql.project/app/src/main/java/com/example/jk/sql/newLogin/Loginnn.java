package com.example.jk.sql.newLogin;

/**
 * Created by JK on 2/7/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jk.sql.AnalyzeIll.Analyze.DBHelper;
import com.example.jk.sql.R;

public class Loginnn extends Activity
{
    Button btnSignIn,btnSignUp;
    DBHelper ddb;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // create a instance of SQLite Database
        setContentView(R.layout.login);
        ddb=new DBHelper(this);
        db=ddb.getWritableDatabase();




        // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });



        // get the Refferences of views
        final  EditText editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);



        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                if (ddb.getSinlgeEntry(userName,db)==null)
                {
                    Toast.makeText(Loginnn.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
                else {
                    String storedPassword = ddb.getSinlgeEntry(userName,db);
                    // check if the Stored password matches with  Password entered by user
                    if (password.equals(storedPassword)) {
                        Toast.makeText(Loginnn.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(getBaseContext(), Welcome1.class);
                        in.putExtra("UserName", userName);
                        startActivity(in);
                    } else {
                        Toast.makeText(Loginnn.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        ddb.close();
    }
}
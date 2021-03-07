package com.example.jk.sql.AnalyzeIll.Analyze;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.jk.sql.AnalyzeIll.findSymp.Ill;
import com.example.jk.sql.R;

import java.util.ArrayList;

/**
 * Created by JK on 1/17/2015.
 */


public class One extends Activity {


    protected SQLiteDatabase db;
    protected DBHelper ddb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head);

        ArrayList<Integer> s=getIntent().getExtras().getIntegerArrayList("Symptoms");
        ArrayList<Ill> illList = new ArrayList<Ill>();

        ddb = new DBHelper(this);
        db = ddb.getWritableDatabase();
        ddb.dropIll(db);
        ddb.createIll(db);
        ddb.dropillsymp(db);
        ddb.createillsymp(db);

        //Creating ills
        Ill l = new Ill(0, "Common cold ","'Have a warm tea'", 1);
        illList.add(l);
        l = new Ill(1, "Flue" ,"'Have a chicken soup'", 5);
        illList.add(l);
        l = new Ill(2, "Migraine" , "'Can treat with Aspirin, You should see your GP if you have frequent or severe migraine symptoms that cannot be managed with over the counter painkillers, such as paracetamol.'", 8);
        illList.add(l);
        l = new Ill(3, "Hypertension", "'If you drink, limit alcohol, Understand hot tub safety.'", 3);
        illList.add(l);
        l = new Ill(4, "Sinusitis" , "'Sinusitis is treated with medicines and home treatment, such as applying moist heat to your face.'", 13);
        illList.add(l);
        l = new Ill(5, "Glaucoma" , "'Eye drops for glaucoma, Laser surgery for glaucoma, Microsurgery for glaucoma'", 128);
        illList.add(l);
        l = new Ill(6, "Otitis" , "'Ear drops or sprays, A doctor or nurse will usually prescribe a short course of ear drops or an ear spray.\n" +
                        "These usually contain an antibiotic such as clioquinol or neomycin to clear any infection and a steroid to reduce the inflammation and itch.\n" +
                        "A combination of clioquinol and flumetasone is often used.'", 2);
        illList.add(l);
        l = new Ill(7, "Laryngitis" , "'An adult with ear pain or discharge should see a doctor as soon as possible.'", 128);
        illList.add(l);

       for (int i=0; i<illList.size()-1; i++){
        ddb.insertIll(db, illList.get(i));
       }
        for(int i=0; i<=13;i++)
        {
            if (i==0){
                ddb.insertillsymp(0,i,db);
                ddb.insertillsymp(1,i,db);
                ddb.insertillsymp(2,i,db);
                ddb.insertillsymp(3,i,db);
                ddb.insertillsymp(4,i,db);
            }
            if (i==1){
                ddb.insertillsymp(0,i,db);
                ddb.insertillsymp(1,i,db);
                ddb.insertillsymp(4,i,db);
            }
            if (i==2){
                ddb.insertillsymp(2,i,db);
                ddb.insertillsymp(3,i,db);
                ddb.insertillsymp(5,i,db);
            }
            if (i==3){
                ddb.insertillsymp(2,i,db);
                ddb.insertillsymp(3,i,db);
                ddb.insertillsymp(5,i,db);
            }
            if (i==4){
                ddb.insertillsymp(3,i,db);
            }
            if (i==6){
                ddb.insertillsymp(0,i,db);
                ddb.insertillsymp(1,i,db);
                ddb.insertillsymp(4,i,db);
            }
            if (i==7){
                ddb.insertillsymp(0,i,db);
                ddb.insertillsymp(1,i,db);
            }
            if (i==8){
                ddb.insertillsymp(6,i,db);
                ddb.insertillsymp(7,i,db);
            }
            if (i==9){
                ddb.insertillsymp(6,i,db);
            }
            if (i==10){
                ddb.insertillsymp(5,i,db);
            }
            if (i==11){
                ddb.insertillsymp(3,i,db);
            }
            if (i==13){
                ddb.insertillsymp(6,i,db);
            }
        }


        Intent i = new Intent(getBaseContext(), ASPD.class);
        i.putIntegerArrayListExtra("Symptoms", s);
        startActivity(i);
    }


}

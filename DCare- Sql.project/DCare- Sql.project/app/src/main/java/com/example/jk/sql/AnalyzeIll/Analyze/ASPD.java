package com.example.jk.sql.AnalyzeIll.Analyze;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.jk.sql.R;

import java.util.ArrayList;


public class ASPD extends ListActivity {

    // Database Helper
    protected DBHelper ddb;
    protected SQLiteDatabase db;
    protected ListAdapter adapter;
    protected TextView eun;
    protected String id;
    protected String illname;
    protected String symps;
    protected String treat;
    protected String odds;
    protected Cursor cu;
    protected Cursor cursor;
    Button btnClosePopup;
    protected int ill_id;
    private PopupWindow pwindo;
    protected ArrayList<Integer> s;
    protected ArrayList<Cursor> cursorList;
    protected int size=0;
    int a;
    String symp;
    Integer[] illarr;
    ListView listView;
    private int [] arr ={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        eun = (TextView) findViewById(R.id.textV);


        //*******************************************************************************\\


        ddb = new DBHelper(getBaseContext());
        db = ddb.getWritableDatabase();

        s = getIntent().getExtras().getIntegerArrayList("Symptoms");
        eun.setText(s.toString());
        String sc = " WHERE _id = '" + s.get(0) + "'";

        for (int i = 1; i < s.size(); i++) {
            sc = sc + " OR _id = '" + s.get(i) + "'";
        }
        String lmn="";
        Cursor ccs = db.rawQuery("SELECT * FROM SYM " + sc, null);
        ccs.moveToFirst();
        for (int i = 0; i <= s.size()-1; i++) {
            if (ccs.isAfterLast()) {
                ccs.close();
                break;
            }
            lmn =lmn+ ccs.getString(ccs.getColumnIndex("NAME"))+" ,";
            ccs.moveToNext();
            eun.setText(lmn);
            //if (cu.getInt(cu.getColumnIndex("symp_id")) != i)
            //    break;
        }
        // LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //popup window


        listView= getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                cursor.moveToFirst();
                cursor.moveToPosition(position);
                int idd=cursor.getInt(cursor.getColumnIndex("_id"));

                //ill_id = cu.getInt(cu.getColumnIndex("ill_id"));
                Cursor c = db.rawQuery("SELECT * FROM sympill WHERE ill_id= '"+idd+"'",null);
                ArrayList<Integer> list=new ArrayList<Integer>();
                c.moveToFirst();
                String searchlast="WHERE _id='"+c.getInt(c.getColumnIndex("symp_id"))+"'";
                while (!c.isLast())
                {
                    c.moveToNext();
                    searchlast=searchlast+" OR _id ='"+c.getInt(c.getColumnIndex("symp_id"))+"'";
                }
                Cursor c3=db.rawQuery("SELECT * FROM SYM "+searchlast,null);
                c3.moveToFirst();
                symp="";
                while (!c3.isLast())
                {
                    symp=symp+c3.getString(c3.getColumnIndex("NAME"))+" ,";
                    c3.moveToNext();
                }
                //int sympid=c.getInt(c.getColumnIndex("symp_id"));
                //Cursor cc = db.rawQuery("SELECT * FROM SYM WHERE _id= '"+sympid+"'",null);
                    id = "id: " + idd + "\n";
                    illname = "Disease name: " + cursor.getString(cursor.getColumnIndex("illname")) + "\n";
                    symps = "Symptoms: \n\n" + symp + "\n";
                    odds = "Odds: " + cursor.getString(cursor.getColumnIndex("odds")) + "\n";
                    treat = "How to treat: \n" + cursor.getString(cursor.getColumnIndex("treat")) + "\n";


                LayoutInflater inflater = (LayoutInflater) ASPD.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup_element));
                pwindo = new PopupWindow(layout, 900, 1480, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, -300);

                ((TextView) pwindo.getContentView().findViewById(R.id.txt1)).setText(id);
                ((TextView) pwindo.getContentView().findViewById(R.id.txt2)).setText(illname);
                ((TextView) pwindo.getContentView().findViewById(R.id.txt3)).setText(odds);
                ((TextView) pwindo.getContentView().findViewById(R.id.txt4)).setText(treat);
                ((TextView) pwindo.getContentView().findViewById(R.id.txt5)).setText(symps);

                btnClosePopup = (Button) layout.findViewById(R.id.dismiss);
                btnClosePopup.setOnClickListener(cancel_button_click_listener);

            }

        });

    }


    public void search(View view)

    {

        //WHERE symp_id like '%"+s.get(0)+"%'"

        if (s.size() < 2) {
            Intent i = new Intent(getBaseContext(), Analyze.class);
            startActivity(i);
        }
        else
        {
            String search = " WHERE symp_id = '" + s.get(0) + "'";

            for (int i = 1; i < s.size(); i++) {
                search = search + " OR symp_id = '" + s.get(i) + "'";
            }

            cu = db.rawQuery("SELECT rowid _id,* FROM sympill " + search + " ORDER BY ill_id", null);
            String ar = "";
            String ar2 = "";
            cu.moveToLast();
            int last = cu.getInt(cu.getColumnIndex("ill_id"));
            arr = new int[last + 1];
            //int j=0;
            int j = 0;
            String symptoms="";
            cu.moveToFirst();
            for (int i = 0; i <= last; i++) {
                if (cu.isAfterLast()) {
                    cu.close();
                    break;
                }
                j = cu.getInt(cu.getColumnIndex("ill_id"));
                arr[j] = arr[j] + 1;
                cu.moveToNext();

                //if (cu.getInt(cu.getColumnIndex("symp_id")) != i)
                //    break;
            }
            cu.close();
            int l = 0;
            for (int i = 0; i <= last; i++) {
                ar = ar + String.valueOf(arr[i]) + ", ";
                if (arr[i] > 0)
                    l++;
            }
            illarr = new Integer[l];
            int k = 0;
            for (int i = 0; i <= last; i++) {
                if (arr[i] > 0) {
                    illarr[k] = i;
                    ar2 = ar2 + String.valueOf(i) + ", ";
                    if (k > l) {
                        break;
                    }
                    k++;
                }
            }

            //eun.setText(ar + "\n" + ar2);
            String s2 = " WHERE _id = '" + illarr[0] + "'";
            for (int i = 1; i < illarr.length; i++)
            {
                s2 = s2 + " OR _id = '" + illarr[i] + "' ";
            }
            //eun.setText(s2);
            cursor = db.rawQuery("SELECT * FROM ill " + s2 + " ORDER BY odds", null);
           //ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, illarr);
            adapter = new SimpleCursorAdapter(this, R.layout.dtl, cursor,

                   new String[]{"illname","odds" , "treat","_id"},
                   new int[]{R.id.s1, R.id.s2, R.id.s3, R.id.s4});
            setListAdapter(adapter);


            }
        }






   /* private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) ASPD.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,(ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 500, 780, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

 //Cursor c2=db.rawQuery("SELECT * FROM SYM" +search3,null);
                //    c2.moveToFirst();
                //String symp="";
                //while (c2.moveToNext())
                //{
                //    symp = symp + c2.getString(c2.getColumnIndex("NAME"));
                //    c2.moveToNext();
                //}  String search3 = " WHERE _id = '" + s.get(0) + "'";
                for (int i = 1; i < s.size(); i++)
                {
                    search3 = search3 + " OR _id = '" + s.get(i) + "'";
                }


            btnClosePopup = (Button) layout.findViewById(R.id.dismiss);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pwindo.dismiss();
        }
    };
    public void onBackPressed(){
        Intent i = new Intent(getBaseContext(), Analyze2.class);
        i.putIntegerArrayListExtra("Symptoms", s);
        startActivity(i);
    }
}
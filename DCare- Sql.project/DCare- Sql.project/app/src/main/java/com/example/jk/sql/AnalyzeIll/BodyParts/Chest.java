package com.example.jk.sql.AnalyzeIll.BodyParts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jk.sql.AnalyzeIll.Analyze.Analyze2;
import com.example.jk.sql.AnalyzeIll.Analyze.DBHelper;
import com.example.jk.sql.AnalyzeIll.Analyze.One;
import com.example.jk.sql.AnalyzeIll.findSymp.Symptoms;
import com.example.jk.sql.R;

import java.util.ArrayList;

/**
 * Created by JK on 1/8/2015.
 */




public class Chest extends Activity {



    DBHelper ddb;
    MyCustomAdapter dataAdapter = null;
    ArrayList<Integer> symps=new ArrayList<Integer>();
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head);
        Bundle bundle = getIntent().getExtras();

        //Generate list View from ArrayList
        displayListView();
        checkButtonClick();

        ddb = new DBHelper(this);
        db = ddb.getWritableDatabase();
    }



    private void displayListView() {

            //Array list of countries
            ArrayList<Symptoms> symptomsList = new ArrayList<Symptoms>();
            Symptoms symptoms = new Symptoms(20,"your chest hurts?","Chest Pain",false);
            symptomsList.add(symptoms);
            symptoms = new Symptoms(21,"Feeling like you chest pinched","Chest stab",false);
            symptomsList.add(symptoms);
            symptoms = new Symptoms(22,"Pressiure on your chest?","Chest pressure",false);
            symptomsList.add(symptoms);
            symptoms = new Symptoms(23,"the body hurts?","Slight body ache",false);
            symptomsList.add(symptoms);





        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.des_info, symptomsList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                Symptoms symptoms = (Symptoms) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + symptoms.getID(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Symptoms> {

        private ArrayList<Symptoms> sympList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Symptoms> symptomsList) {
            super(context, textViewResourceId, symptomsList);
            this.sympList = new ArrayList<Symptoms>();
            this.sympList.addAll(symptomsList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.des_info, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Symptoms symptoms = (Symptoms) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_SHORT).show();
                        symptoms.setSelected(cb.isChecked());

                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Symptoms symptoms = sympList.get(position);
            holder.code.setText(" (" +  symptoms.getCode() + ")");
            holder.name.setText(symptoms.getName());
            holder.name.setChecked(symptoms.isSelected());
            holder.name.setTag(symptoms);



            return convertView;

        }

    }


    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ddb.dropSYM(db);
                ddb.createSYM(db);
                // StringBuffer responseText = new StringBuffer();
                //responseText.append("The following were selected...\n");

                ArrayList<Symptoms> symptomsList = dataAdapter.sympList;
                int ab = 0;
                while (ab < symptomsList.size()) {
                    Symptoms symptom = symptomsList.get(ab);

                    ddb.insertSYM(db, symptom);

                    if (symptom.isSelected()) {
                        //responseText.append("\n" + symptoms.getName());
                        if (!symps.contains(symptomsList.get(ab).getID()))
                            symps.add(symptom.getID());

                    }

                    if (!symptom.isSelected())
                    {
                        if (symps.contains(symptomsList.get(ab).getID()))
                            symps.remove(symptom.getID());

                    }
                    ab++;
                }
                if (symps==null)
                {
                    Toast.makeText(getApplicationContext(),"Please pick at least 3 symptoms!!", Toast.LENGTH_LONG).show();
                }
                else
                {

                    // Toast.makeText(getApplicationContext(),symps, Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getBaseContext(), One.class);
                    i.putIntegerArrayListExtra("Symptoms", symps);
                    startActivity(i);

                }
            }
        });

    }

    public void onBackPressed(){
        Intent i = new Intent(getBaseContext(), Analyze2.class);
        i.putIntegerArrayListExtra("Symptoms", symps);
        startActivity(i);
    }
}

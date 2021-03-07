package com.example.jk.sql.AnalyzeIll.Analyze;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jk.sql.AnalyzeIll.findSymp.Ill;
import com.example.jk.sql.AnalyzeIll.findSymp.Symptoms;
import com.example.jk.sql.newLogin.LoginDataBaseAdapter;

/**
 * Created by JK on 4/12/2015.
 */

/**
 * Created by JK on 1/26/2015.
 */

// Database Name

public class DBHelper extends SQLiteOpenHelper {



    private SQLiteDatabase db;
   //DB name
    private static final String DATABASE_NAME = "dayCare";


    // Table Names
    private static final String TABLE_SYMPS = "SYM";
    private static final String TABLE_ILL = "ill";
    private static final String TABLE_SYMPILL = "illSymp";

    // Logcat tag
    private static final String LOG = DBHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Common column names

    private static final String KEY_ID = "_id";

    //ill column name
    private static final String KEY_ILLNAME = "illname";
    private static final String KEY_TREAT = "treat";
    private static final String KEY_ODDS = "odds";
    private DBHelper dbHelper;
    //symps column name
    private static final String KEY_SYMPNAME = "NAME";


    private static final String TABLE_CREATE1="CREATE TABLE SYM (_id INTEGER PRIMARY KEY NOT NULL, NAME TEXT NOT NULL);";
    private static final String TABLE_CREATE2="CREATE TABLE ill (_id INTEGER PRIMARY KEY NOT NULL, illname TEXT NOT NULL, treat TEXT NOT NULL, odds INTEGER NOT NULL);";
    private static final String TABLE_CREATE3="CREATE TABLE sympill (ill_id INTEGER, symp_id INTEGER, " +
            "FOREIGN KEY(ill_id) REFERENCES ill(_id), FOREIGN KEY(symp_id) REFERENCES symps(_id));";
    private static final String TABLE_LOGIN="CREATE TABLE"+"LOGIN (_id integer primary key autoincrement, USERNAME text, PASSWORD text);";
    private static final String TABLE_CREATE=TABLE_CREATE1+TABLE_CREATE2+TABLE_CREATE3+TABLE_LOGIN;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //dbHelper = new DBHelper(context);
        System.out.println("In constructor");
    }
   // public  DBHelper open() throws SQLException
   // {
        //db = dbHelper.getWritableDatabase();
       // return this;
   // }
    @Override
    public void onCreate(SQLiteDatabase _db) {

        // creating required tables
        try {

            _db.execSQL(TABLE_CREATE);
            _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);

        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }




    //DROP TABLES
    public void dropIll(SQLiteDatabase _db){

        _db.execSQL("DROP TABLE IF EXISTS ill;");
    }
    public void dropSYM(SQLiteDatabase _db){

        _db.execSQL("DROP TABLE IF EXISTS SYM;");
    }
    public void dropillsymp(SQLiteDatabase _db){

        _db.execSQL("DROP TABLE IF EXISTS sympill;");
    }
    public void dropAll(SQLiteDatabase _db){

        _db.execSQL("DROP TABLE IF EXISTS SYM; DROP TABLE IF EXISTS ill;");
    }

    //Insert into Tables
    public void insertIll(SQLiteDatabase _db, Ill ill){
        _db.execSQL("INSERT INTO ill (_id, illname, treat, odds) VALUES ("+ill.getId()+", '"+ill.getName()+"', "+ill.getTreat()+","+ill.getOdds()+");");

    }

    public void insertSYM(SQLiteDatabase _db, Symptoms symptom){
        _db.execSQL("INSERT INTO SYM (_id, NAME) VALUES ("+symptom.getID()+", '"+symptom.getName()+"');");

    }
    public void insertillsymp(int symp_id, int ill_id, SQLiteDatabase _db){
        _db.execSQL("INSERT INTO sympill (ill_id, symp_id) VALUES ('"+ill_id+"','"+symp_id+"');");

    }


    //creatTables

    public void createIll(SQLiteDatabase _db){
        _db.execSQL(TABLE_CREATE2);
    }
    public void createSYM(SQLiteDatabase _db){
        _db.execSQL(TABLE_CREATE1);
    }
    public void createillsymp(SQLiteDatabase _db){
        _db.execSQL(TABLE_CREATE3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to " + _newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db);
    }

    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
    }
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(_id);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName, SQLiteDatabase _db)
    {
        Cursor cursor=_db.query("LOGIN", null, " USERNAME= '"+userName+"'", null, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public void  updateEntry(String userName,String password, SQLiteDatabase _db)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        _db.update("LOGIN",updatedValues, where, new String[]{userName});
    }

    public Cursor rawQuery(String string, String[] strings) {
        // ILL Auto-generated method stub
        return null;
    }
}
package com.example.adam.aplikacja;

/**
 * Created by Adam on 25.03.2016.
 */

import org.achartengine.model.XYSeries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DataDbAdapter {
    private static final String DEBUG_TAG = "SqLiteTodoManager";

    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "database.db";
    private static final String DB_DATA_TABLE = "daty";

    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;
    public static final String KEY_DATY = "_data";
    public static final String DATY_OPTIONS = "TEXT NOT NULL";
    public static final int DATY_COLUMN = 1;
    public static final String KEY_PRZYCI = "przyci";
    public static final String PRZYCI_OPTIONS = "INTEGER DEFAULT 0";
    public static final int PRZYCI_COLUMN = 2;
    public static final String KEY_CZAS = "czas";
    public static final String CZAS_OPTIONS = "REAL";
    public static final int COMPLETED_COLUMN = 3;

    private static final String DB_CREATE_DATA_TABLE =
            "CREATE TABLE " + DB_DATA_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_DATY + " " + DATY_OPTIONS + ", " +
                    KEY_PRZYCI + " " + PRZYCI_OPTIONS + ", " +
                    KEY_CZAS + " " + CZAS_OPTIONS +
                    ");";
    private static final String DROP_DATA_TABLE =
            "DROP TABLE IF EXISTS " + DB_DATA_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_DATA_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_DATA_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_DATA_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_DATA_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }

    public DataDbAdapter(Context context) {
        this.context = context;
    }

    public DataDbAdapter open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertData (String daty, long przyci, double czas) {
        ContentValues newDataValues = new ContentValues();
        newDataValues.put(KEY_DATY, daty);
        newDataValues.put(KEY_PRZYCI, przyci);
        newDataValues.put(KEY_CZAS, czas);
        return db.insert(DB_DATA_TABLE, null, newDataValues);
    }

    public boolean updateData(Data data) {
        long id = data.getId();
        String daty = data.getDaty();
        long przyci = data.getPrzyci();
        double czas = data.getCzas();
        return updateData(daty, przyci, czas);
    }

    public boolean updateData( String daty, long przyci, double czas) {
        Data data1 = getData(daty);

        if(data1!=null) {
            String where = KEY_DATY + "=" +  "'"+daty+"'" ;
            ContentValues updateDataValues = new ContentValues();
            updateDataValues.put(KEY_PRZYCI, przyci + data1.getPrzyci());
            updateDataValues.put(KEY_CZAS, czas + data1.getCzas());
            return db.update(DB_DATA_TABLE, updateDataValues, where, null) > 0;
        }
        else
        {
            return false;
        }

        }


    public boolean deleteData(long id){
        String where = KEY_ID + "=" + id;
        return db.delete(DB_DATA_TABLE, where, null) > 0;
    }

    public Cursor getAllDatas() {
        String[] columns = {KEY_ID, KEY_DATY, KEY_PRZYCI, KEY_CZAS};
        return db.query(DB_DATA_TABLE, columns, null, null, null, null, null);
    }

    public Data getData(String daty) {
        String[] columns = {KEY_ID, KEY_DATY, KEY_PRZYCI, KEY_CZAS};
        String where =KEY_DATY + "=" +"'"+  daty + "'";
        Cursor cursor = db.query(DB_DATA_TABLE, columns, where,null , null, null, null);
        Data data = null;
        if(cursor != null && cursor.moveToFirst()) {
            Long id = cursor.getLong(0);
            Long przyci = cursor.getLong(2);
            double czas = cursor.getDouble(3);
            System.out.println("czas" + czas);
            data = new Data(id, daty, przyci, czas);
        }
        cursor.close();
        return data;
    }
    public static void givearrayData(Context context, ArrayList<String> data, XYSeries przyci, XYSeries czas, XYSeries sr)
    {
        double x=0;
        DataDbAdapter  dataDbAdapter;
        dataDbAdapter = new DataDbAdapter(context);
        dataDbAdapter.open();
        Cursor k =dataDbAdapter.getAllDatas();
        while(k.moveToNext()){
            int nr=k.getInt(0);
            String data1=k.getString(1);
            double przyci1=k.getDouble(2);
            double czas1=k.getDouble(3);
            sr.add(x,Math.round( ((przyci1/czas1) * 100.0 ) / 100.0));
            czas1 = Math.round( czas1 * 100.0 ) / 100.0;
            data.add(data1);
            przyci.add(x,przyci1);
            czas.add(x,czas1);
            x++;

        }
        dataDbAdapter.close();
    }
}

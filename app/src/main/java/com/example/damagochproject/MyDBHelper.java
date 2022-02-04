package com.example.damagochproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SaveDB", DB_TABLE = "SaveTable";
    public static final String[] COLUM_NAME = {"NAME", "HUNGER", "Boaring", "Tired", "EXP",
                                                "LV", "Stress", "Under", "poop", "Clean", "Chareacter"};

    public MyDBHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_TABLE + " ( NAME char(20) PRIMARY KEY, HUNGER INTEGER, " +
                    "Boaring INTEGER, Tired INTEGER,EXP INTEGER, LV INTEGER, Stress INTEGER, " +
                    "Under INTEGER, poop INTEGER, Clean INTEGER, Chareacter INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }
}

package com.example.damagochproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public  class SaveClass {

    MyDBHelper myHelper;
    SQLiteDatabase sqlDB;

    public  SaveClass(Context context){
        myHelper = new MyDBHelper(context);
    }

    public String Save(TamaCharacter myTama){

        DBClear();

        Bundle bundle = myTama.getBundle();
        String name = bundle.getString(TamaCharacter.NAME_KEY);
        int[] state = bundle.getIntArray(TamaCharacter.STATE_KEY);
        String sqlStr = "";

        for (int i = 0 ; i < state.length; i++){
            sqlStr += ", " + state[i];
        }
        String sqltext = "INSERT INTO " + MyDBHelper.DB_TABLE + " VALUES('"+ name +"'"+ sqlStr +" ); ";

        sqlDB = myHelper.getWritableDatabase();
        sqlDB.execSQL(sqltext);
        sqlDB.close();
        return "저장완료";
    }

    private void DBClear() {
        sqlDB = myHelper.getWritableDatabase();
        myHelper.onUpgrade(sqlDB, 1, 2);
        sqlDB.close();
    }

    public TamaCharacter Load(){
        TamaCharacter myTama;
        Bundle bundle = new Bundle();
        TamagoFactory factory = new TamagoCharacterFactory();
        Cursor cursor;

        sqlDB = myHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM "+ MyDBHelper.DB_TABLE + " ;", null);

        cursor.moveToNext();

        int ver = cursor.getInt(10);
        myTama = factory.createTama(ver);
        String tamaName = cursor.getString(0);

        bundle.putString(TamaCharacter.NAME_KEY, tamaName);
        int[] state = new int[10];
        for (int i = 0 ; i < state.length; i++){
            int index = i;
            state[index] = cursor.getInt((index+1));
        }
        bundle.putIntArray(TamaCharacter.STATE_KEY, state);
        sqlDB.close();
        myTama.setBundle(bundle);
        return myTama;

    }



}

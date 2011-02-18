package com.fernferret.android.fortywinks;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fortywinks";
    
    private SQLiteDatabase mDb;

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDb = getWritableDatabase();
    }
    
    public void saveAlarm(Alarm alarm) {
        SQLiteStatement s = mDb.compileStatement("INSERT INTO alarms VALUES(?,?,?,?,?,?,?,?,?)");
        s.bindString(1, alarm.getId() + "");
        s.bindString(2, alarm.getHour() + "");
        s.bindString(3, alarm.getMinute() + "");
        s.bindString(4, alarm.getThreshold() + "");
        s.bindString(5, alarm.getDaysOfWeek() + "");
        s.bindString(6, alarm.getFollowups() + "");
        s.bindString(7, alarm.getIntervalStart() + "");
        s.bindString(8, alarm.getIntervalEnd() + "");
        s.bindString(9, (alarm.getEnabled() ? 1 : 0) + "");
        s.executeInsert();
    }
    
    public List<String> listAlarms() {
        List<String> result = new ArrayList<String>();
        Cursor cursor = mDb.query("alarms", new String[] {"id"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        
        return result;
    }
    
    public Alarm getAlarm(int id) {
        Alarm result = new Alarm(id);
        String query = "SELECT * FROM alarms WHERE id = ?";
        Cursor cursor = mDb.rawQuery(query, new String[] { id + "" });
        
        if (cursor.moveToFirst()) {
            result.setHour(cursor.getInt(2));
            result.setMinute(cursor.getInt(3));
            result.setThreshold(cursor.getInt(4));
            result.setDaysOfWeek(cursor.getInt(5));
            result.setFollowups(cursor.getInt(6));
            result.setIntervalStart(cursor.getInt(7));
            result.setIntervalEnd(cursor.getInt(8));
            result.setEnabled(cursor.getInt(9) == 1);
        }
        
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alarms (id INTEGER PRIMARY KEY, hour INTEGER, minute INTEGER, threshold INTEGER, days_of_week INTEGER, followups INTEGER, interval_start INTEGER, interval_end INTEGER, enabled INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarms");
        onCreate(db);
    }

}

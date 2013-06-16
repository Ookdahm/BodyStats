
package com.androidsfuture.bodystats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class BodyDbAdapter extends SQLiteOpenHelper {

	public static final String KEY_ROWID_01 = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_CHEST = "chest";
    public static final String KEY_HIPS = "hips";
    public static final String KEY_THIGH = "thigh";
    public static final String KEY_WAIST = "waist";
    public static final String KEY_ARM = "arm";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_TIME = "time";
    public static final String KEY_ENTRY = "entry";
    public static final String KEY_ROWID_02 = "_id";

    private static final String DATABASE_NAME = "bodystats";
    private static final String DATABASE_TABLE_01 = "measurements";
    private static final String DATABASE_TABLE_02 = "journal";
    private static final int DATABASE_VERSION = 1;
	
    public BodyDbAdapter(Context ctx){
    	super(ctx, DATABASE_NAME, null, DATABASE_VERSION);

    }


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DATABASE_TABLE_01 + "( "
				+ " _id integer primary key autoincrement, "
				+ " date STRING, "
				+ " chest STRING, "
				+ " hips STRING,  "
				+ " thigh STRING, "
				+ " waist STRING, "
				+ " arm STRING, "
				+ " weight STRING );");

		
		db.execSQL("CREATE TABLE " + DATABASE_TABLE_02 + "( "
				+ " _id integer primary key autoincrement, "
				+ " date STRING, "
				+ " time STRING, "
				+ " entry STRING);");
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_01);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_02);
		onCreate(db);
	}
	
}

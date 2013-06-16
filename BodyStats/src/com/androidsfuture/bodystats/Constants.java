package com.androidsfuture.bodystats;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	

	//Columns in the BodyStats database
	public static final String KEY_ROWID = "_id";
	public static final String KEY_DATE = "date";
	public static final String KEY_CHEST = "chest";
	public static final String KEY_HIPS = "hips";
	public static final String KEY_THIGH = "thigh";
	public static final String KEY_WAIST = "waist";
	public static final String KEY_ARM = "arm";
	public static final String KEY_WEIGHT = "weight";
	public static final String KEY_JOURNAL = "journal";
	
	public static final String TABLE_NAME_01 = "measurements";
	public static final String TABLE_NAME_02 = "journal";
}

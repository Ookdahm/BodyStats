package com.androidsfuture.bodystats;

import static com.androidsfuture.bodystats.Constants.TABLE_NAME_01;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class List extends ListActivity {
	
	private static final int ACTIVITY_EDIT=1;
	private static String[] FROM = {BodyDbAdapter.KEY_ROWID_01, BodyDbAdapter.KEY_DATE, BodyDbAdapter.KEY_CHEST, BodyDbAdapter.KEY_HIPS, 
		BodyDbAdapter.KEY_THIGH, BodyDbAdapter.KEY_WAIST, BodyDbAdapter.KEY_ARM, BodyDbAdapter.KEY_WEIGHT};
	private static int[] TO = {R.id._ID, R.id.Date_List, R.id.Chest_List, R.id.Hips_List, R.id.Thigh_List,
									R.id.Waist_List, R.id.Arm_List, R.id.Weight_List};
	//private static String ORDER_BY = BodyDbAdapter.KEY_DATE + " DESC";
	private BodyDbAdapter mDbHelper;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        
        mDbHelper = new BodyDbAdapter(this);
        
        
        try {
        	getStats();
        	
        }finally{
        	mDbHelper.close();
        }
	
}
	
	private Cursor getStats(){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME_01, FROM, null, null, null, null, null);
		startManagingCursor(cursor);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.history_row, cursor, FROM, TO);
		setListAdapter(adapter);
		return cursor;
	}


	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        Intent i = new Intent(this, EditStats.class);
        i.putExtra(BodyDbAdapter.KEY_ROWID_01, id);
        startActivityForResult(i, ACTIVITY_EDIT);
       
    }

	
}

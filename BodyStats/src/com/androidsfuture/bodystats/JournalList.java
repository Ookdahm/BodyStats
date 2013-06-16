package com.androidsfuture.bodystats;

import static com.androidsfuture.bodystats.Constants.TABLE_NAME_02;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class JournalList extends ListActivity {
	
	private static String[] FROM = {BodyDbAdapter.KEY_ROWID_02, BodyDbAdapter.KEY_DATE, BodyDbAdapter.KEY_TIME, BodyDbAdapter.KEY_ENTRY};
	private static int[] TO = {R.id.Journal_ID, R.id.Journal_Date, R.id.Journal_Time, R.id.Journal_Text};
	
	private static final int ACTIVITY_EDIT=1;
	private BodyDbAdapter mDbHelper;
	
	private static final int LIST_NEW_TO_OLD = 0;
	private static final int LIST_OLD_TO_NEW = 1;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_list);
        
        
        
        mDbHelper = new BodyDbAdapter(this);
        
        try {
        	getJournal();      	
        }finally{
        	mDbHelper.close();
        }
	}
	
	private Cursor getJournal() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME_02, FROM, null, null, null, null, null);
		startManagingCursor(cursor);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.journal_row, cursor, FROM, TO);
		setListAdapter(adapter);
		return cursor;
		
	} 
	

	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        Intent i = new Intent(this, JournalUpdate.class);
        i.putExtra(BodyDbAdapter.KEY_ROWID_02, id);
        startActivityForResult(i, ACTIVITY_EDIT);
        this.finish();
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		
	    menu.add(0, LIST_NEW_TO_OLD, 0,  this.getString(R.string.menu_sort_new_to_old));
	    menu.add(0, LIST_OLD_TO_NEW, 0,  this.getString(R.string.menu_sort_old_to_new));
	    return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case LIST_NEW_TO_OLD:
	    	getJournalNew();
	        return true;
	    case LIST_OLD_TO_NEW:
	    	getJournalOld();
	        return true;
	    }
	    return false;
	}
	
	private Cursor getJournalNew() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME_02, FROM, null, null, null, null, BodyDbAdapter.KEY_ROWID_02 + " DESC");
		startManagingCursor(cursor);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.journal_row, cursor, FROM, TO);
		setListAdapter(adapter);
		Toast.makeText(this, this.getString(R.string.toast_sort_new_to_old), Toast.LENGTH_LONG).show();
		return cursor;
		
		
	}
	
	private Cursor getJournalOld() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME_02, FROM, null, null, null, null, BodyDbAdapter.KEY_ROWID_02 + " ASC");
		startManagingCursor(cursor);

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.journal_row, cursor, FROM, TO);
		setListAdapter(adapter);
		Toast.makeText(this, this.getString(R.string.toast_sort_old_to_new), Toast.LENGTH_LONG).show();
		return cursor;
		
	}

}

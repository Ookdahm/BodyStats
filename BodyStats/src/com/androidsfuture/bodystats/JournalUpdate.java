package com.androidsfuture.bodystats;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class JournalUpdate extends Activity {
    public static final String KEY_ENTRY = "entry";
    public static final String KEY_ROWID_02 = "_id";
    private static final String DATABASE_TABLE_02 = "journal";
    private static final String WHERE = "where";
	
	
	private SQLiteDatabase mDb;
	private BodyDbAdapter mDbHelper;
	
	private EditText JournalEntryUpdate;
	private Long mRowId;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_update);
        
        mDbHelper = new BodyDbAdapter(this);
        mDb = mDbHelper.getWritableDatabase();
                     
        JournalEntryUpdate = (EditText)findViewById(R.id.JournalEntry_Update);
        Button updateButton = (Button) findViewById(R.id.Save_Update_Button);    
        
        Button deleteButton = (Button)findViewById(R.id.Delete_Button);
               
        mRowId = savedInstanceState != null ? savedInstanceState.getLong(BodyDbAdapter.KEY_ROWID_02) 
				: null;
        
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();            
			mRowId = extras != null ? extras.getLong(BodyDbAdapter.KEY_ROWID_02) 
									: null;
		}
		
		populateFields();
		
	    updateButton.setOnClickListener(new View.OnClickListener() {

	    	public void onClick(View view) {
	    		setResult(RESULT_OK);
	    	    closeView();
	    	}
	      
	    });
	    
	    deleteButton.setOnClickListener(new View.OnClickListener(){

			public void onClick(View view) {
				dialogJournal();
								
			}
	    	
	    });
               
	}
	
	private void dialogJournal(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(this.getString(R.string.toast_are_you_sure))
		       .setCancelable(false)
		       .setPositiveButton(this.getString(R.string.toast_delete_entry), new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   deleteNote(mRowId);
		               closeView();
		           }
		       })
		       .setNegativeButton(this.getString(R.string.toast_made_a_mistake), new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
		
	}
	
	
	
	private void closeView(){
		
		startActivity(new Intent(this, JournalList.class));
		finish();
	}
	
	
    private void populateFields() {
        if (mRowId != null) {
            Cursor note = fetchNote(mRowId);
            startManagingCursor(note);
            JournalEntryUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_ENTRY)));
        }
    }
    

	
    public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE_02, new String[] {KEY_ROWID_02,
                        KEY_ENTRY}, KEY_ROWID_02 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    public boolean deleteNote(long rowId) {

        return mDb.delete(DATABASE_TABLE_02, KEY_ROWID_02 + "=" + rowId, null) > 0;
    }
     
   /* public boolean updateNote(long rowId, String entry) {
        ContentValues args = new ContentValues();
        args.put(KEY_ENTRY, entry);

        return mDb.update(DATABASE_TABLE_02, args, KEY_ROWID_02 + "=" + rowId, null) > 0;
    } */
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(BodyDbAdapter.KEY_ROWID_02, mRowId);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }
    
    private void saveState() {
    	String body = JournalEntryUpdate.getText().toString();
    	ContentValues args = new ContentValues();
       
    		args.put(KEY_ENTRY, body);
    	mDb.update(DATABASE_TABLE_02, args, KEY_ROWID_02 + "=" + mRowId, null);
    }
    
    /*public void updateNote1(String title, String body) {
    String body = JournalEntryUpdate.getText().toString();   
    ContentValues args = new ContentValues();
       int rowId1=1;
       args.put(KEY_TITLE, title);
       args.put(KEY_ENTRY, body);
     mDb.update(DATABASE_TABLE_02, args, KEY_ROWID_02 + "=" + mRowId, null);*/

    
    
}


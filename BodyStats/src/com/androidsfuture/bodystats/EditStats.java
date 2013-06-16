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
import android.widget.Toast;


public class EditStats extends Activity {
	public static final String KEY_ROWID_01 = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_CHEST = "chest";
    public static final String KEY_HIPS = "hips";
    public static final String KEY_THIGH = "thigh";
    public static final String KEY_WAIST = "waist";
    public static final String KEY_ARM = "arm";
    public static final String KEY_WEIGHT = "weight";
    private static final String DATABASE_TABLE_01 = "measurements";
    
	
	
	private SQLiteDatabase mDb;
	private BodyDbAdapter mDbHelper;
	private Long mRowId;
	
	private EditText ChestUpdate;
	private EditText WaistUpdate;
	private EditText HipsUpdate;
	private EditText ArmUpdate;
	private EditText ThighUpdate;
	private EditText WeightUpdate;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_stats);
        
        mDbHelper = new BodyDbAdapter(this);
        mDb = mDbHelper.getWritableDatabase();
                     
        ChestUpdate = (EditText)findViewById(R.id.DelChest);
        WaistUpdate = (EditText)findViewById(R.id.DelWaist);
        HipsUpdate = (EditText)findViewById(R.id.DelHips);
        ArmUpdate = (EditText)findViewById(R.id.DelArm);
        ThighUpdate = (EditText)findViewById(R.id.DelThigh);
        WeightUpdate = (EditText)findViewById(R.id.DelWeight);
        
        Button delSaveButton = (Button)findViewById(R.id.DelSave);
        Button delDeleteButton = (Button)findViewById(R.id.DelDelete);
        Button delDismissButton = (Button)findViewById(R.id.DelDismiss);
               
        mRowId = savedInstanceState != null ? savedInstanceState.getLong(BodyDbAdapter.KEY_ROWID_01) 
				: null;
        
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();            
			mRowId = extras != null ? extras.getLong(BodyDbAdapter.KEY_ROWID_01) 
									: null;
		}
		
		//populateFields();
		
	    delSaveButton.setOnClickListener(new View.OnClickListener() {

	    	public void onClick(View view) {
	    		setResult(RESULT_OK);
	    		closeView();
	    		showSavedToast();
	    	    
	    	}
	      
	    });
	    
	    delDeleteButton.setOnClickListener(new View.OnClickListener(){

			public void onClick(View view) {
				dialogMeasurements();
								
			}
	    	
	    });
	    
	    delDismissButton.setOnClickListener(new View.OnClickListener(){

			public void onClick(View view) {
				finish();
								
			}
	    	
	    });
               
	}
	
	private void dialogMeasurements(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage (this.getString(R.string.toast_are_you_sure))
		       .setCancelable(false)
		       .setPositiveButton(this.getString(R.string.toast_delete_entry), new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   deleteNote(mRowId);
		        	   showDeletedToast();
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
		startActivity(new Intent(this, BodyStats.class));
		finish();
	}
	
	
    private void populateFields() {
        if (mRowId != null) {
            Cursor note = fetchNote(mRowId);
            startManagingCursor(note);
            ChestUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_CHEST)));
            WaistUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_WAIST)));
            HipsUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_HIPS)));
            ArmUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_ARM)));
            ThighUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_THIGH)));
            WeightUpdate.setText(note.getString(
                    note.getColumnIndexOrThrow(BodyDbAdapter.KEY_WEIGHT)));
        }
    }
    

	
    public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE_01, new String[] {KEY_ROWID_01,
                        KEY_CHEST, KEY_WAIST, KEY_HIPS, KEY_ARM, 
                        KEY_THIGH, KEY_WEIGHT}, KEY_ROWID_01 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    public boolean deleteNote(long rowId) {

        return mDb.delete(DATABASE_TABLE_01, KEY_ROWID_01 + "=" + rowId, null) > 0;
    }
     
   /* public boolean updateNote(long rowId, String entry) {
        ContentValues args = new ContentValues();
        args.put(KEY_ENTRY, entry);

        return mDb.update(DATABASE_TABLE_02, args, KEY_ROWID_02 + "=" + rowId, null) > 0;
    } */
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(BodyDbAdapter.KEY_ROWID_01, mRowId);
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
    	String chest = ChestUpdate.getText().toString();
    	String waist = WaistUpdate.getText().toString();
    	String hips = HipsUpdate.getText().toString();
    	String arm = ArmUpdate.getText().toString();
    	String thigh = ThighUpdate.getText().toString();
    	String weight = WeightUpdate.getText().toString();
    	
    	ContentValues args = new ContentValues();
       
    		args.put(KEY_CHEST, chest);
    		args.put(KEY_WAIST, waist);
    		args.put(KEY_HIPS, hips);
    		args.put(KEY_ARM, arm);
    		args.put(KEY_THIGH, thigh);
    		args.put(KEY_WEIGHT, weight);
    	mDb.update(DATABASE_TABLE_01, args, KEY_ROWID_01 + "=" + mRowId, null);
    	
    }
    
    public void showSavedToast(){
    	
    	Toast.makeText(this, R.string.toast_stats_saved, Toast.LENGTH_LONG).show();
    }
    
    public void showDeletedToast(){
    	
    	Toast.makeText(this, R.string.toast_entry_deleted, Toast.LENGTH_LONG).show();
    	
    }
    
    /*public void updateNote1(String title, String body) {
    String body = JournalEntryUpdate.getText().toString();   
    ContentValues args = new ContentValues();
       int rowId1=1;
       args.put(KEY_TITLE, title);
       args.put(KEY_ENTRY, body);
     mDb.update(DATABASE_TABLE_02, args, KEY_ROWID_02 + "=" + mRowId, null);*/

    
    
}


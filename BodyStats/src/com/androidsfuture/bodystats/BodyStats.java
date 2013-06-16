package com.androidsfuture.bodystats;

import static com.androidsfuture.bodystats.Constants.KEY_ARM;
import static com.androidsfuture.bodystats.Constants.KEY_CHEST;
import static com.androidsfuture.bodystats.Constants.KEY_DATE;
import static com.androidsfuture.bodystats.Constants.KEY_HIPS;
import static com.androidsfuture.bodystats.Constants.KEY_THIGH;
import static com.androidsfuture.bodystats.Constants.KEY_WAIST;
import static com.androidsfuture.bodystats.Constants.KEY_WEIGHT;
import static com.androidsfuture.bodystats.Constants.TABLE_NAME_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.admob.android.ads.AdManager;


public class BodyStats extends Activity implements OnClickListener{
	
	private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String Month;
    private BodyDbAdapter mDbHelper;
   

    
    public static final String DB_SELECTION = "selection";
    public static final int BACKUP_DATABASE = 0;
    public static final int RESTORE_DATABASE = 1;
    
    static final int DATE_DIALOG_ID = 0;
   
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        
       
        mDbHelper = new BodyDbAdapter(this);
        
      
    	//Activate buttons
        View saveButton = this.findViewById(R.id.Save_Button);
        	saveButton.setOnClickListener(this);
        View graphButton = this.findViewById(R.id.Show_Button);
        	graphButton.setOnClickListener(this);
    	View clearButton = this.findViewById(R.id.Clear_Button);
    		clearButton.setOnClickListener(this);
        
        // capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.Date_Text);
        
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
      //Display month in text instead of a number
		 switch (mMonth){
		 case 0:
			 Month = this.getString(R.string.january);
			 break;
		 case 1:
			 Month = this.getString(R.string.february);
			 break;
		 case 2:
			 Month = this.getString(R.string.march);
			 break;
		 case 3:
			 Month = this.getString(R.string.april);
			 break;
		 case 4:
			 Month = this.getString(R.string.may);
			 break;
		 case 5:
			 Month = this.getString(R.string.june);
			 break;
		 case 6:
			 Month = this.getString(R.string.july);
			 break;
		 case 7:
			 Month = this.getString(R.string.august);
			 break;
		 case 8:
			 Month = this.getString(R.string.september);
			 break;
		 case 9:
			 Month = this.getString(R.string.october);
			 break;
		 case 10:
			 Month = this.getString(R.string.november);
			 break;
		 case 11:
			 Month = this.getString(R.string.december);
			 break;
			 
		 }

        // display the current date (this method is below)
        updateDisplay();
    }

    //Display the current date
	private void updateDisplay() {
		 mDateDisplay.setText(
		            new StringBuilder()
		                    // Month is 0 based so add 1
		                    //.append(mMonth + 1).append("-")
		            		.append(Month).append(" ")
		                    .append(mDay).append(", ")
		                    .append(mYear).append(" "));
		
	}
	
	
	//Create menu items
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	
		return true;
	}

	public void onClick(View v) {
		switch(v.getId()){
		case R.id.Save_Button:
			statsSaved();
			break;
			
		case R.id.Show_Button:
			startActivity(new Intent(this, List.class));
			break;
			
		case R.id.Clear_Button:
			clearFields();
			break;
		}
		
	}
	
    public void statsSaved(){
    	//Create variables for EditText fields
    	String Date = mDateDisplay.getText().toString();
        String Chest = ((EditText)findViewById(R.id.Chest_Edit)).getText().toString();
        String Hips = ((EditText)findViewById(R.id.Hips_Edit)).getText().toString();
        String Waist = ((EditText)findViewById(R.id.Waist_Edit)).getText().toString();
        String Arm = ((EditText)findViewById(R.id.Arm_Edit)).getText().toString();
        String Thigh =((EditText)findViewById(R.id.Thigh_Edit)).getText().toString();
        String Weight = ((EditText)findViewById(R.id.Weight_Edit)).getText().toString();
        
           
    	
        //If no fields are filled in, display message. Otherwise, save and show stats
        if (Chest.equals("") && Hips.equals("") && Waist.equals("")&& 
        		Arm.equals("") && Thigh.equals("") && Weight.equals("")) {
        	//Shake the edit boxes and display a message
        	Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            	findViewById(R.id.Chest_Edit).startAnimation(shake);
            	findViewById(R.id.Hips_Edit).startAnimation(shake);
            	findViewById(R.id.Waist_Edit).startAnimation(shake);
            	findViewById(R.id.Arm_Edit).startAnimation(shake);
            	findViewById(R.id.Thigh_Edit).startAnimation(shake);
            	findViewById(R.id.Weight_Edit).startAnimation(shake);
            	
            Toast.makeText(this, "C\'mon. Don't be scared.... We won\'t " +
            					"tell. Promise :-)", Toast.LENGTH_SHORT).show();
               	
        	
        }else{
        	
        	try {
        		addStats(Date, Chest, Hips, Thigh, Waist, Arm, Weight);
        	}finally{
        		mDbHelper.close();
        	}
        	
        	String todaysDate = mDateDisplay.getText().toString();
        	Context context = getApplicationContext();
        	CharSequence text = this.getString(R.string.stats_saved_on) + " " + todaysDate +
    			this.getString(R.string.chest_saved) + "		" + Chest +
    			this.getString(R.string.hips_saved) + "			" + Hips +
    			this.getString(R.string.thigh_saved) + "		" + Thigh +
    			this.getString(R.string.waist_saved) + "		" + Waist +
    			this.getString(R.string.arm_saved) + "			" + Arm +
    			this.getString(R.string.weight_saved) + "		" + Weight;
        	int duration = Toast.LENGTH_LONG;

        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();  	
    	
        		clearFields(); 
        }
    }
    
    private void addStats(String date, String chest, String hips, String thigh,
    		String waist, String arm, String weight) {
    	SQLiteDatabase db = mDbHelper.getWritableDatabase();
    	ContentValues values = new ContentValues();
    		values.put(KEY_DATE, date);
    		values.put(KEY_CHEST, chest);
    		values.put(KEY_HIPS, hips);
    		values.put(KEY_THIGH, thigh);
    		values.put(KEY_WAIST, waist);
    		values.put(KEY_ARM, arm);
    		values.put(KEY_WEIGHT, weight);
    	db.insertOrThrow(TABLE_NAME_01, null, values);
    }
		
    public void clearFields(){
    	//Clear all EditText fields
		EditText Weight = ((EditText)findViewById(R.id.Weight_Edit));
			Weight.getText().clear();
		EditText Hips = ((EditText)findViewById(R.id.Hips_Edit));
			Hips.getText().clear();
		EditText Waist = ((EditText)findViewById(R.id.Waist_Edit));
			Waist.getText().clear();
		EditText Arm = ((EditText)findViewById(R.id.Arm_Edit));
			Arm.getText().clear();
		EditText Thigh = ((EditText)findViewById(R.id.Thigh_Edit));
			Thigh.getText().clear();
		EditText Calf = ((EditText)findViewById(R.id.Chest_Edit));
			Calf.getText().clear();
    	
    }
	
    //Create menu items
    public boolean onCreateOptionsMenu1(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
		return true;	
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case R.id.About_Menu:
    		startActivity(new Intent(this, About.class));
    		return true;
    		
    	case R.id.Journal_Menu:
    		startActivity(new Intent(this, Journal.class));
    		return true;
    			
    	case R.id.Suggestion_Menu:
    		startActivity(new Intent(this, SendTo.class));
    		return true;
    		
    	case R.id.Gym_Menu:
    		startActivity(new Intent(this, GymList.class));
    		return true;
    		
    	case R.id.Motivation_Menu:
    		startActivity(new Intent(this, Motivation.class));
    		return true;	
    		
    	case R.id.Hidden_Menu:
    		startActivity(new Intent(this, Hidden.class));
    		return true;
    		
    	case R.id.Tell_A_Friend_Menu:
    		startActivity(new Intent(this, TellAFriend.class));
    		return true;
    		
    	case R.id.Disclaimer_Menu:
    		startActivity(new Intent(this, Disclaimer.class));
    		return true;
    	
    	}
    	return false;
    }
    
	 private void databaseOptionsMenu(){
		 new AlertDialog.Builder(this)
		 	.setItems(R.array.databaseOptions, 
		 			new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int i) {
							databaseOptions(i);
							
						}
					})
					.show();
	 }
	 
	 private void databaseOptions(int i){
		
		 if (i == BACKUP_DATABASE){
			 backupDB();
		 }
		 
		 if (i == RESTORE_DATABASE){
			 
		 }
		 
		 
		 
	 }
	 
	 private void backupDB(){
		 
		 mDbHelper.close();
	        if (this.isExternalStorageAvail()) {

	            new ExportDatabaseFileTask().execute();

	         } else {
	         	Toast.makeText(this, "SD card is not available, " +
	         			"unable to backup database.",
	            Toast.LENGTH_LONG).show();
	         }
	  }
	
		   private boolean isExternalStorageAvail() {

		      return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		   }

		   private class ExportDatabaseFileTask extends AsyncTask<String, Void, Boolean> {

		      private final ProgressDialog dialog = new ProgressDialog(BodyStats.this);

		      // can use UI thread here

		      protected void onPreExecute() {

		         this.dialog.setMessage("Backing up database...");
		         this.dialog.show();
		      }

		      // automatically done on worker thread (separate from UI thread)

		      protected Boolean doInBackground(final String... args) {

		    	  File dbFile =
		    		  	new File(Environment.getDataDirectory() + "/data/data/" +
		    		  			"/com.androidsfuture.bodystats/databases/bodystats");

		         File exportDir = new File(Environment.getExternalStorageDirectory(), "BodyStats");
		         if (!exportDir.exists()) {

		            exportDir.mkdirs();
		         }

		         File file = new File(exportDir, dbFile.getName());

		         try {
		            file.createNewFile();
		            //this.copyFile(dbFile, file);
		            return true;
		         } catch (IOException e) {
		        	 
		            return false;
		         }

		      }

		      // can use UI thread here
		      protected void onPostExecute(final Boolean success) {

		         if (this.dialog.isShowing()) {
		            this.dialog.dismiss();
		         }
		         if (success) {
		            Toast.makeText(BodyStats.this, "Backup saved to SD card in " +
		            		"folder 'BodyStats'", Toast.LENGTH_LONG).show();
		         } else {
		            Toast.makeText(BodyStats.this, "Backup failed", Toast.LENGTH_LONG).show();
		         }

		      }

		      void copyFile(File src, File dst) throws IOException {

		         FileChannel inChannel = new FileInputStream(src).getChannel();
		         FileChannel outChannel = new FileOutputStream(dst).getChannel();

		         try {
		            inChannel.transferTo(0, inChannel.size(), outChannel);

		         } finally {

		            if (inChannel != null)

		               inChannel.close();

		            if (outChannel != null)

		               outChannel.close();

		         }

		      }
	 }
		 
}
	 
    
   

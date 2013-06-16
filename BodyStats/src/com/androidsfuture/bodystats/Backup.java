package com.androidsfuture.bodystats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class Backup extends Activity {


	@Override

	   public void onCreate(final Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	  
	   
	  
		
        if (this.isExternalStorageAvail()) {

            new ExportDatabaseFileTask().execute();

         } else {
         	Toast.makeText(this, "External storage is not available, " +
         			"unable to export data.",

                     Toast.LENGTH_SHORT).show();
         }

  }



	
	   private boolean isExternalStorageAvail() {

	      return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

	   }

	   private class ExportDatabaseFileTask extends AsyncTask<String, Void, Boolean> {

	      private final ProgressDialog dialog = new ProgressDialog(Backup.this);

	      // can use UI thread here

	      protected void onPreExecute() {

	         this.dialog.setMessage("Exporting database...");
	         this.dialog.show();

	      }

	      // automatically done on worker thread (separate from UI thread)

	      protected Boolean doInBackground(final String... args) {

	    	  File dbFile =
	    		  	new File(Environment.getDataDirectory() + "/data/data" +
	    		  			"com.androidsfuture.bodystats/databases/bodystats.db");

	         File exportDir = new File(Environment.getExternalStorageDirectory(), "exampledata");
	         if (!exportDir.exists()) {

	            exportDir.mkdirs();

	         }

	         File file = new File(exportDir, dbFile.getName());

	         try {

	            file.createNewFile();

	            this.copyFile(dbFile, file);

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

	            Toast.makeText(Backup.this, "Export successful!", Toast.LENGTH_SHORT).show();

	         } else {

	            Toast.makeText(Backup.this, "Export failed", Toast.LENGTH_SHORT).show();

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

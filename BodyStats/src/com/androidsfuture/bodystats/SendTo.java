package com.androidsfuture.bodystats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SendTo extends Activity {
	
	String[] Me = new String[] {"androidboy7@gmail.com"};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Intent sendTo = new Intent(android.content.Intent.ACTION_SEND);
		sendTo.putExtra(android.content.Intent.EXTRA_EMAIL, Me);
		sendTo.putExtra(android.content.Intent.EXTRA_SUBJECT, "Body Stats - Suggestion");
		sendTo.putExtra(android.content.Intent.EXTRA_TEXT, "Here's what I think this app needs:\n\n");
		sendTo.addCategory(Intent.CATEGORY_DEFAULT);
		sendTo.setType("text/plain");
        startActivity(Intent.createChooser(sendTo, this.getString(R.string.send_suggestion_using)));   
        finish();

	}
	


}

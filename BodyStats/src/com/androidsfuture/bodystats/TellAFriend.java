package com.androidsfuture.bodystats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TellAFriend extends Activity {
	
	String[] Me = new String[] {};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Intent sendTo = new Intent(android.content.Intent.ACTION_SEND);
		sendTo.putExtra(android.content.Intent.EXTRA_EMAIL, Me);
		sendTo.putExtra(android.content.Intent.EXTRA_SUBJECT, this.getString(R.string.tell_a_friend_email_subject));
		sendTo.putExtra(android.content.Intent.EXTRA_TEXT, this.getString(R.string.tell_a_friend_email_body));
		sendTo.addCategory(Intent.CATEGORY_DEFAULT);
		sendTo.setType("text/plain");
        startActivity(Intent.createChooser(sendTo, this.getString(R.string.tell_a_friend_using)));   
        finish();

	}
	


}

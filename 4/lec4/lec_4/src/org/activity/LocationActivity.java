package org.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity extends Activity {
	
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       TextView tv = new TextView(this);
	       tv.setText("Hello, Android");
	       setContentView(tv);
	}
}
package org.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BasicActivity extends Activity {
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_game:
			addKurs();
			return true;
		case R.id.help:
			deleteKurs();
			return true;
		case R.id.compass:
			compassView();
			return true;
		case R.id.gps:
			gpsView();
			return true;
		case R.id.main:
			mainView();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void mainView() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

	}

	public void gpsView() {
		Intent intent = new Intent(this, GPSActivity.class);
		startActivity(intent);

	}

	public void compassView() {
		Intent intent = new Intent(this, Compass.class);
		startActivity(intent);

	}

	public void deleteKurs() {

	}

	public void addKurs() {

	}
}

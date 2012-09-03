package org.activity;

import java.io.IOException;
import java.io.InputStream;

import org.listener.SpinnerCurrentOnClickListener;
import org.model.ModelListKurs;
import org.readXML.ReadXMLFile;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity1 extends Activity {
	private EditText editTextIn;
	private EditText editTextOut;
	private EditText editTextAria;
	private Spinner spinnerCurrent;
	private Spinner spinnerValuta;
	private TextView textViewOut;
	private Button buttonKonvert;
	private ModelListKurs model;
	private ArrayAdapter<CharSequence> currentAdapter;
	private ArrayAdapter<CharSequence> valutaAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editTextIn = (EditText) findViewById(R.id.editTextIn);
		editTextOut = (EditText) findViewById(R.id.editTextOut);
		editTextAria = (EditText) findViewById(R.id.editTextAria);
		spinnerCurrent = (Spinner) findViewById(R.id.spinnerCurrent);
		spinnerValuta = (Spinner) findViewById(R.id.spinnerValuta);
		textViewOut = (TextView) findViewById(R.id.textViewOut);
		buttonKonvert = (Button) findViewById(R.id.buttonKonvert);

		model = new ModelListKurs();

	}

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
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void deleteKurs() {
		disableComponents();

	}

	private void addKurs() {
		initModel();
		initComponents();

	}

	private void initModel() {
		model.getList().removeAll(model.getList());
		AssetManager assetManager = this.getAssets();
		InputStream is = null;
		try {
			is = assetManager.open("kurs.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ReadXMLFile fileXML = new ReadXMLFile(is);
		fileXML.readFile(model);

		initAdapter();

	}

	private void initAdapter() {
		currentAdapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);
		valutaAdapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);
		for (String item : model.currentValuta()) {
			currentAdapter.add(item);
		}
		spinnerCurrent.setAdapter(currentAdapter);
		spinnerCurrent
				.setOnItemSelectedListener(new SpinnerCurrentOnClickListener(
						currentAdapter, valutaAdapter, editTextAria, model));

		for (String item : model.valuta(currentAdapter.getItem(0).toString())) {
			valutaAdapter.add(item);
		}
		spinnerValuta.setAdapter(valutaAdapter);

	}

	public void konvertOnClick(View view) {
		double summa = Double.valueOf(editTextIn.getText().toString());
		String current = spinnerCurrent.getSelectedItem().toString();
		String valuta = spinnerValuta.getSelectedItem().toString();
		double kurs = model.valueKues(current, valuta);
		double result = summa * kurs;
		editTextOut.setText(String.valueOf(result),
				TextView.BufferType.EDITABLE);

	}

	private void disableComponents() {
		spinnerCurrent.setVisibility(View.GONE);
		spinnerValuta.setVisibility(View.GONE);
		textViewOut.setVisibility(View.GONE);
		buttonKonvert.setEnabled(false);
		editTextAria.setText("");
		editTextIn.setText("");
		editTextOut.setText("");
	}

	private void initComponents() {
		spinnerCurrent.setVisibility(View.VISIBLE);
		spinnerValuta.setVisibility(View.VISIBLE);
		textViewOut.setVisibility(View.VISIBLE);
		buttonKonvert.setEnabled(true);
	}
}
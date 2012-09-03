package org.listener;

import java.util.ArrayList;

import org.model.ModelListKurs;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class SpinnerCurrentOnClickListener implements OnItemSelectedListener {

	ArrayAdapter<CharSequence> currentAdapter;
	ArrayAdapter<CharSequence> valutaAdapter;
	EditText editTextAria;
	ModelListKurs model;

	public SpinnerCurrentOnClickListener(
			ArrayAdapter<CharSequence> currentAdapter,
			ArrayAdapter<CharSequence> valutaAdapter, EditText editTextAria,
			ModelListKurs model) {
		this.currentAdapter = currentAdapter;
		this.valutaAdapter = valutaAdapter;
		this.editTextAria = editTextAria;
		this.model = model;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		valutaAdapter.clear();
		String text = "";
		int i = 0;
		String current = currentAdapter.getItem(arg2).toString();
		ArrayList<Double> list = model.valueAllKues(current);
		for (String item : model.valuta(current)) {
			valutaAdapter.add(item);
		}
		for (String item : model.valuta(current)) {
			text += item + " : " + list.get(i) + "\n";
			i++;
		}
		editTextAria.setText(text);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}

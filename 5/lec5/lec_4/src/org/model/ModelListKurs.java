package org.model;

import java.util.ArrayList;

public class ModelListKurs {
	private ArrayList<ModelItemKurs> list;

	public ModelListKurs() {
		list = new ArrayList<ModelItemKurs>();
	}

	public ArrayList<ModelItemKurs> getList() {
		return list;
	}

	public void setList(ArrayList<ModelItemKurs> list) {
		this.list = list;
	}

	public ArrayList<String> currentValuta() {
		ArrayList<String> list = new ArrayList<String>();
		for (ModelItemKurs item : getList()) {
			if (list.indexOf(item.getCurrent()) == -1) {
				list.add(item.getCurrent());
			}
		}
		return list;
	}
	
	
	public ArrayList<String> valuta(String current) {
		ArrayList<String> list = new ArrayList<String>();
		for (ModelItemKurs item : getList()) {
			if (current.equals(item.getCurrent())) {
				if (list.indexOf(item.getCurrent()) == -1) {
					list.add(item.getValuta());
				}
			}
		}
		return list;
	}

	public Double valueKues(String current, String valuta) {
		for (ModelItemKurs item : getList()) {
			if (current.equals(item.getCurrent()) && (valuta.equals(item.getValuta()))) {
				return item.getValue();
			}
		}
		return -1.0;
	}
	public ArrayList<Double> valueAllKues(String current) {
		ArrayList<Double> list = new ArrayList<Double>();	
		for (ModelItemKurs item : getList()) {
			if (current.equals(item.getCurrent())) {
				list.add(item.getValue());				
			}
		}
		return list;
	}

	public void removeAll() {
		list.removeAll(list);
		
	}

	
}

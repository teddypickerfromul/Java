package org.dictionary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputData {
	private List<Word> WordsList;

	public InputData(String inputfile, String delimiters)
			throws FileNotFoundException, IOException {
		this.initWordsList();
		this.loadFromFile(inputfile, delimiters);
	}

	public void initWordsList() {
		this.WordsList = new ArrayList<Word>();
	}

	public void loadFromFile(String inputfile, String delimiters)
			throws FileNotFoundException, IOException {

		FileReader fr = new FileReader(inputfile);

		int position = 0;
		StringBuffer buffer = new StringBuffer();
		buffer.setLength(100);

		while ((position = fr.read()) != -1) {
			// TODO:убрать тупое копирование и инициалзацию массива символов
			char symbol = (char) position;
			if (delimiters.indexOf(symbol) != -1 && symbol != '\n'
					&& symbol != '\t') {
				if (buffer.length() != 0) {
//					System.out.println("Detected");
					System.out.println(buffer + " - длина " + buffer.length());
					buffer.delete(0, buffer.length());
				}
			} else {
				buffer.append(symbol);
				// System.out.println(buffer);
				// System.out.println("Ok");
			}

			/*
			 * if (symbol != delimiter) { buffer.append(symbol); } else { Word
			 * word = new Word(buffer.toString());
			 * word.updateWord(buffer.toString()); this.addWord(word);
			 * buffer.delete(0, buffer.length()); }
			 */
		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		InputData in = new InputData("/home/teddy/in.txt", ";./!,[]<>:-__@#$%^&*«» ");
	}
}

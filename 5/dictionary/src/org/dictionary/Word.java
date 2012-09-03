package org.dictionary;

public class Word {
	private String word;
	private int length;
	private int frequency;
	private int hashcode;

	Word() {
//		this.updateWord(null);
		this.setLength(0);
		this.setHashcode(0);
	}

	Word(String word) {
		this.updateWord(word);
		this.setLength(word.length());
		this.setHashcode(word.hashCode());
	}

	private void setLength(int i) {
		this.length = i;
	}

	public String getString() {
		return this.word;
	}

	public String getWord() {
		return word;
	}

	public void trim() {
		this.word.trim();
	}

	public void updateWord(String word) {
		this.word = word;
		this.hashcode = word.hashCode();
		this.length = word.length();
	}

	public int getLength() {
		return length;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getHashcode() {
		return hashcode;
	}

	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}
}

package org.coursework;

public class WordPatternBuilder {

	private String pattern;

	public WordPatternBuilder(String word) {
		this.pattern = "(?!<b><i>)\\b("+word+")\\b(?!<\\/i><\\/b>)";
	}

	public String getWordPattern() {
		return this.pattern;
	}

}

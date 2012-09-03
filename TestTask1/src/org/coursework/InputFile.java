package org.coursework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class InputFile {
	private String filename;
	private long filesize;
	private long linesNumber;
	private long maxLinesNumber;
	private FileReader inputfile;
	private static String delimiters = ";./!,[]<>:-__@#$%^&*\"«»— =+-'()";

	public InputFile(String fname, long maxsize) throws FileFormatException,
			IOException {
		this.filename = fname;
		if (!(this.checkFileSize(maxsize))) {
			throw new FileFormatException(
					"Cлишком большой размер входного файла");
		} else {
			this.filename = fname;
			this.inputfile = new FileReader(fname);
			this.maxLinesNumber = maxsize;
		}
	}

	public String getFileName() {
		return this.filename;
	}

	public FileReader getFileReader() {
		return this.inputfile;
	}

	public void process(DictionaryFile dict, String htmlHead, String htmlTail,
			String outFilename) throws FileFormatException, IOException {
		if (dict.isClear()) {
			throw new FileFormatException(
					"Файл словаря пуст или не был корректно загружен");
		} else {
			PrintWriter outFile = new PrintWriter(outFilename);
			outFile.println(htmlHead);
			BufferedReader reader = new BufferedReader(this.inputfile);
			StringBuffer wordBuffer = new StringBuffer();
			String line = new String();
			while (line != null) {
				line = reader.readLine();
				if (line != null) {
					for (int i = 0; i < line.length(); i++) {
						if (delimiters.indexOf(line.charAt(i)) != -1) {
							if (wordBuffer.length() != 0) {
								Word word = new Word(wordBuffer.toString()
										.trim());
								wordBuffer.delete(0, wordBuffer.length());
								if (dict.exists(word)) {
									WordHighlighter wh = new WordHighlighter(
											word);
									WordPatternBuilder pattern = new WordPatternBuilder(
											word.getWord());
									line = line.replaceAll(
											pattern.getWordPattern(),
											wh.getHighlighted());
								}
							}
						} else {
							wordBuffer.append(line.charAt(i));
						}
					}
					outFile.println(line);
				}
			}
			reader.close();
			outFile.println(htmlTail);
			outFile.close();
		}
	}

	public long getFileSizeBytes() {
		File inputfile = new File(this.filename);
		this.filesize = inputfile.length();
		return this.filesize;
	}

	private boolean checkFileSize(long maxsize) {
		this.getFileSizeBytes();
		if (this.filesize < maxsize) {
			return true;
		} else {
			return false;
		}
	}

	public long getLinesNumber() throws IOException {
		FileReader f = new FileReader(this.filename);
		BufferedReader reader = new BufferedReader(f);
		long lines = 0;
		while (reader.readLine() != null) {
			lines++;
		}
		reader.close();
		f.close();
		this.linesNumber = lines;
		return lines;
	}
}

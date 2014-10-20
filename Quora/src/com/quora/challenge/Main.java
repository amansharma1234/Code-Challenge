package com.quora.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.quora.challenge.runner.QueryParser;

public class Main {
	public static void main(String[] args) {
		QueryParser queryResultQuestion = new QueryParser();
		queryResultQuestion.getInfoFromInput(readInput());
	}

	/* read input from stdin (paste the input on the console and press enter ) */
	private static List<String> readInput() {
		String line = new String("break");
		System.out.println("Please provide input...\n");
		List<String> lines = new ArrayList<String>();
		
		while (!line.equals("")) {
			line = input();
			lines.add(line);
		}
		
		return lines;
	}
	
	/*read input from stdin*/
	private static String input() {
		int ch;
		String result = new String();
		try {
			while ((ch = System.in.read()) != '\n')
				result += (char) ch;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class EvilHangman {

	private ArrayList<String> wordList;
	private HashSet<Character> previousGuesses;
	private TreeSet<Character> incorrectGuesses;
	private Scanner inputScanner;
	// private HashSet<String> potentialWords;
	private Answer answer;
	private int maxLength = 0;
	private int minLength = 10000;

	public EvilHangman() {
		this("engDictionary.txt");
	}

	public EvilHangman(String filename) {
		try {
			wordList = dictionaryToList(filename);
		} catch (IOException e) {
			System.out.printf(
					"Couldn't read from the file %s. Verify that you have it in the right place and try running again.",
					filename);
			e.printStackTrace();
			System.exit(0);
		}

		previousGuesses = new HashSet<>();
		incorrectGuesses = new TreeSet<>();
		int randomLength = new Random().nextInt(maxLength - minLength + 1) + minLength;
		ArrayList<String> potentialWords = new ArrayList<>();

		for (String s : wordList) {
			if (s.length() == randomLength) {
				potentialWords.add(s);
			}
		}

		answer = new Answer(randomLength, potentialWords);
		inputScanner = new Scanner(System.in);
	}

	private ArrayList<String> dictionaryToList(String filename) throws IOException {

		FileInputStream fs = new FileInputStream(filename);
		Scanner scnr = new Scanner(fs);

		ArrayList<String> wordList = new ArrayList<>();

		String word;

		while (scnr.hasNext()) {
			word = scnr.next();
			wordList.add(word);
			if (word.length() < minLength) {
				minLength = word.length();
			}
			if (word.length() > maxLength) {
				maxLength = word.length();
			}

		}

		scnr.close();
		return wordList;
	}

	public void start() {
		// INITIAL randomLength
		// potential word collection

		while (!answer.isSolved()) {
			char guess = promptForGuess();

			// arrayList
			// answer.getWordFamily(guess, potentialWords);
			// ADDGUESS
			recordGuess(guess);
		}
		printVictory();
	}

	private void recordGuess(char guess) {
		previousGuesses.add(guess);
		boolean isCorrect = answer.addGuess(guess);
		if (!isCorrect) {
			incorrectGuesses.add(guess);
		}
	}

	private void printVictory() {
		System.out.printf("Congrats! The word was %s%n", answer.partialSolution);
	}

	private char promptForGuess() {
		while (true) {
			System.out.println("Guess a letter.\n");
			answer.printProgress();
			System.out.println("Incorrect guesses:\n" + incorrectGuesses.toString());
			String input = inputScanner.next();

			if (input.length() != 1) {
				System.out.println("Please enter a single character.");
			} else if (previousGuesses.contains(input.charAt(0))) {
				System.out.println("You've already guessed that.");
			} else {
				return input.charAt(0);
			}

		}

	}

}

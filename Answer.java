import java.util.ArrayList;
import java.util.HashMap;

public class Answer {

	public String partialSolution;
	private int missingChars;
	private HashMap<String, ArrayList<String>> partialSolutionToWordFamily;
	private ArrayList<String> potentialWords;

	public Answer(int randomLength, ArrayList<String> potentialWords) {
		missingChars = randomLength;
		partialSolution = "";
		for (int i = 0; i < randomLength; i++) {
			partialSolution = partialSolution.concat("_");
			// System.out.println(partialSolution);
		}
		this.potentialWords = potentialWords;
		partialSolutionToWordFamily = new HashMap<>();
	}

	private String getWordFamily(char guess) {

		String returnString = "";
		int longestValue = 0;

		for (String s : potentialWords) {
			String keyString = "";

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == guess) {
					keyString = keyString.concat(Character.toString(guess));
				} else {
					if (partialSolution == "" || partialSolution.charAt(i) == '_') {
						keyString = keyString.concat("_");
					} else {
						keyString = keyString.concat(Character.toString(partialSolution.charAt(i)));
					}

				}
			}

			if (!partialSolutionToWordFamily.containsKey(keyString)) {
				ArrayList<String> wordFamily = new ArrayList<>();
				wordFamily.add(s);
				partialSolutionToWordFamily.put(keyString, wordFamily);
				if (longestValue == 0) {
					returnString = keyString;
					longestValue = 1;
				}

			} else {
				partialSolutionToWordFamily.get(keyString).add(s);
				// System.out.println(partialSolutionToWordFamily.get(keyString));
				if (partialSolutionToWordFamily.get(keyString).size() > longestValue) {
					longestValue = partialSolutionToWordFamily.get(keyString).size();
					returnString = keyString;
				}

			}
		}

		potentialWords = partialSolutionToWordFamily.get(returnString);
		partialSolutionToWordFamily = new HashMap<>();
		// System.out.println(potentialWords);

		return returnString;

	}

	public boolean addGuess(char guess) {
		boolean guessCorrect = false;
		String currString = getWordFamily(guess);

		for (int i = 0; i < currString.length(); i++) {
			if (currString.charAt(i) == guess) {
				guessCorrect = true;
				partialSolution = currString;
				missingChars--;
			}
		}

		return guessCorrect;
	}

	public boolean isSolved() {
		return missingChars == 0;
	}

	public void printProgress() {
		// System.out.println(partialSolution);
		// System.out.println("111111");
		for (int i = 0; i < partialSolution.length(); i++) {
			System.out.print(partialSolution.charAt(i) + " ");
		}
		System.out.println();
	}

}

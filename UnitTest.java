import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UnitTest {

	@Test
	void testAnswerAddGuess1() {
		ArrayList<String> potentialWordsForTest = new ArrayList<>();
		potentialWordsForTest.add("echo");
		potentialWordsForTest.add("heal");
		potentialWordsForTest.add("belt");
		potentialWordsForTest.add("peel");
		potentialWordsForTest.add("hazy");
		Answer testAns1 = new Answer(4, potentialWordsForTest);

		char input = 'e';
		boolean actual = testAns1.addGuess(input);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	void testAnswerAddGuess2() {
		ArrayList<String> potentialWordsForTest = new ArrayList<>();
		potentialWordsForTest.add("echo");
		potentialWordsForTest.add("heal");
		potentialWordsForTest.add("belt");
		potentialWordsForTest.add("peel");
		potentialWordsForTest.add("hazy");
		Answer testAns1 = new Answer(4, potentialWordsForTest);

		char input = 'a';
		boolean actual = testAns1.addGuess(input);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	void testAnswerAddGuess3() {
		ArrayList<String> potentialWordsForTest = new ArrayList<>();
		potentialWordsForTest.add("echo");
		potentialWordsForTest.add("heal");
		potentialWordsForTest.add("belt");
		potentialWordsForTest.add("peel");
		potentialWordsForTest.add("hazy");
		Answer testAns1 = new Answer(4, potentialWordsForTest);

		char input = 'm';
		boolean actual = testAns1.addGuess(input);
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	void testAnswerIsSolved1() {
		ArrayList<String> potentialWordsForTest = new ArrayList<>();
		potentialWordsForTest.add("echo");
		potentialWordsForTest.add("heal");
		potentialWordsForTest.add("belt");
		potentialWordsForTest.add("peel");
		potentialWordsForTest.add("hazy");
		Answer testAns1 = new Answer(4, potentialWordsForTest);

		boolean actual = testAns1.isSolved();
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	void testAnswerPrintProgress1() {
		ArrayList<String> potentialWordsForTest = new ArrayList<>();
		potentialWordsForTest.add("echo");
		potentialWordsForTest.add("heal");
		potentialWordsForTest.add("belt");
		potentialWordsForTest.add("peel");
		potentialWordsForTest.add("hazy");
		Answer testAns1 = new Answer(4, potentialWordsForTest);

		testAns1.printProgress();
	}

}

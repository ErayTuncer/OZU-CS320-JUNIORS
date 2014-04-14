package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighestScore {
	private static String FILE_PATH = "assets/highscore";
	
	public static int readHighestscore() {
		try {
			Scanner reader = new Scanner(new File(FILE_PATH));
			int score = reader.nextInt();
			reader.close();
			return score;
		} catch (FileNotFoundException e) {
			System.err.println("Highscore reading error.");
			return -1;
		}		
	}
	
	public static void writeHighestScore(int score) {
		try {
			PrintWriter highScoreWriter = new PrintWriter(new File(FILE_PATH));
			highScoreWriter.print(score);
			highScoreWriter.close();
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find the highscore file!");
		}		
}
}

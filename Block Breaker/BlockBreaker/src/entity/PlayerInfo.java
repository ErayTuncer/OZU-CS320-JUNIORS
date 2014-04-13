package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerInfo {
	
	public int remainingLife;
	public int score;
	
	public static int readHighscoreFromFile() {
		try {
			Scanner reader = new Scanner(new File("assets/highscore"));
			int score = reader.nextInt();
			reader.close();
			return score;
		} catch (FileNotFoundException e) {
			System.err.println("Highscore reading error.");
			return -1;
		}		
	}

}


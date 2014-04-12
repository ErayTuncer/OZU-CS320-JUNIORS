package util;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Brick;

public class BrickDesignImporter {
	
	private static ArrayList<Brick> bricks;
	private static ArrayList<char[]> brickDesign;
	private static String DESIGN_DIRECTORY_PATH = "assests/brickDesigns/";
	
	public static ArrayList<Brick> getBrickDesign(int designNumber){
		bricks = new ArrayList<Brick>();
		brickDesign = new ArrayList<char[]>();
		readFromFile(designNumber);
		generateBricks();
		return bricks;
	}

	private static void readFromFile(int designNumber) {
		File designFile = new File( DESIGN_DIRECTORY_PATH + designNumber + ".txt");
		try {
			Scanner scanner = new Scanner(designFile);
			while (scanner.hasNext()) {
				brickDesign.add(scanner.nextLine().toCharArray());
			}
			scanner.close();
		} catch (FileNotFoundException e){
			System.err.println(designNumber + " brick design not found!");
		}
	}
	
	private static void generateBricks() {
		for (int i = 0; i < brickDesign.size(); i++) {
			for (int j = 0; j < brickDesign.get(i).length; j++) {
				if(brickDesign.get(i)[j] == '_'){
					Brick brick = Brick.createBrick(brickDesign.get(i)[j], new Point(i, j));
					bricks.add(brick);
				}
			}
		}
		
	}
}

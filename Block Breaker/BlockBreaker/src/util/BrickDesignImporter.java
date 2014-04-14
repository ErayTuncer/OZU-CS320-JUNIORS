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
	private static String DESIGN_DIRECTORY_PATH = "assets/brickDesigns/";
	private static int numOfBreakableBricks = 0;
	
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
		numOfBreakableBricks=0;
		for (int i = 0; i < brickDesign.size(); i++) {
			for (int j = 0; j < brickDesign.get(i).length; j++) {
				if(brickDesign.get(i)[j] != '_'){
					int type = Character.getNumericValue(brickDesign.get(i)[j]);
					if(type == 1 || type == 2){
						numOfBreakableBricks++;
					}
					Brick brick = Brick.createBrick(type, calculateBricksPosition(i, j));
					bricks.add(brick);
				}
			}
		}
	}

	private static Point calculateBricksPosition(int i, int j) {
		return new Point(Brick.WIDTH + j * Brick.WIDTH, Brick.WIDTH + i * Brick.HEIGHT);
	}
	
	public static int getNumOfBreakableBricks(){
		return numOfBreakableBricks;
	}	
}

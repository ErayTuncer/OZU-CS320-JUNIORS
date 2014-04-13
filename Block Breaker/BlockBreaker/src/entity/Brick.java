package entity;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brick extends Rectangle {
	
	public static final int UNBREAKABLE = 0;
	public static final int 	   WEAK = 1;
	public static final int 	 STRONG = 2;
	
	public static final int WIDTH  = 50;
	public static final int HEIGHT = 20;
	
	private int type;
	private int health;
	private BufferedImage image;
	
	private static final String IMAGE_DIRECTORY_PATH = "assets/brickImages/";
	
	public static Brick createBrick(int type, Point position) {
		Brick brick = new Brick(type);
		brick.setLocation(position);
		brick.setSize(new Dimension(WIDTH, HEIGHT));
		return brick;
	}

	public Brick (int type) {
		this.type = type;
		initilizeHealth();
		initilizeImage();
	}

	private void initilizeHealth() {
		if (type == UNBREAKABLE) {
			setHealth(-1);
		} else if (type == WEAK) {
			setHealth(1);
		} else if (type == STRONG) {
			setHealth(3);
		} else {
			throw new RuntimeException("Brick type is WRONG.");
		}
	}

	private void initilizeImage() {
		try {
			if (type == UNBREAKABLE) {
				setImage(ImageIO.read(new File(IMAGE_DIRECTORY_PATH + "unbreakable.png")));
			} else if (type == WEAK) {
				setImage(ImageIO.read(new File(IMAGE_DIRECTORY_PATH + "weak.png")));
			} else if (type == STRONG) {
				setImage(ImageIO.read(new File(IMAGE_DIRECTORY_PATH + "strong.png")));
			} else {
				throw new RuntimeException("Brick type is WRONG.");
			}	
		} catch (IOException e) {
			System.err.println("Image NOT found.");
		}
	}

	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}
	

	public int getType() {
		return type;
	}	
	
}


package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Brick extends Rectangle {
	
	private int health;
	private Point position;
	private BufferedImage image;
	
	
	public static Brick createBrick(int health, BufferedImage img, Point position, int width, int height) {
		Brick brick = new Brick();
		brick.setHealth(health);
		brick.setImage(img);
		brick.setLocation(position);
		brick.setSize(width, height);
		return brick;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public Point getPosition() {
		return position;
	}


	public void setPosition(Point position) {
		this.position = position;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}


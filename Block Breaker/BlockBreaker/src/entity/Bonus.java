package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Bonus extends Rectangle {
	
	public static final int PADDLE_BIGGER = 1;
	public static final int PADDLE_SMALLER = 2;
	
	private int bonusType;
	private Point position;
	private BufferedImage image;
	private static int width = 100, height = 10;
	private int speed = -2;
	
	public static Bonus createBonus (Point position, int bonusType, BufferedImage img) {
		Bonus bonus = new Bonus();
		bonus.setLocation(position);
		bonus.setSize(width, height);
		bonus.setType(bonusType);
		bonus.setImage(img);
		return bonus;
	}

	private void setType(int type) {
		this.bonusType = type;
	}

	private void setImage(BufferedImage img) {
		this.image = img;
	}

	public int getType() {
		return bonusType;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	private void moveBonus(){
		this.position.y += speed;
	}
	
}


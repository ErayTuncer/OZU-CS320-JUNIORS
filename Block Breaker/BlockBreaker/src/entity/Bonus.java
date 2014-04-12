package entity;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Bonus extends Rectangle {

	private int bonusType;
	private Point position;
	private BufferedImage image;
	private int width = 100;
	private int height = 10;
	private int speed = -2;
	private Color bonusColor;

	
	private Bonus createBonus(Point position, int bonusType){
		Bonus bonus = new Bonus();
		bonus.setLocation(position);
		bonus.setSize(width, height);
		setColor(bonus, bonusType);
		return bonus;
	}

	private void setColor(Bonus bonus, int bonusType2) {
		if(bonusType == 1){
			
		}
	}

	private void moveBonus(){
		this.position.y += speed;
	}
	
}


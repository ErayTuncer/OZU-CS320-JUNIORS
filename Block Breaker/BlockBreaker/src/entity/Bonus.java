package entity;

import java.awt.Color;
import java.awt.Image;

public class Bonus {
	
	private int bonusType;
	private int xLoc;
	private int yLoc;
	private int width = 10;
	private int height = 10;
	private int speed = -2;
	private Color bonusColor;
	
	public Bonus(int xLoc, int yLoc, int bonusType){
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.bonusType = bonusType;
	}
	
	private void move(){
		this.yLoc += speed;
	}
	
	private void drawBonus(){
		if(bonusType == 1) {
			bonusColor = Color.red;
		}
	}
	
}


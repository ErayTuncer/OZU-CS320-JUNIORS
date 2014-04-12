package entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Paddle extends Rectangle{
	private Point position;
	private BufferedImage image;
	private int width;
	private int height;
		
	private Paddle createPaddle() {
		Paddle paddle = new Paddle();
		paddle.setBounds(position.x, position.y, width, height);
		paddle.setImage(image);
		return paddle;
	}
	
	private void movePaddle(int xSpeed, int ySpeed) {
		this.position.x += xSpeed;
		this.position.y += ySpeed;		

	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setWitdh(int witdh) {
		this.width = witdh;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	

}


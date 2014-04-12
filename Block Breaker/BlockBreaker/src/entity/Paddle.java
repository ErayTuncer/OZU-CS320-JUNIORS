package entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Paddle extends Rectangle {
	
	private Point position;
	private BufferedImage image;
		
	public static Paddle createPaddle(Point position, BufferedImage img, int width, int height) {
		Paddle paddle = new Paddle();
		paddle.setBounds(position.x, position.y, width, height);
		paddle.setImage(img);
		return paddle;
	}
	
	public void movePaddle(int deltaX) {
		this.position.x += deltaX;
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setWitdh(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	

}


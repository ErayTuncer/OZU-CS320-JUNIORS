package entity;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Paddle extends Rectangle {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 20;
	public static final Color COLOR = Color.MAGENTA;

	private Color color;
	
	public static Paddle createPaddle(Point position) {
		Paddle paddle = new Paddle();
		paddle.setBounds(position.x, position.y, WIDTH, HEIGHT);
		paddle.setColor(COLOR);
		return paddle;
	}

	public void setWitdh(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
}


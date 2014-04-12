package entity;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ball extends Rectangle {

	private Point position;
	private BufferedImage image;
	private int ballRadius = 20;

	private Ball createBall(Point position){
		Ball ball = new Ball();
		ball.setBounds(position.x, position.y, ballRadius, ballRadius);
		ball.setImage(image);
		return ball;
	}
	
	private void setImage(BufferedImage image) {
		this.image = image;
	}
	
	private void moveBall(int xSpeed, int ySpeed){
		this.position.x += xSpeed;
		this.position.y += ySpeed;		
	}
	
}


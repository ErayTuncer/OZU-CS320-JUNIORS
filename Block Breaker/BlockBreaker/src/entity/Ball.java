package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ball extends Rectangle {

	private Point position;
	private BufferedImage image;
	private static int ballRadius = 20;

	public static Ball createBall(Point position, BufferedImage img){
		Ball ball = new Ball();
		ball.setBounds(position.x, position.y, ballRadius, ballRadius);
		ball.setImage(img);
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


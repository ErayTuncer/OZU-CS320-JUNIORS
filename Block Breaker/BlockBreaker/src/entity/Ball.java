package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball extends Rectangle {

	private Point position;
	private static final String IMAGE_DIRECTORY_PATH = "assets/BallImage/";
	private BufferedImage image;
	public static int ballRadius = 20;

	public static Ball createBall(Point position){
		Ball ball = new Ball();
		ball.position = position;
		ball.setBounds(position.x, position.y, ballRadius, ballRadius);
		ball.setImage();
		return ball;
	}
	
	private void setImage() {
		try{
			BufferedImage img = ImageIO.read(new File(IMAGE_DIRECTORY_PATH + "ball.png"));
			this.image = img;
		}catch (IOException e) {
			System.err.println("Image NOT found.");
		}
		
	}
	
	private void moveBall(int xSpeed, int ySpeed){
		this.position.x += xSpeed;
		this.position.y += ySpeed;		
	}
	
	public Point getPosition(){
		return this.position;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}


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
	public static int radius = 20;

	public static Ball createBall(Point position){
		Ball ball = new Ball();
		ball.position = position;
		ball.setBounds(position.x, position.y, radius, radius);
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
	
	public void moveBall(int deltaX, int deltaY){
		this.position.x += deltaX;
		this.position.y += deltaY;		
	}
	
	public Point getPosition(){
		return this.position;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}


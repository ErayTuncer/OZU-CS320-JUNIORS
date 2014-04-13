package graphic.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import controller.GameController;
import entity.Ball;
import entity.Brick;
import entity.Paddle;


public class GameView extends View {
	
	private GameController controller;
	
	public GameView(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void paint(Graphics graphics) { 
		super.paint(graphics);
		drawBackground(graphics);
		drawBricks(graphics);
		drawPaddle(graphics);
		drawBall(graphics);
	}

	private void drawBackground(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}

	private void drawBricks(Graphics graphics) {
		ArrayList<Brick> bricks = controller.getLevel().getBricks();
		
		for (Brick brick : bricks) {
			Point position = brick.getLocation();
			Dimension size = brick.getSize(); 
	
			graphics.drawImage(brick.getImage(), position.x, position.y, size.width, size.height, this);		
		}
	}
	
	private void drawPaddle(Graphics graphics) {
		Paddle paddle = controller.getLevel().getPaddle();
		Point position = paddle.getPosition();
		Dimension size = paddle.getSize();
		System.out.println(position.x);
		graphics.setColor(paddle.getColor());
		graphics.drawRect(position.x, position.y, size.width, size.height);
	}
	
	private void drawBall(Graphics graphics) {
		Ball ball = controller.getLevel().getBall();
		Point position = ball.getPosition();
		graphics.drawImage(ball.getImage() , position.x, position.y, ball.ballRadius, ball.ballRadius, this);
		
	}
	
}


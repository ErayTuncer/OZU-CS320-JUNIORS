package graphic.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import controller.GameController;
import entity.Brick;
import entity.Paddle;


public class GameView extends View {
	
	private GameController controller;
	
	public GameView(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		// TODO add bonus 
		super.paintComponent(graphics);
		drawBricks(graphics);
		drawPaddle(graphics);
		drawBall(graphics);
	}

	private void drawBricks(Graphics graphics) {
		ArrayList<Brick> bricks = controller.getLevel().getBricks();
		
		for (Brick brick : bricks) {
			Point position = brick.getPosition();
			Dimension size = brick.getSize(); 
			
			graphics.drawImage(brick.getImage(), position.x, position.y, size.width, size.height, null);		
		}
	}
	
	private void drawPaddle(Graphics graphics) {
		Paddle paddle = controller.getLevel().getPaddle();
		Point position = paddle.getPosition();
		Dimension size = paddle.getSize();
		
		graphics.setColor(paddle.getColor());
		graphics.drawRect(position.x, position.y, size.width, size.height);
	}
	
	private void drawBall(Graphics graphics) {
		//TODO : finish
		
	}
	
}


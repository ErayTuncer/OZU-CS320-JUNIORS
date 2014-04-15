package graphic.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import controller.GameController;
import entity.Ball;
import entity.Bonus;
import entity.Brick;
import entity.Paddle;
import entity.PlayerInfo;

public class GameView extends View implements MouseMotionListener {

	private GameController controller;

	public GameView(GameController controller) {
		this.controller = controller;
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		drawBackground(graphics);
		drawBricks(graphics);
		drawPaddle(graphics);
		drawBall(graphics);
		drawInfo(graphics);
		drawBonus(graphics);
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

			graphics.drawImage(brick.getImage(), position.x, position.y,
					size.width, size.height, this);
		}
	}

	private void drawPaddle(Graphics graphics) {
		Paddle paddle = controller.getLevel().getPaddle();
		Point position = paddle.getLocation();
		Dimension size = paddle.getSize();

		graphics.setColor(paddle.getColor());
		graphics.fillRect(position.x, position.y, size.width, size.height);
	}

	private void drawBall(Graphics graphics) {
		Ball ball = controller.getLevel().getBall();
		Point position = ball.getLocation();
		graphics.drawImage(ball.getImage(), position.x, position.y,
				ball.RADIUS, ball.RADIUS, this);
	}

	private void drawInfo(Graphics graphics) {
		PlayerInfo info = controller.getPlayerInfo();

		graphics.setColor(Color.WHITE);
		graphics.drawString("Life : " + info.remainingLife, 5, 15);
		graphics.drawString("Score : " + info.score, 5, 30);
	}

	private void drawBonus(Graphics graphics) {
		if (controller.isGotBonus()) {
			Bonus bonus = controller.getBonus();
			Point position = bonus.getLocation();
			Dimension size = bonus.getSize();
			graphics.drawImage(bonus.getImage(), position.x, position.y,
					size.width, size.height, this);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// controller.movePaddle(e.getX());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		controller.movePaddle(e.getX());
	}

}

package util;

import java.awt.Point;

import entity.Ball;
import entity.Level;
import entity.Paddle;
import graphic.AppFrame;

public class LevelFactory {

	public static Level createLevel(int levelNumber) {
		Level level = new Level();
		level.setNumber(levelNumber);
		level.setBricks(BrickDesignImporter.getBrickDesign(levelNumber));
		level.setPaddle(Paddle.createPaddle(getInitialPositionOfPaddle()));
		level.setBall(Ball.createBall(getInitialPositionOfBall()));
		level.setBonuses(null); //TODO IMPLEMENT
		level.setNumberOfBricks(BrickDesignImporter.getNumOfBreakableBricks());
		return level;
	}

	public static Point getInitialPositionOfBall() {
		return new Point((AppFrame.WIDTH - Ball.RADIUS) / 2, (AppFrame.HEIGHT - 4 * Paddle.HEIGHT) - Ball.RADIUS);
	}

	public static Point getInitialPositionOfPaddle() {
		return new Point((AppFrame.WIDTH - Paddle.WIDTH) / 2, AppFrame.HEIGHT - 4 * Paddle.HEIGHT);
	}
}

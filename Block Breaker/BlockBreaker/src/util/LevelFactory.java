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
		level.setPaddle(Paddle.createPaddle(calculatePaddlesPosition()));
		level.setBall(Ball.createBall(calculateBallPosition()));
		level.setBonuses(null); //TODO IMPLEMENT
		return level;
	}

	private static Point calculateBallPosition() {
		return new Point((AppFrame.WIDTH - Paddle.WIDTH) / 2, (AppFrame.HEIGHT - 4 * Paddle.HEIGHT) - Ball.ballRadius);
	}

	private static Point calculatePaddlesPosition() {
		return new Point((AppFrame.WIDTH - Paddle.WIDTH) / 2, AppFrame.HEIGHT - 4 * Paddle.HEIGHT);
	}
}

package controller;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import util.LevelFactory;
import entity.Ball;
import entity.Brick;
import entity.Level;
import entity.Paddle;
import entity.PlayerInfo;
import graphic.AppFrame;
import graphic.view.GameView;

public class GameController extends Thread {
	
	private AppController appController;
	private GameView view;
	private Level level;
	private int deltaX, deltaY;
	private PlayerInfo playerInfo;
	private int BONUS_POINT = 50;
	private static String DESIGN_DIRECTORY_PATH = "assets/brickDesigns/";
	
	public GameController(AppController appController) {
		this.appController = appController;
	}

	public void run() {
		initPlayerInfo();
		initBallDirection();	
			
		while(true) {
			if (isBallGoneOut()) {
				playerInfo.remainingLife -= 1;
				resetLevel();
				if (playerInfo.remainingLife <= 0) {
					setHighestScore();
					break;
				}
			} else {
				checkcollusion();
				level.getBall().moveBall(deltaX, deltaY);			
				pause(30);
				view.repaint();
			}
		}
		
		appController.reset();
	}

	private void setHighestScore() {
			try {
				PrintWriter highScoreWriter = new PrintWriter(new File("assets/highscore"));
				highScoreWriter.print(getPlayerInfo().score);
				highScoreWriter.close();
			} catch (FileNotFoundException e) {
			}		
	}

	private void resetLevel() {
		level.setBall(Ball.createBall(LevelFactory.getInitialPositionOfBall()));
		level.setPaddle(Paddle.createPaddle(LevelFactory.getInitialPositionOfPaddle()));
		initBallDirection();
	}

	private void initPlayerInfo() {
		playerInfo = new PlayerInfo();
		playerInfo.remainingLife = 3;
		playerInfo.score = 0;
	}

	private void initBallDirection() {
		Random rgen = new Random();
		int degree = rgen.nextInt(61) + 60;
		while(degree== 90) degree += rgen.nextInt(61) - 60;
		deltaX = (int) ((Math.cos(Math.toRadians(degree)) < 0) ? Math.floor(5.0 * Math.cos(Math.toRadians(degree))) : Math.ceil(5.0 * Math.cos(Math.toRadians(degree))));
		deltaY = (int) Math.ceil((5.0 * Math.sin(Math.toRadians(degree)))) * -1;
	}

	private void checkcollusion() {
		checkCollisionWithBricks();
		checkCollisionWithPaddle();
		checkCollusionWithWalls();
	}

	private void checkCollisionWithBricks() {
		Point ballLocation = level.getBall().getLocation();
		ArrayList<Brick> bricks = level.getBricks();
		for(int i = 0; i< bricks.size(); i++){
			Brick brick = bricks.get(i);
			Point brickLocation = brick.getLocation();
			checkTopCollision(ballLocation, brickLocation, brick); // Is balls top hitting any brick
			checkBottomCollision(ballLocation, brickLocation, brick);
			checkLeftCollision(ballLocation, brickLocation, brick);
			checkRightCollision(ballLocation, brickLocation, brick);
		}
		
	}
	
	private void checkRightCollision(Point ballLocation, Point brickLocation, Brick brick) {
		if((ballLocation.x + Ball.RADIUS == brickLocation.x) &&(ballLocation.y + Ball.RADIUS/2 > brickLocation.y && ballLocation.y + Ball.RADIUS/2 < brickLocation.y + Brick.HEIGHT)){
			level.getBricks().remove(brick);
			getPlayerInfo().score += BONUS_POINT;
			deltaX = -Math.abs(deltaX);
			deltaY = -Math.abs(deltaY);
		}
	}

	private void checkLeftCollision(Point ballLocation, Point brickLocation,Brick brick) {
		if((ballLocation.x == brickLocation.x + Brick.WIDTH) &&(ballLocation.y + Ball.RADIUS/2 > brickLocation.y && ballLocation.y + Ball.RADIUS/2 < brickLocation.y + Brick.HEIGHT)){
			level.getBricks().remove(brick);
			getPlayerInfo().score += BONUS_POINT;
			deltaX = -Math.abs(deltaX);
			deltaY = -Math.abs(deltaY);
		}
	}

	private void checkBottomCollision(Point ballLocation, Point brickLocation, Brick brick) {
		if((ballLocation.y + Ball.RADIUS == brickLocation.y) &&(ballLocation.x + Ball.RADIUS/2 > brickLocation.x && ballLocation.x + Ball.RADIUS/2 < brickLocation.x + Brick.WIDTH)){
			level.getBricks().remove(brick);
			getPlayerInfo().score += BONUS_POINT;
			deltaX = Math.abs(deltaX);
			deltaY = - Math.abs(deltaY);
		}
	}

	private void checkTopCollision(Point ballLocation, Point brickLocation, Brick brick) {
		if((ballLocation.y == brickLocation.y + Brick.HEIGHT) &&(ballLocation.x + Ball.RADIUS/2 > brickLocation.x && ballLocation.x + Ball.RADIUS/2 < brickLocation.x + Brick.WIDTH)){
			level.getBricks().remove(brick);
			getPlayerInfo().score += BONUS_POINT;
			deltaX = - Math.abs(deltaX);
			deltaY = Math.abs(deltaY);
		}
	}

	private void checkCollisionWithPaddle() {
		Point paddleLocation = level.getPaddle().getLocation();
		Point ballLocation = level.getBall().getLocation();
		
		if(isOnSameXDirection(paddleLocation.x, ballLocation.x) && isOnSameYDirection(paddleLocation.y, ballLocation.y)) {
			deltaY = - Math.abs(deltaY);
		}
	}

	private boolean isOnSameYDirection(int paddleY, int ballY) {
		return ballY + Ball.RADIUS >= paddleY && ballY + Ball.RADIUS < paddleY + Paddle.HEIGHT;
	}

	private boolean isOnSameXDirection(int paddleX, int ballX) {
		return (ballX + Ball.RADIUS / 2) > paddleX && (ballX + Ball.RADIUS / 2) <= paddleX + Paddle.WIDTH;
	}

	private void checkCollusionWithWalls() {
		Point ballLocation = level.getBall().getLocation();
		if(ballLocation.x <= 0) deltaX = Math.abs(deltaX);
		if(ballLocation.x >= view.getWidth() - Ball.RADIUS) deltaX = - Math.abs(deltaX);
		if(ballLocation.y <= 0) deltaY = Math.abs(deltaY);
	}

	private boolean isBallGoneOut() {
		Ball ball = this.level.getBall();
		if(ball.getLocation().y > AppFrame.HEIGHT){
			return true;
		}
		return false;
	}

	public void pause(int time) {
		try {
			sleep(time);
		} catch (InterruptedException e) {
			System.err.println("Thread.sleep error!");	
		}
	}
	
	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public Level getLevel() {
		return level;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public void initilizeLevel(int levelNumber) {
		level = LevelFactory.createLevel(levelNumber);
	}
	
	public void movePaddle(int x){
		Paddle paddle = this.level.getPaddle(); 
		if(x - paddle.getSize().width / 2 > 0 && x + paddle.getSize().width / 2 < view.getWidth()) {
			paddle.setLocation(x - paddle.getSize().width / 2, paddle.y);
		}
		view.repaint();
	}

}


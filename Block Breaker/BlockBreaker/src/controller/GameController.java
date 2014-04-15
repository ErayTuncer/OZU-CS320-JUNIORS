package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import util.HighestScore;
import util.LevelFactory;
import entity.Ball;
import entity.Bonus;
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
	private int numberOfBreakableBricks;
	private int bricksDestroyed = 0;
	private Bonus bonus;
	private boolean gotBonus = false;
	
	public GameController(AppController appController) {
		this.appController = appController;
	}

	public void run() {
		initPlayerInfo();
		initBallDirection();	
			
		while(true) {
			if(gotBonus) {
				bonus.setLocation(bonus.getLocation().x, bonus.getLocation().y + 2);
			}
			if (isBallGoneOut()) {
				playerInfo.remainingLife -= 1;
				resetLevel();
				if (playerInfo.remainingLife <= 0) {
					setHighestScore();
					break;
				}
			} else if(numberOfBreakableBricks == 0){
				int newLevelNo = level.getNumber() + 1;
				initilizeLevel(newLevelNo);
				if(level.getNumber() == 10) {
					setHighestScore();
					break;
				}
			}else{
				checkCollision();
				level.getBall().moveBall(deltaX, deltaY);			
				pause(30);
				view.repaint();
			}
		}
		appController.reset();
	}

	private void setHighestScore() {
		if(HighestScore.readHighestscore() < getPlayerInfo().score)
			HighestScore.writeHighestScore(getPlayerInfo().score);
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

	private void checkCollision() {
		checkCollisionWithBricks();
		checkCollisionWithPaddle();
		checkCollisionWithWalls();
	}

	private void checkCollisionWithBricks() {

		for (int i = 0; i < level.getBricks().size(); i++) {
			Brick brick = level.getBricks().get(i);
			checkCollisionWithBrick(brick);
			if(brick.getHealth() == 0)  {
				level.getBricks().remove(brick);
				numberOfBreakableBricks--;
			}			
		}		
	}
	
	// This part is adapted from "http://stackoverflow.com/questions/18418125/java-brickbreaker-paddle-collision-detection"
	private void checkCollisionWithBrick(Brick brick) {
		Ball ball = level.getBall();
		if(ball.getBounds().intersects(brick.getBounds())){
			Rectangle intersection = ball.getBounds().intersection(brick.getBounds());
			if (intersection.width >= intersection.height){
				ball.setLocation(ball.getLocation().x, ball.getLocation().y - (Math.abs(deltaY)/deltaY) * intersection.height);
				deltaY = - deltaY;
			}
			if (intersection.height >= intersection.width){
				ball.setLocation(ball.getLocation().x- (Math.abs(deltaX)/deltaX) * intersection.width, ball.getLocation().y);
				deltaX = - deltaX;
			}
			if(brick.getType() != Brick.UNBREAKABLE) {
				getPlayerInfo().score += Level.BONUS_POINT;
				brick.reduceHealth();
				if(brick.getHealth() == 0) {
					bricksDestroyed++;
					if(bricksDestroyed == 10) {
						System.out.println(brick.getLocation().x + " " + brick.getLocation().y);
						bonus = Bonus.createBonus(brick.getLocation(), new Random().nextInt(3), brick.getImage());
						bricksDestroyed = 0;
						gotBonus = true;
					}
				}
			}
		}
	}

	private void checkCollisionWithPaddle() {
		Point paddleLocation = level.getPaddle().getLocation();
		Point ballLocation = level.getBall().getLocation();
		
		if(isOnSameXDirection(paddleLocation.x, ballLocation.x) && isOnSameYDirection(paddleLocation.y, ballLocation.y)) {
			deltaY = - Math.abs(deltaY);
			ballLocation.x += level.getBall().getBounds().width/2;
//			double gap = ballLocation.x - paddleLocation.x;
//			if (gap < level.getPaddle().width/2){
//				double ratio = gap / level.getPaddle().width/2;
//				deltaX = (int) - (5.0 / (ratio*6));
//			}else if(gap > level.getPaddle().width/2){
//				double ratio = (level.getPaddle().width - gap) / level.getPaddle().width/2;
//				deltaX = (int) + (5.0 / (ratio*6));
//			}else{
//				deltaX = 0;
//				return;
//			}
		}
		
		Paddle paddle = level.getPaddle();
		if(gotBonus && bonus.getBounds().intersects(level.getPaddle().getBounds())) {
			switch (bonus.getType()){
			case 0:
				paddle.setBounds(paddle.x, paddle.y, paddle.width - 20, paddle.height);
				break;
			case 1:
				paddle.setBounds(paddle.x, paddle.y, paddle.width + 20, paddle.height);
				break;
			case 2:
				level.getBallzOfSteel().add(Ball.createBall(new Point(100, 100)));
				break;
			default:
				break;
			}
			gotBonus = false;
			resetBonus();
		}
	}

	private boolean isOnSameYDirection(int paddleY, int ballY) {
		return ballY + Ball.RADIUS >= paddleY && ballY + Ball.RADIUS < paddleY + Paddle.HEIGHT;
	}

	private boolean isOnSameXDirection(int paddleX, int ballX) {
		return (ballX + Ball.RADIUS / 2) > paddleX && (ballX + Ball.RADIUS / 2) <= paddleX + Paddle.WIDTH;
	}

	private void checkCollisionWithWalls() {
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
	
	private void resetBonus() {
		bonus.setLocation(-100, -100);
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
		numberOfBreakableBricks = level.getNumberOfBricks();
	}
	
	public void movePaddle(int x){
		Paddle paddle = this.level.getPaddle(); 
		if(x - paddle.getSize().width / 2 > 0 && x + paddle.getSize().width / 2 < view.getWidth()) {
			paddle.setLocation(x - paddle.getSize().width / 2, paddle.y);
		}
		view.repaint();
	}

	public Bonus getBonus() {
		return bonus;
	}

	public boolean isGotBonus() {
		return gotBonus;
	}

}


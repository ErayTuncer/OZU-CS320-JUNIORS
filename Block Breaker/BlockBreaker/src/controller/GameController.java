package controller;

import util.LevelFactory;
import entity.Ball;
import entity.Level;
import entity.Paddle;
import graphic.AppFrame;
import graphic.view.GameView;

public class GameController {
	
	private GameView view;
	private Level level;
	private int deltaX, deltaY;

	public void run() {
		deltaX = 5; 
		deltaY = 5;
		
		view.repaint();
		
		while(!isBallGoneOut()) {
			checkcollusion();

			level.getBall().moveBall(deltaX, deltaY);
			
			//checkBallGoneOut(); //TODO : implement			
			//pause(); //TODO : implement
			view.repaint();
		}
		
		// TODO : 
		//if(playerInfo.getRemaningLives > 0) {
		// loselife();
		// run(); ... 
		// } else .....
	}

	private void checkcollusion() {
		checkCollisionWithBricks();
		checkCollisionWithPaddle();
		checkCollusionWithWalls();
	}

	private void checkCollisionWithBricks() {
		// TODO Auto-generated method stub
		
	}
	
	private void checkCollisionWithPaddle() {
		// TODO Auto-generated method stub
		
	}

	private void checkCollusionWithWalls() {
		Ball ball = level.getBall();
		if(ball.getLocation().x <= 0 || ball.getLocation().x >= AppFrame.WIDTH - Ball.RADIUS) {
			deltaX = - deltaX;
		}
		if(ball.getLocation().y <= 0) {
			deltaY = - deltaY;
		}
		
	}

	private boolean isBallGoneOut() {
		Ball ball = this.level.getBall();
		if(ball.getPosition().y > AppFrame.HEIGHT){
			return true;
		}
		return false;
	}

	public void pause() {
		//TODO : implement

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


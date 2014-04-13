package controller;

import util.LevelFactory;
import entity.Ball;
import entity.Level;
import graphic.AppFrame;
import graphic.view.GameView;

public class GameController {
	
	private GameView view;
	private Level level;
	
	

	public void run() {
		//TODO : implement
		view.repaint();
		int i = 0;
		while(i < 100) {
			Ball ball = this.level.getBall();
			ball.moveBall();					
			//checkCollisionWithBricks(); //TODO : implement
			//checkCollisionWithPaddle(); //TODO : implement
			//checkBallGoneOut(); //TODO : implement
			view.repaint();
			//pause(); //TODO : implement
			i++;
			
		}
		
	}
	
	private void checkBallGoneOut() {
		Ball ball = this.level.getBall();
		if(ball.getPosition().y > AppFrame.HEIGHT){
			//TODO : implement
//			loseLife(); 
//			if(isGameOver()){
//				terminateGame();
//				saveHighScore(score);
//			}
		}
	}

	private void checkCollisionWithPaddle() {
		// TODO Auto-generated method stub
		
	}

	private void checkCollisionWithBricks() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		

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

}


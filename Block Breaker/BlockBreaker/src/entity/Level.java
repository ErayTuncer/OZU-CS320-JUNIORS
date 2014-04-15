package entity;

import java.util.ArrayList;

public class Level {
	public static int BONUS_POINT = 50;
	
	private int number;
	private ArrayList<Brick> bricks;
	private Paddle paddle;
	private Ball ball;
	private ArrayList<Bonus> bonuses;
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private int numberOfBricks;

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	public void setBricks(ArrayList<Brick> bricks) {
		this.bricks = bricks;
	}
	public Paddle getPaddle() {
		return paddle;
	}
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	public ArrayList<Bonus> getBonuses() {
		return bonuses;
	}
	public void setBonuses(ArrayList<Bonus> bonuses) {
		this.bonuses = bonuses;
	}
	public Ball getBall() {
		return ball;
	}
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	public int getNumberOfBricks() {
		return this.numberOfBricks;
	}
	public void setNumberOfBricks(int numOfBreakableBricks) {
		this.numberOfBricks = numOfBreakableBricks;	
	}
	public ArrayList<Ball> getBallzOfSteel() {
		return balls;
	}
}

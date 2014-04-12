package entity;

import java.util.List;

public class Level {
	private int number;
	private List<Brick> bricks;
	private Paddle paddle;
	private List<Bonus> bonuses;
	
	
	
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<Brick> getBricks() {
		return bricks;
	}
	public void setBricks(List<Brick> bricks) {
		this.bricks = bricks;
	}
	public Paddle getPaddle() {
		return paddle;
	}
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	public List<Bonus> getBonuses() {
		return bonuses;
	}
	public void setBonuses(List<Bonus> bonuses) {
		this.bonuses = bonuses;
	}
	
}

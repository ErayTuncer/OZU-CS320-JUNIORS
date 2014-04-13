package controller;

import entity.Level;
import graphic.view.GameView;

public class GameController extends Thread {
	
	private GameView view;
	private Level level;
	
	

	public void run() {
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
		// TODO Auto-generated method stub
		
	}

}


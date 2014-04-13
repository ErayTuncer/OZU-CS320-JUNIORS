package graphic.view;

import java.awt.Graphics;
import java.util.ArrayList;

import controller.GameController;
import entity.Brick;


public class GameView extends View {
	
	private GameController controller;
	
	public GameView(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		// TODO implement
		super.paintComponent(graphics);
		drawBricks(graphics);		
	}

	private void drawBricks(Graphics graphics) {
		ArrayList<Brick> bricks = controller.getLevel().getBricks();
		
	}

	
	
}


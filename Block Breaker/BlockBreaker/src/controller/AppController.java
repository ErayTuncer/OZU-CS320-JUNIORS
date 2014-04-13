package controller;
import graphic.view.GameView;
import graphic.view.MenuView;
import graphic.AppFrame;

public class AppController {
	private AppFrame appFrame;
	private GameController gameController;
	
	public static void main(String[] args) {
		AppController appController = new AppController();
		appController.displayMenu();
	}

	public AppController() {
		appFrame = new AppFrame();
	}
		
	private void displayMenu() {
		appFrame.setView(new MenuView(this));	
	}

	public void startGame() {
		initilizeGameController();
		appFrame.setView(gameController.getView());
		gameController.start();
	}

	private void initilizeGameController() {
		gameController = new GameController(this);
		gameController.initilizeLevel(1);
		gameController.setView(new GameView(gameController));
	}
	
	public void reset() {
		displayMenu();
	}

	public void terminate() {
		appFrame.dispose();
		System.exit(0);
	}

}



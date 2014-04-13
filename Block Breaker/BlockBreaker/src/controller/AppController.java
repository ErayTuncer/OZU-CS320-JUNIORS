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
		gameController = new GameController();
		gameController.initilizeLevel(1);
		
		appFrame.setView(new GameView(gameController));		
		
		gameController.run();
		
		appFrame.removeContents();
		System.out.println("start"); // TODO: REMOVE

	}
	
	public void terminate() {
		appFrame.dispose();
		System.exit(0);
	}
	

}



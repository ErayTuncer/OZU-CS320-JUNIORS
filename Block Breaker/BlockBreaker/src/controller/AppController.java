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
		setGameControllerForGame();
		setAppFrameForGame();		
		gameController.run();
		System.out.println("start"); // TODO: REMOVE

	}

	private void setAppFrameForGame() {
		appFrame.removeContents();
		appFrame.setView(new GameView(gameController));
	}
	

	private void setGameControllerForGame() {
		gameController = new GameController();
		gameController.initilizeLevel(1);
	}

	public void terminate() {
		appFrame.dispose();
		System.exit(0);
	}

}



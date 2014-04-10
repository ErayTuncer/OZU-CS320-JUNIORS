package MainPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GamePlay.GameController;

public class AppController implements ActionListener{
	private AppFrame appFrame;
	private MenuView menuView;
	private GameController gameController;
	
	public AppController() {
		appFrame = new AppFrame();
		menuView = new MenuView(this);
		appFrame.setMenuView(menuView);
	}
	
	public void startGame(){
		appFrame.removeContents();
		System.out.println("start");
		gameController = new GameController();
		gameController.run();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("start".equals(e.getActionCommand())){
			startGame();
		}
		else if ("exit".equals(e.getActionCommand())){
			System.exit(0);
		}
		
	}

}



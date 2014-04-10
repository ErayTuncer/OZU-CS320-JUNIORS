package MainPage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuView extends JPanel {	
	private int width = 120;
	private int height = 250;
	private static final long serialVersionUID = 1L;

	public MenuView(AppController appController){
		GridLayout gridLayout = new GridLayout(3,0);
	    gridLayout.setVgap(40);
		setLayout(gridLayout);
		createButtonsWithListener(appController);
		setOpaque(false);
	}

	private void createButtonsWithListener(AppController appController) {
		//ButtonActionListener listener = new ButtonActionListener();
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(appController);
		startGameButton.setActionCommand("start");

		add(startGameButton);
		
		JLabel highestScoreLabel = new JLabel("Highest score");
		highestScoreLabel.setForeground(Color.WHITE);
		add(highestScoreLabel);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(appController);
		exitButton.setActionCommand("exit");
		add(exitButton);
		
	}
	

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    
}


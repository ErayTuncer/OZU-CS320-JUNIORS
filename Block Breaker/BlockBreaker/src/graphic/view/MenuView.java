package graphic.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import controller.AppController;


public class MenuView extends View implements ActionListener {	
	
	private AppController controller;

	public MenuView(AppController appController){
		this.controller = appController;
		setLayout(new GridLayout(3,0));
		setBorder(BorderFactory.createMatteBorder(250, 250, 250, 250, Color.GREEN));
		createButtonsWithListener();
		setOpaque(false);
	}

	private void createButtonsWithListener() {
		JButton startGameButton = new JButton("Start Game");
		startGameButton.setPreferredSize(new Dimension(120, 60)); 
		startGameButton.addActionListener(this);
		startGameButton.setActionCommand("start");

		add(startGameButton);
		
		JLabel highestScoreLabel = new JLabel("Highest score");
		highestScoreLabel.setForeground(Color.WHITE);
		highestScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		add(highestScoreLabel);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");
		add(exitButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("start".equals(e.getActionCommand())){
			controller.startGame();
		}
		else if ("exit".equals(e.getActionCommand())){
			controller.terminate();
		}
		
	}
    
}


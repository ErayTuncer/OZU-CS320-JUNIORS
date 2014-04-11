package graphic;
import graphic.view.View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class AppFrame extends JFrame {
	
	public AppFrame(){
		setTitle("CS320-Juniors-BlockBreaker");
		Dimension size = new Dimension(800, 600);
		setSize(size);
		setPreferredSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(getMinimumSize());
		setMinimumSize(getMinimumSize());
		getContentPane().setBackground(Color.BLACK);
	}

	public void setView(View view) {
		view.setPreferredSize(getPreferredSize());
		add(view);
		setVisible(true);	
	}

	public void removeContents() {
		revalidate();
		repaint();
	}
	
}



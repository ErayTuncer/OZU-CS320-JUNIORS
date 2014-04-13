package graphic;
import graphic.view.View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class AppFrame extends JFrame {
	public static final int  WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public AppFrame(){
		setTitle("CS320-Juniors-BlockBreaker");
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setSize(size);
		setPreferredSize(size);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(getMinimumSize());
		setMinimumSize(getMinimumSize());
		getContentPane().setBackground(Color.BLACK);
	}

	public void setView(View view) {
		removeContents();
		view.setPreferredSize(getPreferredSize());
		getContentPane().add(view);
		getContentPane().revalidate();	
		setVisible(true);
	}

	private void removeContents() {
		getContentPane().removeAll();
		getContentPane().invalidate();
	}
	
}



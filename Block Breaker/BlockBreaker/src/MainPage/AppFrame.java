package MainPage;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class AppFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Box box;
	
	public AppFrame(){
		setTitle("CS320-Juniors-BlockBreaker");
		Dimension size = new Dimension(800, 600);
		setSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(getMinimumSize());
		setMinimumSize(getMinimumSize());
		setPreferredSize(getPreferredSize());
		getContentPane().setBackground(Color.BLACK);

	}

	public void setMenuView(MenuView menuView) {
		box = new Box(BoxLayout.Y_AXIS);
		box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(Box.createVerticalGlue());
		box.add(menuView);
		box.add(Box.createVerticalGlue());
		box.setBackground(Color.GREEN);
		add(box);
		//pack();
		setVisible(true);	
	}

	public void removeContents() {
		remove(box);
		revalidate();
		repaint();
	}
}



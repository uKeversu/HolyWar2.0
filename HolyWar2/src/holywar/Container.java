package holywar;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import holywar.model.Level;

public class Container extends JFrame {

	public Container() {

		add(new Level());

		setTitle("Holy War - Beta");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Container();
	}
	
}

package views;

import listeners.CloseApplicationListener;

import javax.swing.*;
import java.awt.*;

/**
 * @author dzimiks
 * Date: 18-04-2019 at 23:03
 */
public class MainView extends JFrame {

	private static MainView instance;

	private MainView() {
		init();
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
		}

		return instance;
	}

	private void init() {
		setLookAndFeel();
		setLayout(new BorderLayout());
		setSize(new Dimension(1600, 800));
		setMinimumSize(new Dimension(800, 700));
		setTitle("File Storage");
		this.addWindowListener(new CloseApplicationListener());
		setLocationRelativeTo(null);

		JLabel label = new JLabel("Text");

		this.add(label, BorderLayout.CENTER);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

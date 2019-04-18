package listeners;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author dzimiks
 * Date: 18-04-2019 at 23:06
 */
public class CloseApplicationListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		JFrame frame = (JFrame) e.getComponent();
		int answer = JOptionPane.showConfirmDialog(
				frame,
				"Do you want to exit application?",
				"Exit",
				JOptionPane.YES_NO_OPTION
		);

		if (answer == JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		} else {
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}

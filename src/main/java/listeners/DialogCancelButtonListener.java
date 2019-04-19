package listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cancel button listener.
 * @author dzimiks
 * Date: 19-04-2019 at 02:40
 */
public class DialogCancelButtonListener implements ActionListener {

	private JDialog dialog;

	public DialogCancelButtonListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CreateFileDialogOkButtonListener Cancel");
		this.dialog.setVisible(false);
	}
}

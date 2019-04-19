package listeners;

import dialogs.CreateFileDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dzimiks
 * Date: 19-04-2019 at 02:38
 */
public class CreateFileDialogOkButtonListener implements ActionListener {

	private CreateFileDialog dialog;

	public CreateFileDialogOkButtonListener(CreateFileDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CreateFileDialogOkButtonListener OK");
		this.dialog.setVisible(false);
	}
}

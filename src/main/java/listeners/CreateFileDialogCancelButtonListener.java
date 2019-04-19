package listeners;

import dialogs.CreateFileDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dzimiks
 * Date: 19-04-2019 at 02:40
 */
public class CreateFileDialogCancelButtonListener implements ActionListener {

	private CreateFileDialog dialog;

	public CreateFileDialogCancelButtonListener(CreateFileDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CreateFileDialogOkButtonListener Cancel");
		this.dialog.setVisible(false);
	}
}

package listeners;

import dialogs.DeleteDirDialog;
import model.Student;
import models.LocalDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDirDialogOkBtnListener implements ActionListener {
	private DeleteDirDialog dialog;
	private String implementation;
	private Student student;

	public DeleteDirDialogOkBtnListener(DeleteDirDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path = dialog.getTfFilePath();

		if (implementation.equals("local")) {
			try {
				LocalDirectory ld = new LocalDirectory();
				// TODO: Handle exceptions if empty string etc.
//                if (name.equals("")) {
//                    name = "new-local-directory";
//                }

				ld.delete(path);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Delete directory ===");

			System.out.println("Path: " + path);
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

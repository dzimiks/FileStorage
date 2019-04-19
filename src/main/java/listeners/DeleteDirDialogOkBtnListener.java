package listeners;

import dialogs.DeleteDirDialog;
import dropbox.models.DropboxDirectory;
import exceptions.DeleteException;
import model.Student;
import models.LocalDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Delete directory button listener.
 */
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
		String accessToken = student.getAccessToken();
		String implementation = student.getImplementation();

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
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);

			try {
				dropboxDirectory.delete(path);
			} catch (DeleteException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

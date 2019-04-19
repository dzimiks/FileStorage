package listeners;

import dialogs.DeleteDirDialog;
import dialogs.DeleteFileDialog;
import dropbox.models.DropboxDirectory;
import model.Student;
import models.LocalDirectory;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFileDialogOkBtnListener implements ActionListener {
	private DeleteFileDialog dialog;
	private String implementation;
	private Student student;

	public DeleteFileDialogOkBtnListener(DeleteFileDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path = dialog.getTfFilePath();
		implementation = student.getImplementation();
		String accessToken = student.getAccessToken();

		if (implementation.equals("local")) {
			try {
				LocalFile lf = new LocalFile();
				// TODO: Handle exceptions if empty string etc.
//                if (name.equals("")) {
//                    name = "new-local-directory";
//                }

				lf.delete(path);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Delete file ===");

			System.out.println("Path: " + path);
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);
			dropboxDirectory.delete(path);
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

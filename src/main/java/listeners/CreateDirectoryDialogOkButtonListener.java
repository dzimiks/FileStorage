package listeners;

import dialogs.CreateDirectoryDialog;
import dialogs.CreateFileDialog;
import dropbox.models.DropboxDirectory;
import exceptions.CreateDirectoryException;
import exceptions.CreateFileException;
import model.Student;
import models.LocalDirectory;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDirectoryDialogOkButtonListener implements ActionListener {

	private CreateDirectoryDialog dialog;
	private Student student;

	public CreateDirectoryDialogOkButtonListener(CreateDirectoryDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String implementation = student.getImplementation();
		String accessToken = student.getAccessToken();
		String name = dialog.getTfFileName().trim().replace(" ", "-");
		String path = dialog.getTfFilePath();

		// TODO: Handle exceptions if empty string etc.
		if (name.equals("")) {
			name = "new-local-directory.txt";
		}

		System.out.println("=== Create directory ===");
		System.out.println("Name: " + name);
		System.out.println("Path: " + path);

		if (implementation.equals("local")) {
			try {
				LocalDirectory ld = new LocalDirectory();
				ld.create(name, path);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);

			try {
				dropboxDirectory.create(name, path);
			} catch (CreateDirectoryException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

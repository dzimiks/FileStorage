package listeners;

import dialogs.UploadDirsDialog;
import dropbox.models.DropboxDirectory;
import exceptions.UploadException;
import exceptions.UploadMultipleException;
import exceptions.UploadMultipleZipException;
import model.Student;
import models.LocalDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class UploadDirsOkBtnListener implements ActionListener {
	private UploadDirsDialog dialog;
	private Student student;

	public UploadDirsOkBtnListener(UploadDirsDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<File> src = dialog.getFiles();
		String dest = dialog.getTfDirDestination();
		String implementation = student.getImplementation();
		String accessToken = student.getAccessToken();

		if (implementation.equals("local")) {
			try {
				LocalDirectory ld = new LocalDirectory();
				// TODO: Handle exceptions if empty string etc.
//                if (name.equals("")) {
//                    name = "new-local-directory";
//                }
//
//                ld.create(name, path);
				ld.uploadMultipleZip(src, dest, student.getName() + "-" + student.getSurname());
			} catch (UploadMultipleZipException ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Create directory ===");
			System.out.println("Src: " + src);
			System.out.println("Dest: " + dest);
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);

			try {
				dropboxDirectory.uploadMultipleZip(src, dest, student.getName() + "-" + student.getSurname());
			} catch (UploadMultipleZipException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

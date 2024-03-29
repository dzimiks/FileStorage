package listeners;

import dialogs.UploadFilesDialog;
import dropbox.models.DropboxDirectory;
import dropbox.models.DropboxFile;
import exceptions.UploadException;
import exceptions.UploadMultipleZipException;
import model.Student;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Upload file button listener.
 */
public class UploadFilesOkBtnListener implements ActionListener {
	private UploadFilesDialog dialog;
	private Student student;

	public UploadFilesOkBtnListener(UploadFilesDialog dialog, Student student) {
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
				LocalFile lf = new LocalFile();
				// TODO: Handle exceptions if empty string etc.
//                if (name.equals("")) {
//                    name = "new-local-directory";
//                }
//
//                ld.create(name, path);
				lf.uploadMultipleZip(src, dest, student.getName() + "-" + student.getSurname());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Create directory ===");
			System.out.println("Src: " + src);
			System.out.println("Dest: " + dest);
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);
			DropboxFile dropboxFile = new DropboxFile(dropboxDirectory.getClient());

			try {
				dropboxFile.uploadMultipleZip(src, dest, student.getName() + "-" + student.getSurname());
			} catch (UploadMultipleZipException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

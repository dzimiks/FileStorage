package listeners;

import dialogs.UploadDirDialog;
import dialogs.UploadFileDialog;
import dropbox.models.DropboxDirectory;
import dropbox.models.DropboxFile;
import model.Student;
import models.LocalDirectory;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadFileOkBtnListener implements ActionListener {
	private UploadFileDialog dialog;
	private Student student;

	public UploadFileOkBtnListener(UploadFileDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = dialog.getTfDirSource();
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
				lf.upload(src, dest);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Create directory ===");
			System.out.println("Src: " + src);
			System.out.println("Dest: " + dest);
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);
			DropboxFile dropboxFile = new DropboxFile(dropboxDirectory.getClient());
			dropboxFile.upload(src, dest);
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

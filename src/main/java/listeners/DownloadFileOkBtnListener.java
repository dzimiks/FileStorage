package listeners;

import dialogs.DownloadFileDialog;
import dropbox.models.DropboxDirectory;
import dropbox.models.DropboxFile;
import exceptions.DownloadException;
import model.Student;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownloadFileOkBtnListener implements ActionListener {
	private DownloadFileDialog dialog;
	private Student student;

	public DownloadFileOkBtnListener(DownloadFileDialog dialog, Student student) {
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
				lf.move(src, dest);
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
				dropboxFile.download(src, dest);
			} catch (DownloadException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

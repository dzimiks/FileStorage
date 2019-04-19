package listeners;

import dialogs.CreateDirectoryDialog;
import dialogs.DownloadDirDialog;
import dropbox.models.DropboxDirectory;
import exceptions.DownloadException;
import model.Student;
import models.LocalDirectory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Directory download button listener.
 */
public class DownloadDirOkBtnListener implements ActionListener {
	private DownloadDirDialog dialog;
	private Student student;

	public DownloadDirOkBtnListener(DownloadDirDialog dialog, Student student) {
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
				LocalDirectory ld = new LocalDirectory();
				// TODO: Handle exceptions if empty string etc.
//                if (name.equals("")) {
//                    name = "new-local-directory";
//                }
//
//                ld.create(name, path);
				ld.move(src, dest);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Create directory ===");
			System.out.println("Src: " + src);
			System.out.println("Dest: " + dest);
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);

			try {
				dropboxDirectory.download(src, dest);
			} catch (DownloadException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

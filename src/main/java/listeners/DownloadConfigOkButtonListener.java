package listeners;

import dialogs.DownloadConfigDialog;
import model.Student;
import models.LocalDirectory;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author dzimiks
 * Date: 19-04-2019 at 05:27
 */
public class DownloadConfigOkButtonListener implements ActionListener {

	private DownloadConfigDialog dialog;
	private Student student;
	private final String LOCAL_CONFIG_PATH = "./config.json";

	public DownloadConfigOkButtonListener(DownloadConfigDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String implementation = student.getImplementation();
		String accessToken = student.getAccessToken();
		String name = dialog.getTfFirstName().trim().replace(" ", "-");
		String lastName = dialog.getTfLastName();
		String index = dialog.getTfIndex();
		String path = dialog.getPath();

		System.out.println("=== Download config ===");
		System.out.println("Name: " + name);
		System.out.println("Last name: " + lastName);
		System.out.println("Index: " + index);

		if (implementation.equals("local")) {
			LocalFile localFile = new LocalFile();
			localFile.upload(LOCAL_CONFIG_PATH, path + File.separator + "config.json");
		} else if (implementation.equals("dropbox")) {

		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}
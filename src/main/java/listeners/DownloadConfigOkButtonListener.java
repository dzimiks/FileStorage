package listeners;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dialogs.DownloadConfigDialog;
import dropbox.models.DropboxDirectory;
import dropbox.models.DropboxFile;
import exceptions.DownloadException;
import exceptions.UploadException;
import model.Student;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Download config button listener.
 * @author dzimiks
 * Date: 19-04-2019 at 05:27
 */
public class DownloadConfigOkButtonListener implements ActionListener {

	private DownloadConfigDialog dialog;
	private Student student;
	private final String LOCAL_CONFIG_PATH = "./config.json";
	private final String DROPBOX_CONFIG_PATH = "/UUP2018-januar/config.json";

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
		String group = dialog.getComboBoxItem();

		System.out.println("=== Download config ===");
		System.out.println("Name: " + name);
		System.out.println("Last name: " + lastName);
		System.out.println("Index: " + index);
		System.out.println("Path: " + path);
		System.out.println("Group: " + group);

		if (implementation.equals("local")) {
			LocalFile localFile = new LocalFile();

			try {
				localFile.upload(LOCAL_CONFIG_PATH, path + File.separator + "config.json");
			} catch (UploadException e1) {
				e1.printStackTrace();
			}

			saveMeta(name, lastName, index, group, implementation, accessToken);
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);
			DropboxFile dropboxFile = new DropboxFile(dropboxDirectory.getClient());

			try {
				dropboxFile.download(DROPBOX_CONFIG_PATH, path + File.separator + "config.json");
			} catch (DownloadException e1) {
				e1.printStackTrace();
			}

			saveMeta(name, lastName, index, group, implementation, accessToken);
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}

	private void saveMeta(String name, String lastName, String index, String group, String implementation, String accessToken) {
		try {
			FileOutputStream writer = new FileOutputStream("meta.json");
			OutputStreamWriter out = new OutputStreamWriter(writer);
			Student s = new Student(name, lastName, index, group, implementation, accessToken);
			Gson gson = new Gson();
			out.append(gson.toJson(s));
			out.close();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
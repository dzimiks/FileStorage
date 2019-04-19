package listeners;

import dialogs.CreateFileDialog;
import exceptions.CreateFileException;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dzimiks
 * Date: 19-04-2019 at 02:38
 */
public class CreateFileDialogOkButtonListener implements ActionListener {

	private CreateFileDialog dialog;
	private String implementation;

	public CreateFileDialogOkButtonListener(CreateFileDialog dialog, String implementation) {
		this.dialog = dialog;
		this.implementation = implementation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = dialog.getTfFileName().trim().replace(" ", "-");
		String path = dialog.getTfFilePath();
		LocalFile localFile = new LocalFile();

		if (implementation.equals("local")) {
			try {
				// TODO: Handle exceptions if empty string etc.
				if (name.equals("")) {
					name = "new-local-file.txt";
				}

				localFile.create(name, path);
			} catch (CreateFileException ex) {
				ex.printStackTrace();
			}

			System.out.println("=== Create file ===");
			System.out.println("Name: " + name);
			System.out.println("Path: " + path);
		} else {
			System.out.println("Error");
		}

		this.dialog.setVisible(false);
	}
}

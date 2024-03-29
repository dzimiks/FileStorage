package listeners;

import dialogs.CreateFileDialog;
import exceptions.CreateFileException;
import model.Student;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Create file button listener.
 * @author dzimiks
 * Date: 19-04-2019 at 02:38
 */
public class CreateFileDialogOkButtonListener implements ActionListener {

	private CreateFileDialog dialog;
	private Student student;

	public CreateFileDialogOkButtonListener(CreateFileDialog dialog, Student student) {
		this.dialog = dialog;
		this.student = student;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String implementation = student.getImplementation();
		String name = dialog.getTfFileName().trim().replace(" ", "-");
		String path = dialog.getTfFilePath();

		if (implementation.equals("local")) {
			LocalFile localFile = new LocalFile();
			// TODO: Handle exceptions if empty string etc.
			if (name.equals("")) {
				name = "new-local-file.txt";
			}

			try {
				localFile.create(name, path);
			} catch (CreateFileException e1) {
				e1.printStackTrace();
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

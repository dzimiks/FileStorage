package views;

import listeners.CloseApplicationListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author dzimiks
 * Date: 18-04-2019 at 23:03
 */
public class MainView extends JFrame {

	private static MainView instance;

	private JLabel firstName;
	private JTextField tfFirstName;
	private JLabel lastName;
	private JTextField tfLastName;
	private JTextField path;
	private JButton chooseFile;
	private JFileChooser fileChooser;
	private File readFromFile;

	private MainView() {
		init();
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
		}

		return instance;
	}

	private void init() {
		setLookAndFeel();
		setLayout(null);
		setSize(new Dimension(1200, 600));
		setMinimumSize(new Dimension(800, 700));
		setTitle("File Storage");
		this.addWindowListener(new CloseApplicationListener());
		setLocationRelativeTo(null);

		firstName = new JLabel("Student first name:");
		tfFirstName = new JTextField();

		lastName = new JLabel("Student last name:");
		tfLastName = new JTextField();

		path = new JTextField();
		chooseFile = new JButton("Upload");
		path.setEditable(false);

		firstName.setBounds(10, 10, 150, 25);
		tfFirstName.setBounds(180, 10, 190, 25);
		lastName.setBounds(10, 50, 150, 25);
		tfLastName.setBounds(180, 50, 190, 25);
		path.setBounds(10, 90, 250, 25);
		chooseFile.setBounds(270, 90, 100, 25);

		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		btnOk.setBounds(120, 130, 50, 25);
		btnCancel.setBounds(180, 130, 80, 25);

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("json, txt", "json", "txt");
		fileChooser.setFileFilter(filter);

		chooseFile.addActionListener(e -> {
			if (fileChooser.showDialog(new JDialog(), "Choose") == JFileChooser.APPROVE_OPTION) {
				path.setText(fileChooser.getSelectedFile().getAbsolutePath());
				readFromFile = fileChooser.getSelectedFile();
			}
		});

		add(firstName);
		add(tfFirstName);
		add(lastName);
		add(tfLastName);
		add(path);
		add(chooseFile);
		add(btnOk);
		add(btnCancel);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

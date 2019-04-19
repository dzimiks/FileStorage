package views;

import com.google.gson.Gson;
import dialogs.CreateFileDialog;
import listeners.CloseApplicationListener;
import model.Student;
import models.LocalDirectory;
import models.LocalFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

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

	private BufferedReader reader;
	private Gson gson;

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
		this.gson = new Gson();

		try {
			this.reader = new BufferedReader(new FileReader("./config.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Student student = gson.fromJson(reader, Student.class);
		String implementation = student.getImplementation();
		System.out.println("Student name: " + student.getName() + " " + student.getSurname());
		System.out.println("Implementation: " + implementation);

//		setLookAndFeel();
		setLayout(null);
//		setSize(new Dimension(1800, 800));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(800, 700));
		setTitle("File Storage");
		this.addWindowListener(new CloseApplicationListener());
		setLocationRelativeTo(null);

		firstName = new JLabel("Student first name:");
		tfFirstName = new JTextField();

		lastName = new JLabel("Student last name:");
		tfLastName = new JTextField();

		path = new JTextField();
		chooseFile = new JButton("Download");
		path.setEditable(false);

		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("json, txt", "json", "txt");
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);

		chooseFile.addActionListener(e -> {
			if (fileChooser.showDialog(new JDialog(), "Choose") == JFileChooser.APPROVE_OPTION) {
				path.setText(fileChooser.getSelectedFile().getAbsolutePath());
				readFromFile = fileChooser.getSelectedFile();
			}
		});

		btnOk.addActionListener(e -> {

		});

		LocalDirectory localDirectory = new LocalDirectory();
		LocalFile localFile = new LocalFile();

		ArrayList<File> dirs = localDirectory.listDirectories(".", true);
		JComboBox comboBox = new JComboBox();

		for (File dir : dirs) {
			try {
				comboBox.addItem(dir.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		JButton btnCreateFile = new JButton("Create file");
		JButton btnDeleteFile = new JButton("Delete file");
		JButton btnDownloadFile = new JButton("Download file");
		JButton btnUploadFile = new JButton("Upload file");

		JButton btnCreateDir = new JButton("Create dir");
		JButton btnDeleteDir = new JButton("Delete dir");
		JButton btnDownloadDir = new JButton("Download dir");
		JButton btnUploadDir = new JButton("Upload dir");

		// TODO: Bounds
		firstName.setBounds(10, 10, 150, 25);
		tfFirstName.setBounds(180, 10, 190, 25);
		lastName.setBounds(10, 50, 150, 25);
		tfLastName.setBounds(180, 50, 190, 25);
		path.setBounds(10, 90, 250, 25);
		chooseFile.setBounds(270, 90, 100, 25);
		btnOk.setBounds(120, 130, 70, 25);
		btnCancel.setBounds(180, 130, 80, 25);
		comboBox.setBounds(10, 180, 600, 25);
		btnCreateFile.setBounds(800, 10, 150, 25);
		btnDeleteFile.setBounds(800, 50, 150, 25);
		btnDownloadFile.setBounds(800, 90, 150, 25);
		btnUploadFile.setBounds(800, 130, 150, 25);
		btnCreateDir.setBounds(1000, 10, 150, 25);
		btnDeleteDir.setBounds(1000, 50, 150, 25);
		btnDownloadDir.setBounds(1000, 90, 150, 25);
		btnUploadDir.setBounds(1000, 130, 150, 25);

		btnCreateFile.addActionListener(e -> {
			CreateFileDialog dialog = new CreateFileDialog(implementation);
			dialog.setVisible(true);
		});

		add(firstName);
		add(tfFirstName);
		add(lastName);
		add(tfLastName);
		add(path);
		add(chooseFile);
		add(btnOk);
		add(btnCancel);
		add(comboBox);
		add(btnCreateFile);
		add(btnDeleteFile);
		add(btnDownloadFile);
		add(btnUploadFile);
		add(btnCreateDir);
		add(btnDeleteDir);
		add(btnDownloadDir);
		add(btnUploadDir);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package views;

import com.google.gson.Gson;
import dialogs.*;
import dropbox.models.DropboxDirectory;
import exceptions.ListFilesException;
import listeners.CloseApplicationListener;
import model.Student;
import models.LocalDirectory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * @author dzimiks
 * Date: 18-04-2019 at 23:03
 */
public class MainView extends JFrame {

	private static MainView instance;
	private BufferedReader reader;
	private Gson gson;

	private MainView() {
		try {
			init();
		} catch (ListFilesException e) {
			e.printStackTrace();
		}
	}

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
		}

		return instance;
	}

	private void init() throws ListFilesException {
		this.gson = new Gson();

		try {
			this.reader = new BufferedReader(new FileReader("./config.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Student student = gson.fromJson(reader, Student.class);
		String implementation = student.getImplementation();
		String accessToken = student.getAccessToken();
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

		JComboBox comboBox = generateComboBox(implementation, accessToken);
		JComboBox comboBoxFiles = generateComboBoxFiles(implementation, accessToken);

		JButton btnDownloadConfig = new JButton("Config");

		JButton btnCreateFile = new JButton("Create file");
		JButton btnDeleteFile = new JButton("Delete file");
		JButton btnDownloadFile = new JButton("Download file");
		JButton btnUploadFile = new JButton("Upload file");
		JButton btnUploadFiles = new JButton("Upload files");

		JButton btnCreateDir = new JButton("Create dir");
		JButton btnDeleteDir = new JButton("Delete dir");
		JButton btnDownloadDir = new JButton("Download dir");
		JButton btnUploadDir = new JButton("Upload dir");
		JButton btnUploadDirs = new JButton("Upload dirs");

		// TODO: Bounds
		btnDownloadConfig.setBounds(10, 0, 150, 25);
		comboBox.setBounds(10, 180, 600, 25);
		comboBoxFiles.setBounds(10, 220, 600, 25);
		btnCreateFile.setBounds(800, 10, 150, 25);
		btnDeleteFile.setBounds(800, 50, 150, 25);
		btnDownloadFile.setBounds(800, 90, 150, 25);
		btnUploadFile.setBounds(800, 130, 150, 25);
		btnUploadFiles.setBounds(800, 170, 150, 25);

		btnCreateDir.setBounds(1000, 10, 150, 25);
		btnDeleteDir.setBounds(1000, 50, 150, 25);
		btnDownloadDir.setBounds(1000, 90, 150, 25);
		btnUploadDir.setBounds(1000, 130, 150, 25);
		btnUploadDirs.setBounds(1000, 170, 150, 25);

		btnDownloadConfig.addActionListener(e -> {
			DownloadConfigDialog dialog = new DownloadConfigDialog(student);
			dialog.setVisible(true);
		});

		btnCreateFile.addActionListener(e -> {
			CreateFileDialog dialog = new CreateFileDialog(student);
			dialog.setVisible(true);
		});

		btnCreateDir.addActionListener(e -> {
			CreateDirectoryDialog dialog = new CreateDirectoryDialog(student);
			dialog.setVisible(true);
		});

		btnDeleteDir.addActionListener(e -> {
			DeleteDirDialog dialog = new DeleteDirDialog(student);
			dialog.setVisible(true);
		});

		btnDeleteFile.addActionListener(e -> {
			DeleteFileDialog dialog = new DeleteFileDialog(student);
			dialog.setVisible(true);
		});

		btnDownloadDir.addActionListener(e -> {
			DownloadDirDialog dialog = new DownloadDirDialog(student);
			dialog.setVisible(true);
		});

		btnDownloadFile.addActionListener(e -> {
			DownloadFileDialog dialog = new DownloadFileDialog(student);
			dialog.setVisible(true);
		});

		btnUploadDir.addActionListener(e -> {
			UploadDirDialog dialog = new UploadDirDialog(student);
			dialog.setVisible(true);
		});

		btnUploadFile.addActionListener(e -> {
			UploadFileDialog dialog = new UploadFileDialog(student);
			dialog.setVisible(true);
		});

		btnUploadFiles.addActionListener(e -> {
			UploadFilesDialog dialog = new UploadFilesDialog(student);
			dialog.setVisible(true);
		});

		btnUploadDirs.addActionListener(e -> {
			UploadDirsDialog dialog = new UploadDirsDialog(student);
			dialog.setVisible(true);
		});

		add(btnDownloadConfig);
		add(comboBox);
		add(comboBoxFiles);
		add(btnCreateFile);
		add(btnDeleteFile);
		add(btnDownloadFile);
		add(btnUploadFile);
		add(btnUploadFiles);
		add(btnCreateDir);
		add(btnDeleteDir);
		add(btnDownloadDir);
		add(btnUploadDir);
		add(btnUploadDirs);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JComboBox generateComboBox(String implementation, String accessToken) throws ListFilesException {
		JComboBox comboBox = new JComboBox();

		if (implementation.equals("local")) {
			LocalDirectory localDirectory = new LocalDirectory();
			List<File> dirs = localDirectory.listDirectories(".", true);

			for (File dir : dirs) {
				try {
					comboBox.addItem(dir.getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);
			List<String> files = dropboxDirectory.listFilesWithGivenExtensions("", new String[]{"txt", "json", "zip"}, true);

			for (String file : files) {
				comboBox.addItem(file);
			}
		}

		return comboBox;
	}

	private JComboBox generateComboBoxFiles(String implementation, String accessToken) throws ListFilesException {
		JComboBox comboBox = new JComboBox();

		if (implementation.equals("local")) {
			LocalDirectory localDirectory = new LocalDirectory();
//			ArrayList<File> dirs = localDirectory.listFiles(".", true);
			List<File> files = localDirectory.listFiles(".", true);

			for (File f : files) {
				try {
					comboBox.addItem(f.getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (implementation.equals("dropbox")) {
			DropboxDirectory dropboxDirectory = new DropboxDirectory(accessToken);
			List<String> files = dropboxDirectory.listFilesWithGivenExtensions("", new String[]{"txt", "json"}, true);

			for (String file : files) {
				comboBox.addItem(file);
			}
		}

		return comboBox;
	}
}
package dialogs;

import listeners.DialogCancelButtonListener;
import listeners.DownloadConfigOkButtonListener;
import model.Student;
import views.MainView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Objects;

/**
 * Dialog for downloading config.
 *
 * @author dzimiks
 * Date: 19-04-2019 at 05:26
 */
public class DownloadConfigDialog extends JDialog {

	private JLabel firstName;
	private JTextField tfFirstName;
	private JLabel lastName;
	private JTextField tfLastName;
	private JLabel index;
	private JTextField tfIndex;
	private JTextField path;
	private JButton chooseFile;
	private JFileChooser fileChooser;
	private JComboBox comboBox;
	private File readFromFile;
	private Student student;

	public DownloadConfigDialog(Student student) {
		this.student = student;
		setLayout(null);
		setResizable(false);
		setTitle("Load config");
		setSize(400, 280);
		setLocationRelativeTo(MainView.getInstance());
		init();
	}

	private void init() {
		firstName = new JLabel("Student first name:");
		tfFirstName = new JTextField();

		lastName = new JLabel("Student last name:");
		tfLastName = new JTextField();

		index = new JLabel("Student index:");
		tfIndex = new JTextField();

		path = new JTextField();
		chooseFile = new JButton("Download");
		path.setEditable(false);

		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		fileChooser = new JFileChooser();
//		fileChooser.setAcceptAllFileFilterUsed(false);
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON files", "json"));
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Txt files", "txt"));
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents", "docx", "xlsx", "pptx"));
//		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		chooseFile.addActionListener(e -> {
			if (fileChooser.showDialog(new JDialog(), "Choose") == JFileChooser.APPROVE_OPTION) {
				path.setText(fileChooser.getSelectedFile().getAbsolutePath());
				readFromFile = fileChooser.getSelectedFile();
			}
		});

		comboBox = new JComboBox();

		for (int i = 301; i < 311; i++) {
			comboBox.addItem(i);
		}

		firstName.setBounds(10, 10, 150, 25);
		tfFirstName.setBounds(180, 10, 190, 25);
		lastName.setBounds(10, 50, 150, 25);
		tfLastName.setBounds(180, 50, 190, 25);
		index.setBounds(10, 90, 250, 25);
		tfIndex.setBounds(180, 90, 190, 25);
		path.setBounds(10, 130, 250, 25);
		chooseFile.setBounds(270, 130, 100, 25);
		comboBox.setBounds(10, 170, 130, 25);
		btnOk.setBounds(120, 210, 70, 25);
		btnCancel.setBounds(180, 210, 80, 25);

		btnOk.addActionListener(new DownloadConfigOkButtonListener(this, student));
		btnCancel.addActionListener(new DialogCancelButtonListener(this));

		add(firstName);
		add(tfFirstName);
		add(lastName);
		add(tfLastName);
		add(index);
		add(tfIndex);
		add(path);
		add(chooseFile);
		add(comboBox);
		add(btnOk);
		add(btnCancel);
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public void setFirstName(String firstName) {
		this.firstName.setText(firstName);
	}

	public String getTfFirstName() {
		return tfFirstName.getText();
	}

	public void setTfFirstName(String tfFirstName) {
		this.tfFirstName.setText(tfFirstName);
	}

	public String getLastName() {
		return lastName.getText();
	}

	public void setLastName(String lastName) {
		this.lastName.setText(lastName);
	}

	public String getTfLastName() {
		return tfLastName.getText();
	}

	public void setTfLastName(String tfLastName) {
		this.tfLastName.setText(tfLastName);
	}

	public String getIndex() {
		return index.getText();
	}

	public void setIndex(String index) {
		this.index.setText(index);
	}

	public String getTfIndex() {
		return tfIndex.getText();
	}

	public void setTfIndex(String tfIndex) {
		this.tfIndex.setText(tfIndex);
	}

	public JButton getChooseFile() {
		return chooseFile;
	}

	public void setChooseFile(JButton chooseFile) {
		this.chooseFile = chooseFile;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getPath() {
		return path.getText();
	}

	public void setPath(String path) {
		this.path.setText(path);
	}

	public String getComboBoxItem() {
		return String.valueOf(comboBox.getSelectedItem());
	}

	public void addComboBoxItem(Object item) {
		this.comboBox.addItem(item);
	}

	public void setSelectedComboBoxItem(Object item) {
		this.comboBox.setSelectedItem(item);
	}
}

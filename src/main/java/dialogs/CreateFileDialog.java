package dialogs;

import listeners.DialogCancelButtonListener;
import listeners.CreateFileDialogOkButtonListener;
import views.MainView;

import javax.swing.*;

/**
 * @author dzimiks
 * Date: 19-04-2019 at 02:24
 */
public class CreateFileDialog extends JDialog {

	private JLabel fileName;
	private JTextField tfFileName;
	private JLabel filePath;
	private JTextField tfFilePath;
	private String implementation;

	public CreateFileDialog(String implementation) {
		this.implementation = implementation;
		setLayout(null);
		setResizable(false);
		setTitle("Create new file");
		setSize(400, 170);
		setLocationRelativeTo(MainView.getInstance());
		init();
	}

	private void init() {
		fileName = new JLabel("Name:");
		tfFileName = new JTextField();
		filePath = new JLabel("Path:");
		tfFilePath = new JTextField();

		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		fileName.setBounds(10, 10, 150, 25);
		tfFileName.setBounds(180, 10, 190, 25);
		filePath.setBounds(10, 50, 150, 25);
		tfFilePath.setBounds(180, 50, 190, 25);
		btnOk.setBounds(135, 100, 50, 25);
		btnCancel.setBounds(195, 100, 80, 25);

		btnOk.addActionListener(new CreateFileDialogOkButtonListener(this, implementation));
		btnCancel.addActionListener(new DialogCancelButtonListener(this));

		add(fileName);
		add(tfFileName);
		add(filePath);
		add(tfFilePath);
		add(btnOk);
		add(btnCancel);
	}

	public String getFileName() {
		return fileName.getText();
	}

	public void setFileName(String fileName) {
		this.fileName.setText(fileName);
	}

	public String getTfFileName() {
		return tfFileName.getText();
	}

	public void setTfFileName(String tfFileName) {
		this.tfFileName.setText(tfFileName);
	}

	public String getFilePath() {
		return filePath.getText();
	}

	public void setFilePath(String filePath) {
		this.filePath.setText(filePath);
	}

	public String getTfFilePath() {
		return tfFilePath.getText();
	}

	public void setTfFilePath(String tfFilePath) {
		this.tfFilePath.setText(tfFilePath);
	}
}

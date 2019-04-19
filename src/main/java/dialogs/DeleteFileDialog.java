package dialogs;

import listeners.DeleteFileDialogOkBtnListener;
import listeners.DialogCancelButtonListener;
import model.Student;
import views.MainView;

import javax.swing.*;

public class DeleteFileDialog extends JDialog {
    private JLabel fileName;
    private JTextField tfFileName;
    private JLabel filePath;
    private JTextField tfFilePath;
    private String implementation;
    private Student student;

    public DeleteFileDialog(Student student) {
        this.student = student;
        setLayout(null);
        setResizable(false);
        setTitle("Delete file");
        setSize(400, 170);
        setLocationRelativeTo(MainView.getInstance());
        init();
    }

    private void init() {
        filePath = new JLabel("Path:");
        tfFilePath = new JTextField();

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        filePath.setBounds(10, 50, 150, 25);
        tfFilePath.setBounds(180, 50, 190, 25);
        btnOk.setBounds(135, 100, 60, 25);
        btnCancel.setBounds(195, 100, 80, 25);

        btnOk.addActionListener(new DeleteFileDialogOkBtnListener(this, student));
        btnCancel.addActionListener(new DialogCancelButtonListener(this));

//        add(fileName);
//        add(tfFileName);
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

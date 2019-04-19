package dialogs;

import listeners.DeleteDirDialogOkBtnListener;
import listeners.DialogCancelButtonListener;
import listeners.DownloadDirOkBtnListener;
import model.Student;
import views.MainView;

import javax.swing.*;

public class DownloadDirDialog extends JDialog {
    private JLabel dirSource;
    private JTextField tfDirSource;
    private JLabel dirDestination;
    private JTextField tfDirDestination;
    private String implementation;
    private Student student;

    public DownloadDirDialog(Student student) {
        this.student = student;
        setLayout(null);
        setResizable(false);
        setTitle("Download directory");
        setSize(400, 170);
        setLocationRelativeTo(MainView.getInstance());
        init();
    }

    private void init() {
        String text;
        dirSource = new JLabel("Source");
        tfDirSource = new JTextField();
        dirDestination = new JLabel("Destination:");
        tfDirDestination = new JTextField();

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        dirSource.setBounds(10, 10, 150, 25);
        tfDirSource.setBounds(180, 10, 190, 25);
        dirDestination.setBounds(10, 50, 150, 25);
        tfDirDestination.setBounds(180, 50, 190, 25);
        btnOk.setBounds(135, 100, 60, 25);
        btnCancel.setBounds(195, 100, 80, 25);

        btnOk.addActionListener(new DownloadDirOkBtnListener(this, student));
        btnCancel.addActionListener(new DialogCancelButtonListener(this));

        add(dirSource);
        add(tfDirSource);
        add(dirDestination);
        add(tfDirDestination);
        add(btnOk);
        add(btnCancel);
    }

    public JLabel getDirSource() {
        return dirSource;
    }

    public void setDirSource(JLabel dirSource) {
        this.dirSource = dirSource;
    }

    public String getTfDirSource() {
        return tfDirSource.getText();
    }

    public void setTfDirSource(JTextField tfDirSource) {
        this.tfDirSource = tfDirSource;
    }

    public void setDirDestination(JLabel dirDestination) {
        this.dirDestination = dirDestination;
    }

    public void setTfDirDestination(JTextField tfDirDestination) {
        this.tfDirDestination = tfDirDestination;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getDirDestination() {
        return dirDestination.getText();
    }

    public void setDirDestination(String dirDestination) {
        this.dirDestination.setText(dirDestination);
    }

    public String getTfDirDestination() {
        return tfDirDestination.getText();
    }

    public void setTfDirDestination(String tfDirDestination) {
        this.tfDirDestination.setText(tfDirDestination);
    }
}

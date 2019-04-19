package listeners;

import dialogs.DownloadDirDialog;
import dialogs.UploadDirDialog;
import model.Student;
import models.LocalDirectory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadDirOkBtnListener implements ActionListener {
    private UploadDirDialog dialog;
    private Student student;

    public UploadDirOkBtnListener(UploadDirDialog dialog, Student student) {
        this.dialog = dialog;
        this.student = student;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = dialog.getTfDirSource();
        String dest = dialog.getTfDirDestination();
        String implementation = student.getImplementation();

        if (implementation.equals("local")) {
            try {
                LocalDirectory ld = new LocalDirectory();
                // TODO: Handle exceptions if empty string etc.
//                if (name.equals("")) {
//                    name = "new-local-directory";
//                }
//
//                ld.create(name, path);
                ld.upload(src,dest);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("=== Create directory ===");
            System.out.println("Src: " + src);
            System.out.println("Dest: " + dest);
        } else {
            System.out.println("Error");
        }

        this.dialog.setVisible(false);
    }
}

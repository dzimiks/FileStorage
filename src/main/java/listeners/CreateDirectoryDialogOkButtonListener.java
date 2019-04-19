package listeners;

import dialogs.CreateDirectoryDialog;
import dialogs.CreateFileDialog;
import exceptions.CreateFileException;
import models.LocalDirectory;
import models.LocalFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDirectoryDialogOkButtonListener implements ActionListener {

    private CreateDirectoryDialog dialog;
    private String implementation;

    public CreateDirectoryDialogOkButtonListener(CreateDirectoryDialog dialog, String implementation) {
        this.dialog = dialog;
        this.implementation = implementation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = dialog.getTfFileName().trim().replace(" ", "-");
        String path = dialog.getTfFilePath();


        if (implementation.equals("local")) {
            try {
                LocalDirectory ld = new LocalDirectory();
                // TODO: Handle exceptions if empty string etc.
                if (name.equals("")) {
                    name = "new-local-directory";
                }

                ld.create(name, path);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("=== Create directory ===");
            System.out.println("Name: " + name);
            System.out.println("Path: " + path);
        } else {
            System.out.println("Error");
        }

        this.dialog.setVisible(false);
    }
}

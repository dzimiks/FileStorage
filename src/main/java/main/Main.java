package main;

import com.google.gson.Gson;
import dropbox.models.DropboxDirectory;
import dropbox.models.DropboxFile;
import model.Student;
import models.LocalDirectory;
import models.LocalFile;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) {
		String filename = "/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageApp1/FileStorage/config.json";
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Student student = gson.fromJson(br, Student.class);
			System.out.println("Student name: " + student.getName());
			System.out.println("Implementation: " + student.getImplementation());
			if (student.getImplementation().equals("local")) {
				LocalDirectory ld = new LocalDirectory();
				LocalFile lf = new LocalFile();
				ld.create("UUP2018-januar", ".");
				ld.create("grupa1", "./UUP2018-januar");
				ld.create("grupa2", "./UUP2018-januar");
				lf.create("file1.txt", "./UUP2018-januar/grupa1");
				lf.create("file2.txt", "./UUP2018-januar/grupa2");
			} else {
				if(student.getImplementation().equals("dropbox")){
					DropboxDirectory storage = new DropboxDirectory();
					storage.create("/UUP2018-januar",null);
					storage.create("/UUP2018-januar" + File.separator + "grupa1", null);
					storage.create("/UUP2018-januar" + File.separator + "grupa2", null);
					DropboxFile dropboxFile = new DropboxFile(storage.getClient());
					dropboxFile.upload(
							"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageApp1/FileStorage/test.txt",
							"/UUP2018-januar/grupa1"+File.separator+"test.txt");
					dropboxFile.upload(
							"/home/slime/Faks/6.Semestar/SoftverskeKomponente/FileStorageApp1/FileStorage/test.txt",
							"/UUP2018-januar/grupa2"+File.separator+"test.txt");

				}else{
					System.out.println("Error");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}
}


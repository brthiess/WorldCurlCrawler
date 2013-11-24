package WorldCurlingTourCrawler;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileManager {

	public FileManager() {
		
	}
	
	public ArrayList<Team> openFile() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("teams.tmp");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Team> teams = (ArrayList<Team>) ois.readObject();
			ois.close();
			return teams;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Team>();
		
	}
}

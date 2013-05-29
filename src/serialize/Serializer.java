package serialize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import event.Event;

/*
 * Sauvegarde dans un XML
 */

public class Serializer {

	public void serializer()
	{
		try{
			String userHome = System.getProperty("user.home");
			
			XStream xstream = new XStream(new DomDriver());
			
			SaveData save = new SaveData();
			
			String xml = xstream.toXML(save);
			if (!new File(userHome, ".Skyroom").exists()) {
			      File directory = new File(userHome, ".Skyroom");
			      directory.mkdir();
			      hideWindowsFile(directory);
			}
		    
			
			File fichier = new File(userHome+"/.Skyroom/Save.save");
		
			FileOutputStream fos = new FileOutputStream(fichier);
			try {
				xstream.toXML(save, fos);
				Event.haveNoSave = false;
				} finally {
				fos.close();
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				}
	}
	
	public static void hideWindowsFile(File fileToHide) {
	    String[] cmd = { "attrib", "+H", fileToHide.getAbsolutePath() };
	    try
	    {
	      Process process = Runtime.getRuntime().exec(cmd);
	      process.getOutputStream().close();
	      process.getErrorStream().close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

}

package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Deserializer {
	
public static SaveData saveData;
	
// Permet de lire le XML de sauvegarde	

public void DeSerializere(){
		try {
			String userHome = System.getProperty("user.home");
			
            XStream xstream = new XStream(new DomDriver());
            
            FileInputStream fis = new FileInputStream(new File(userHome+"/.Skyroom/Save.save"));
            
            try {
               
            saveData = (SaveData) xstream.fromXML(fis);
           
           } finally {
               
                fis.close();
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
		
	}
	
	public static SaveData getSaveData()
	{
		return saveData;
	}

	
}

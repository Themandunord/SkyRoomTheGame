package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import event.Event;

public class Deserializer {
	
public static SaveData saveData;
private FileInputStream fis;
	
// Permet de lire le XML de sauvegarde	

public void DeSerializere(){
		try {
			String userHome = System.getProperty("user.home");
			
            XStream xstream = new XStream(new DomDriver());
            
            File file = new File(userHome+"/.Skyroom/Save.save");
            
            if(file.exists())
            	fis = new FileInputStream(file);
            else Event.haveNoSave = true;
            
            try {
            if(fis != null)  
            	saveData = (SaveData) xstream.fromXML(fis);
           
           } finally {
               
                if(fis!=null)
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

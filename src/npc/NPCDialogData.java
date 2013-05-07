package npc;

/**
 * Permet de gérer la récupération du dialogue
 * 
 * @author Rémy
 *
 */

public class NPCDialogData {
	
	/** ID du dialogue */
	public int ID;
	/** Dialog du NPC */
	public String dialog;
	
	/**
	 * Permet de donnée les paramètres
	 * 
	 * @param ID Numéro du dialogue
	 * @param dialog Dialogue
	 */
	public NPCDialogData(int ID, String dialog){
		this.ID = ID;
		this.dialog = dialog;
	}
	
	/**
	 * @return dialog Dialogue
	 */
	public String getDialog(){
		return dialog;
	}
	
	/**
	 * @return ID  Numéro du dialogue
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * Redéfinition de la méthode toString afin de debugger
	 */
	public String toString()
	{
		return "ID : " +Integer.toString(ID)+ " Dialog : " + dialog + "\n";
	}
}


package npc;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de mettre les ID et les Dialogues dans une liste
 * 
 * @author Rémy
 *
 */

public class NPCDialogList {

	/** Liste de NPCDialogData */
	private List<NPCDialogData> list;

	/**
	 * Instanciationd  de la liste 
	 */
    public NPCDialogList(){
        list = new ArrayList<NPCDialogData>();
    }

    /**
     * On ajoute chaque élément à la liste 
     * 
     * @param p ID et dialogue
     */
    public void add(NPCDialogData p){
        list.add(p);
    }

    /**
     * On Récupère le dialogue à partir de la liste
     * @param id
     * @return
     */
    public NPCDialogData getDialogFromID(int id)
    {
    	NPCDialogData personTemp=null;
    	
    	// On parcourt la liste
    	for(NPCDialogData p : this.list)
    	{
    		// Si on trouve l'id
    		if(p.getID()==id)
    			personTemp = p;
    	}
    	/* Si persTemp n'est pas défini, retourne une Personne par défaut
    	 * sinon retourne persTemp.
    	 */
    	
    	return personTemp==null ? new NPCDialogData(-1,"undefined") : personTemp;
    	
    }
    
    /**
     * Redéfinition de la méthode toString()
     */
    public String toString()
    {
    	String buffer="";
    	for(NPCDialogData p : this.list)
    	{
    		buffer+=p.toString();
    	}
    	return buffer;
    }


}
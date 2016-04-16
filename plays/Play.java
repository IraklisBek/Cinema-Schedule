package project.plays;


import java.util.Scanner;

/**
 * Στην κλάση αυτή αρχικοποιούνται τα κοινά στοιχεία των κλάσεων ΜοviePlay
 * και TheaterPlay όπου μπορούν και να τροποποιηθούν.
 * 
 */
public class Play {
    private String name; 
    private String director; 
    private String description; 
    private String actors; 
    /**
     * Κατασκευαστή της Υπερκλάσης των έργων.
     * @param name          το όνομα.
     * @param director      ο σκηνοθέτης.
     * @param description   η περιγραφή.
     * @param actors        οι ηθοποιεί.
     */
    public Play (String name, String director, String description, String actors){
        this.name=name;
        this.director=director;
        this.description=description;
        this.actors=actors;      
    }    
    /**
     * Μέθοδοι για get/set των κοινών στοιχείων των Έργων.
     */
    
    public String getNameOfPlay(){
        return name;
    }
    
    public String getDirector(){
        return director;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getActors(){
        return actors;
    }
    
    public void setNameOfPlay(String aName){
        name=aName;
    }
    
    public void setDirector(String aDirector){
        director=aDirector;
    }
    
    public void setDescription(String aDescription){
        description=aDescription;
    }
    
    public void setActors(String aActors){
        actors=aActors;
    }

}



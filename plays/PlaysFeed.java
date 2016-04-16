package project.plays;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.rooms.RoomsFeed;

/**
 * Η χρησιμότητα αυτής της κλάσης είναι η ένταξη των Έργων σε ένα ArrayList
 * έτσι ώστε, σε συνεργασία με την Υπερκλάση Play, να μπορoύμε να εντάσουμε όλα 
 * τα έργα σε ένα Αrraylist ανεξάρτητα απο το είδος και έτσι θα γλιτώνουμε
 * χρόνο και επαναχρησιμοποίηση κώδικα.  
 */
public class PlaysFeed {
    private ArrayList <Play> plays;
    /**
     * Κατασκευαστής όπου αρχικοποιεί το ArrayList που θα περιλαμβάνει τα Plays.
     * Περιέχει μεθόδους που θα χρησιμεύσουν στον διαχειριστή (add, remove, change)
     */    
    public PlaysFeed(){
        plays = new ArrayList<>();
    }
    /**
     * Προσθέται έργο.
     * @param play το έργο.
     */
    public void addPlay(Play play){
        if(play instanceof MoviePlay){//Αν είναι ταινία. 
            MoviePlay moviePlay = (MoviePlay) play;
            plays.add(moviePlay);
        }
        if(play instanceof TheaterPlay){//Αν είναι έργο.
            TheaterPlay theaterPlay = (TheaterPlay) play;
            plays.add(theaterPlay);
        }else {
            //error
        }
    }
    /**
     * Αφαιρεί έργο.
     * @param name το έργο.
     */
    public void removePlay(String name){
        Iterator<Play> it = plays.iterator();
        while(it.hasNext()){
            Play r = it.next();
            if(r.getNameOfPlay().equals(name)){
                it.remove();
                return;
            }
        }
    }
    /**
     * Τυπώνει τα έργα σε txt.
     */
    public void outPlays(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("plays.txt"))) {
            for(Play play : plays){
                if(play instanceof MoviePlay){
                    out.writeUTF("Cinema \n");
                    //out.writeObject(play); 
                }
                if(play instanceof TheaterPlay){
                    out.writeUTF("Theater \n");
                    //out.writeObject(play);                     
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RoomsFeed.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }        
    /**
     * Tυπώνει τα έργα (χρήσιμο για την υλοποίηση του κώδικα).
     */
    public void showPlays(){
        System.out.println("Movie plays");
        System.out.println();
        for(Play play : plays){
            if(play instanceof MoviePlay){
                MoviePlay moviePlay = (MoviePlay) play;
                System.out.println("Name: " + moviePlay.getNameOfPlay() + "\nDescription: " + moviePlay.getDescription() + "\nDirector: " + moviePlay.getDirector());
                System.out.println();
            }
        }
        System.out.println("Theater plays");
        System.out.println();
        for(Play play : plays){            
            if(play instanceof TheaterPlay){
                TheaterPlay theaterPlay = (TheaterPlay) play;
                System.out.println("Name: " + theaterPlay.getNameOfPlay() + "\nDescription: " + theaterPlay.getDescription() + "\nDirector: " + theaterPlay.getDirector()); 
                System.out.println();
            }           
        }       
    }
    /**
     * Αλλάζει ένα έργο
     * @param play          το έργο.
     * @param name          καινούργιο όνομα
     * @param description   , περιγραφή
     * @param director      , σκνοθέτης
     * @param actors        , ηθοποιεί
     */
    public void changePlay(Play play, String name, String description, String director, String actors, String duration) throws NullPointerException{
        play.setNameOfPlay(name);
        play.setDescription(description);                
        play.setDirector(director);
        play.setActors(actors);
        if(play instanceof MoviePlay){
            MoviePlay moviePlay = (MoviePlay) play;
            moviePlay.setDuration(description);
        }
        if(play instanceof TheaterPlay){
            
        }
    }  
}

package project.rooms;

import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JTextField;

/**
 * Κλάση όπου δηλώνονται τα ιδιαίτερα στοιχεία της αίθουσας κινηματογράφου.
 * Επίσης διαθέτει μεθόδους για τροποποίηση η επιστροφή των στοιχείων αυτών.
 */
public class CinemaRoom extends Room {//Αίθουσα Κινηματογράφου
    
    //private ScreenSizeEnum screenSize;
    //private ScreenTypeEnum  screenType;
    //private SoundEnum  soundType; 
    private HashMap<String, Integer> movieTickets;
    private String screenSize;
    private String  screenType;
    private String  soundType; 
    private int moviePrice;
    /**
     * Κατασκευαστής της Αίθουσα κινηματογράφου.
     * @param name
     * @param seats
     * @param lines
     * @param screenSize    το μέγεθος της οθόνης.
     * @param screenType    ο τύπος της οθόνης.
     * @param soundType     το είδος του sound.
     */
    public CinemaRoom(String roomName, String capacity, String lines, String screenSize, String screenType, String soundType){//, ScreenSizeEnum  screenSize, ScreenTypeEnum  screenType, SoundEnum  soundType){
        super(roomName, capacity, lines);
        movieTickets = new HashMap<>();
        this.screenSize=screenSize;
        this.screenType=screenType;
        this.soundType=soundType;   
    }

    /**
     * Μέθοδοι get/set για τα στοιχεία της αίθουσας κινηματογράφου 
     * 
     */ 

  
    public String getPrice(HashSet roomName){
        return movieTickets.get(roomName).toString();
    }    
    
    public String  getScreenSize(){
        return screenSize.toString();
    }
    
    public String  getScreenType(){
        return screenType.toString();
    }
    
    public String  getSoundType(){
        return soundType.toString();
    }
    
    public void setScreenSize(String aScreenSize){//ScreenSizeEnum  aScreenSize){
        screenSize=aScreenSize;
    }
    
    public void setScreenType(String aScreenType){//ScreenTypeEnum  aScreenType){
        screenType=aScreenType;
    }
    
    public void setSound(String aSound){//SoundEnum  aSound){
        soundType=aSound;
    }    

    
}

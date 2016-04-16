package project.plays;

import project.rooms.ScreenSizeEnum;
import project.rooms.ScreenTypeEnum;
import project.rooms.SoundEnum;
/**
 * Κλάση όπου δηλώνονται τα ιδιαίτερα στοιχεία των ταινιών.
 * Επίσης διαθέτει μεθόδους για τροποποίηση η επιστροφή των στοιχείων αυτών.
 */
public class MoviePlay extends Play  {
    private String duration;
    /**
     * Κατασκευαστής της ταινίας.
     * @param name
     * @param description
     * @param director
     * @param actors
     * @param duration      η διάρκεια της ταινίας.
     */
    public MoviePlay(String name, String description, String director, String actors, String duration){
        super(name, description, director, actors);
        this.duration=duration;
    }
    
    public String getDuration(){
        return duration;
    }
    
    public void setDuration(String newDuration){
        duration=newDuration;
    }
    

}

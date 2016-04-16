package project.tickets;

import java.util.HashMap;
import java.util.Scanner;
import project.rooms.*;

public class MovieTicket {
    private HashMap<String, Integer> movieTickets; // το HashMap με κλειδί τo όνομα του έργου και τιμή την τιμή του εισητιρίου.
    private int moviePrice;

    Scanner scanner = new Scanner(System.in);
    /**
     * Κατασκυαστής εισητιρίου ταινίας.
     * 
     * @param room  η αίθουσα που θα παίζεται η ταινία. Χρήσιμο για να πάρουμε δεδομένα της αίθουσας για τη διαμόρφωση της τιμής του εισητιρίου.
     */
    public MovieTicket(){
        movieTickets = new HashMap<>();
    }

    /**
     * Διαμόρφωση τιμής εισητιρίου ταινίας.
     * 
     * @param cinemaRoom η αίθουσα που θα παίζεται η ταινία. 
     * Χρήσιμο για να πάρουμε δεδομένα της αίθουσας για τη διαμόρφωση της τιμής του εισητιρίου. 
     */
    public void setPrice(CinemaRoom cinemaRoom){
        
        moviePrice=5;
        if("500".equals(cinemaRoom.getScreenSize()) || "600".equals(cinemaRoom.getScreenSize())){
            moviePrice=moviePrice+1;
        }
        if("3D".equals(cinemaRoom.getScreenType())){
            moviePrice=moviePrice+2;
        }
        if("DOLBY DIGITAL TRUEHD".equals(cinemaRoom.getSoundType()) || "DOLBY DIGITAL LIVE".equals(cinemaRoom.getSoundType())){
            moviePrice=moviePrice+1;
        }
        movieTickets.put(cinemaRoom.getNameOfRoom(), moviePrice);
    } 
    
    public String getMoviePrice(String roomName){
        return movieTickets.get(roomName).toString();
    }

}

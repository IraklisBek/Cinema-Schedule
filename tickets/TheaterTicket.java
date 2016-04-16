/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tickets;

import project.plays.TheaterPlay;
import java.util.HashMap;

/**
 *Κλάση διαμόρφωσηςτηςτιμής του εισητιρίου Θεάτρου.
 * @author Irakl_000
 */
public class TheaterTicket{
    private int theaterPrice;
    private HashMap<String, Integer> theaterTickets;
    public TheaterTicket(){
        theaterTickets = new HashMap<>();
    }    
    /**
     * Όμοια με MovieTicket.
     * @param theaterPlay 
     */
    public void setTheaterPrice(TheaterPlay theaterPlay){
        theaterPrice = 15;
        if(theaterPlay.getTheaterValue().ordinal()>2){
            theaterPrice+=10;
        }
        theaterTickets.put (theaterPlay.getNameOfPlay(), theaterPrice);
    }
    
    public int getTheaterPrice(TheaterPlay theaterPlay){
        return theaterTickets.get(theaterPlay.getNameOfPlay());
    }
    
}

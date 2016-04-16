package project.rooms;

import java.util.HashMap;

/**
 *Όμοια με CinemaRoom.
 */
public class TheaterRoom extends Room{//Αίθουσα Θεάτρου
    
    private String dressingRoomsNumber;
    
    public TheaterRoom(String roomName, String capacity, String lines, String dressingRoomsNumber){
        super(roomName, capacity, lines);
        this.dressingRoomsNumber=dressingRoomsNumber;
    }
    //Μέθοδοι get/set για τα στοιχεία της αίθουσας θεάτρου 
    public String getDressingRoomNumber(){
        return dressingRoomsNumber;
    }
    
    public void setDressingRoomNumber(String aNumber){
        dressingRoomsNumber=aNumber;
    }

}

package project.rooms;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;


/**
 * Η χρησιμότητα αυτής της κλάσης είναι η ένταξη των Αιθουσών σε ένα ArrayList
 * έτσι ώστε, σε συνεργασία με την Υπερκλάση Room, να μπορoύμε να εντάσουμε όλες 
 * τις αίθουσες σε ένα Αrraylist ανεξάρτητα απο το είδος και έτσι θα γλιτώνουμε
 * χρόνο και επαναχρησιμοποίηση κώδικα. 
 */
public class RoomsFeed{

    private ArrayList <Room> rooms;// Το ArrayList των Αιθουσών (Rooms).
    private static final Scanner scanner = new Scanner(System.in); 
    /**
     * Κατασκευαστής όπου αρχικοποιεί το ArrayList που θα περιλαμβάνει τα Rooms.
     * Περιέχει μεθόδους που θα χρησιμεύσουν στον διαχειριστή (add, remove, change)
     */
        
    public RoomsFeed(){
        rooms = new ArrayList<>();
    }
    /**
     * Προσθέτει Αίθουσες στο ArrayList.
     * 
     * @param room  Το room που θα μπαίνει στο ArrayList .
     */
    
    public void addRoom(Room room){
        if(room instanceof CinemaRoom){
            CinemaRoom cinemaRoom = (CinemaRoom) room;
            rooms.add(cinemaRoom);
        }
        if(room instanceof TheaterRoom){
            TheaterRoom theaterRoom = (TheaterRoom) room;
            rooms.add(theaterRoom);
        }else {
            //error
        }
    }   
    
    /**
     * Αφαιρεί αίθουσες απο το ArrayList.
     * 
     * @param room To room που θα αφαιρείται απο το ArrayList.
     */
    
    public void removeRoom(String name){
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()){
            Room r = it.next();
            if(r.getNameOfRoom().equals(name)){
                it.remove();
                return;
            }
        }
    }
    /**
     * Tυπώνει τις αίθουσες (χρήσιμο για γενική υλοποίηση του project.
     */
    public void showRooms(){
        
        System.out.println("Cinema Rooms");
        System.out.println();
        for(Room room : rooms){
            if(room instanceof CinemaRoom){
                CinemaRoom cinemaRoom = (CinemaRoom) room;
                System.out.println("Name: " + room.getNameOfRoom() + "\nCapacity: " + room.getCapacity() + "\nScreen size: " + cinemaRoom.getScreenSize() + "\nScreen type: " + cinemaRoom.getScreenType() + "\nSound type: " + cinemaRoom.getSoundType());
                System.out.println();
            }
        }
        System.out.println("Theater Rooms");
        System.out.println();
        for(Room room : rooms){            
            if(room instanceof TheaterRoom){
                TheaterRoom theaterRoom = (TheaterRoom) room;
                System.out.println("Name: " + room.getNameOfRoom() + "\nCapacity: " + room.getCapacity() + "\nDressing rooms: " + theaterRoom.getDressingRoomNumber()); 
                System.out.println();
            }           
        }       
    }
    
    public void showARoom(String name){
        for(Room room : rooms){
            if(room.getNameOfRoom().equals(name)){
                System.out.println(room.getNameOfRoom());
                System.out.println();
                if(room instanceof CinemaRoom){
                    CinemaRoom cinemaRoom = (CinemaRoom) room;
                    System.out.println("\nCapacity: " + room.getCapacity() + "\nScreen size: " + cinemaRoom.getScreenSize() + "\nScreen type: " + cinemaRoom.getScreenType() + "\nSound type: " + cinemaRoom.getSoundType());
                    System.out.println();
                }           
                if(room instanceof TheaterRoom){
                    TheaterRoom theaterRoom = (TheaterRoom) room;
                    System.out.println("Name: " + room.getNameOfRoom() + "\nCapacity: " + room.getCapacity() + "\nDressing rooms: " + theaterRoom.getDressingRoomNumber()); 
                    System.out.println();
                }                
            }
        }
        
        
            
    }    
    /**
     * Tυπώνει σε txt τις αίθουσες.
     */
    public void outRooms(){  
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("rooms2.txt"))) {
            for(Room room : rooms){
                if(room instanceof CinemaRoom){
                    out.writeUTF("Cinema Rooms\n");
                    //out.writeObject(room); 
                }
                if(room instanceof TheaterRoom){
                    out.writeUTF("Theater Rooms\n");
                    //out.writeObject(room);                     
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RoomsFeed.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}

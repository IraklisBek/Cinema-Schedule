package project.rooms;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JTextField;
/**
 * Στην κλάση αυτή αρχικοποιούνται τα κοινά στοιχεία των κλάσεων CinemaRoom
 * και TheaterRoom όπου μπορούν και να τροποποιηθούν.
 */
public class Room {// Υπερκλάση η οποία έχει κοινά στοιχεία των αιθουσών θεάτρου και κινηματογράφου.
    private static final Scanner scanner=new Scanner(System.in);
    public String roomName;// Το όνομα του room.
    private String lines;// Oι σειρές του πίνακα των θέσεων. Βοηθάει στην αρχικοποίηση του πίνακα.
    private boolean[][] lux;
    private Ticket[][] tickets;
    private String capacity;
    private int[] seats;
    
    /**
     * Κατασκευαστής της Υπερκλάσης των Αιθουσών.
     * 
     * @param roomName  το όνομα του room
     * @param lines οι σειρές του room
     */
    public Room(String roomName, String capacity, String lines){ 
        seats = new int[1000];
        seats=getSeatsPerLine();
        this.roomName=roomName;  
        this.capacity=capacity;
        this.lines=lines;
    }
    
    
    /**
     * Μέθοδος για να παίρνουμε το όνομα της αίθουσας. 
     * 
     * @return το όνομα
     */
    
    public String getNameOfRoom(){
        return roomName;
    }
    /**
     * 
     * @return χωριτικότητα αίθουσας. 
     */
    public String getCapacity(){
        return capacity.toString();
    }
    
    public String getLines(){
        return lines;
    }
    /**
     * Τροποποίηση του αριθμό των σειρών.
     * @param aLines 
     */
    public void setLines(String aLines){
        lines=aLines;
    }
    
    public void setCapacity(String newCapacity){
        capacity=newCapacity;
    }
    /**
     * Μέθοδος για κατασκευή των θέσεων/σειρά.
     * @param i         η σειρά
     * @param theSeats  ο αριθμός θέσεων για την σειρά i
     */
    public void seatsPerLine(int i, String theSeats){  
        int seatS = Integer.parseInt(theSeats);
        seats[i] = seatS;
    }
    
    public int[] getSeatsPerLine(){
        return seats;
    }

    /**
     * Μέθοδος τροποποίησης των θέσεων αίθουσας.
     * 
     * @param line  η σειρά
     * @param x     ο αριθμός των θέσεων για την σειρά
     */
    public void changeSeats(int line, int newSeats){
        seats[line]= newSeats;      
    }
    /**
     * Aρχικοποίηση του πίνακα των θέσεων. Καμία θέση δεν είναι πιασμένη και όλες είναι κανονικές.
     */
    public void createSeats(){
        int tLines = Integer.parseInt(lines);
        for (int i=0; i<tLines; i++){
            for(int j=0; j<seats[i]; j++){
                tickets[i][j]=null;
                lux[i][j]=false;
            }
        }
    }
    /**
     * Μέθοδος για να δούμε αν η θέση είναι πιασμένη.
     * Χρήσιμη για την διεπαφή του user.
     * 
     * @param i σειρά.
     * @param j θέση της σειράς.
     * @return το αν η θέση είναι πιασμένη.
     */    
    public Ticket getSeatMode(int i, int j){
        return tickets[i][j];
    }
    /**
     * Μέθοδος για να δούμε αν η θέση είναι κυριλέ.
     * 
     * @param i σειρά.
     * @param j θέση της σειράς.
     * @return το αν η θέση είναι κυριλέ.
     */
    public boolean getSeatKind(int i, int j){
        return lux[i][j];
    }
    /**
     * Δηλώνει οτι η θέση i,j θα είναι πιασμένη με τα στοιχεία του χρήστη name, number.
     * 
     * @param i         η σειρά.
     * @param j         η θέση.
     * @param name      το όνομα.
     * @param number    το τηλέφωνο.
     */
    
    public void setTicket(int line, int seat, String name, String number){
        tickets[line][seat]= new Ticket(name, number);
    }     
  
    /**
     * Μέθοδος για να γίνει μια θέση Luxury ή όχι.
     * 
     * @param i         η σειρά
     * @param j         η θέση της σειράς
     * @param checkLux  true ή false ανάλογα με το τι θέλω.
     */
    public void makeLuxury(int line, int seat, boolean checkLux){
        lux[line][seat]=checkLux;        
    }

    /**
     * Τροποποίηση ονόματος αίθουσας.
     * @param newName το καινούργιο όνομα.
     */
    
    public void setNameOfRoom(String newName){
        roomName=newName;
    }

}

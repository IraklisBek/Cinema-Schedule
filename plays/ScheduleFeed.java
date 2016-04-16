package project.plays;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.rooms.CinemaRoom;
import project.rooms.RoomsFeed;

/**
 *Η κλάση διαμόρφωσης προγράμματος.
 */
public class ScheduleFeed {

    Scanner scanner = new Scanner(System.in);
    private HashMap<String, HashSet<String>> playsRoom;
    private HashMap<String, HashSet<String>> schedules; // το πρόγραμμα.
    private HashSet<String> roomDates;
    private CinemaRoom cinemaRoom;
    private HashSet rooms;
    
    public ScheduleFeed(){
        roomDates = new HashSet<>();
        schedules = new HashMap<>();
        playsRoom = new HashMap<>();
        rooms = new HashSet<>();
    }
    /**
     * Δημιουργεί πρόγραμαμα για ένα έργο.
     * Toποθετείται σε ένα HashMap κλειδί το όνομα του έργου και τιμή η αίθουσα και η ημερομηνία.
     * Επίσης σε ένα άλλο HashMap τοποθετείται σαν τιμή μόνο η αίθουσα που θα παίζεται το έργο
     * έτσι ώστε να μπορούμε να πάρουμε μόνο το δωμάτιο για να πάρουμε την τιμή του έργου (συγκεκριμένα της ταινίας)
     * αφου η τοιμή εξαρτάται αποκληστικά απο τα χαρακτηριστικα της αίθουσας που παίζεται.
     * 
     * @param name  το όνομα της ταινίας
     * @param room  το δωμάτιο 
     * @param date  η ημερομηνία που θα προστεθεί στο HashSet των ημερομηνιών του έργου.
     */
    public void addaSchedule(String name, String room, String date){
        if(schedules.containsKey(name)){
            HashSet<String> roomDates = schedules.get(name);
            if(roomDates.contains(room + ": " + date)){
                
            }else{
                roomDates.add(room + ": " + date);
            }
        }else{
            HashSet<String> roomDates= new HashSet<>();
            rooms.add(room);
            roomDates.add(room + ": " + date);
            schedules.put(name, roomDates); 
            playsRoom.put(name, rooms);
        }
    } 
    /**
     * Τυπώνει όλο το πρόγραμμα (βοήθεια κατα την υλοποίηση της εργασίας).
     */
    public void showWholeSchedule(){
            for(String name : schedules.keySet()){
                System.out.printf("%s", name);
                System.out.println();
                HashSet<String> roomDates = schedules.get(name);
                    for(String roomDate : roomDates){
                        System.out.printf("%s", roomDate);
                        System.out.println();

                }
            }
    }     
    /**
     * 
     * @param name  το όνομα του έργου
     * @return      τις αίθουσες και την ημερομηνίες που παίζεται το έργο.
     */
    public HashSet getRoomDates(String name){
        return schedules.get(name);
    }
    /**
     * 
     * @param name  το όνομα του έργου.
     * @return      τα δωμάτια που παίζεται το έργο 
     */
    public HashSet getRooms(String name){
        return playsRoom.get(name);
    }

    /**
     * Αφαιρεί πρόγραμμα συγκεκριμένου έργου.
     * @param name το έργο που θα αφαιρεθεί από το πρόγραμμα.
     */    
    public void removeSchedule(String name) throws IOException{
        schedules.remove(name);
    }    
    /**
     * Αλλάζει το πρόγραμμα συγκεκριμένου έργου.
     * @param name      το έργο που θα αλλάξει.
     * @param roomDate  η καινούργιες ημερομηνίες και δωμάτια. 
     */
    public void changeASchedule(String name, String roomDate){
        if(schedules.containsKey(name)){
            HashSet roomDates = schedules.get(name);
            roomDates.add(roomDate);
            schedules.put(name, roomDates);  
            
        }else{
            System.out.println("There is no current play with this name");
        }
    }
    /**
     * Τυπώνει το πρόγραμμα σε txt αρχείο.
     */
    public void outSchedule(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("schedule.txt"))) {
            if(!schedules.isEmpty()){
                for(String name : schedules.keySet()){
                    out.writeUTF(name);
                    HashSet<String> roomDates = schedules.get(name);
                    for(String roomDate : roomDates){
                        out.writeUTF(roomDate);
                        //HashSet<String> rooms = playsRoom.get(name);
                        //for(String room : rooms){
                        //    out.writeUTF(roomDate);
                        //}
                    }           
                }   

            }else{
                System.out.println("The schedule is empty"); 
            }
        } catch (IOException ex) {
            Logger.getLogger(RoomsFeed.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }      
    /**
     * Δείχνει πρόγραμμα συγκεκριμένου έργου.
     * @param play το έργο που θα δείξει.
     */ 
    public void showASchedule(Play play){
        if(schedules.containsKey(play.getNameOfPlay())){
            System.out.printf("%s is playing on: \n", play.getNameOfPlay());
            HashSet<String> roomDates=schedules.get(play.getNameOfPlay());
                for(String roomDate : roomDates){
                    System.out.printf("%s", roomDate);
                    System.out.println();           
                }
        }else{
            System.out.println("There is no schedule for this play");
        }
    }

    /**
     *Αφαιρεί όλο το πρόγραμμα.
     */
    public void removeWholeSchedule(){
        for(String name : schedules.keySet()){
            schedules.remove(name);
        }
    }
    //Υλοποίση addSchedule με 2 HashMap.s
    /*
        public void addSchedule(String name, String room, String date){
        if(schedules2.containsKey(name)){
            HashMap <String, HashSet<String>> roomDates = schedules2.get(name);
            if(roomDates.containsKey(room)){
                HashSet<String> dates = roomDates.get(room);
                if(dates.contains(date)){
                    
                }else{
                    dates.add(date); 
                }
            }else{
                HashSet<String> dates = new HashSet<>();
                dates.add(date);
                roomDates.put(room, dates);
            }
        }else{
            HashMap <String, HashSet<String>> roomDates = new HashMap<>();
            HashSet<String> dates = new HashSet<>();
            dates.add(date);
            roomDates.put(room, dates);
            schedules2.put(name, roomDates);
        }
    }
    */
    
}

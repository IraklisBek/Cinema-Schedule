package project.rooms;
/**
 * Kλάση η οποία θα χρησιμεύσει για την κράτηση θέσεων, 
 * όπου σε μια θέση θα αποδίδεται σαν τιμή το Όνομα και το Τηλέφωνο του πελάτη.
 */
public class Ticket {
    private String nameOfHolder;
    private String phoneOfHolder;
    
    public Ticket(String nameOfHolder, String phoneOfHolder){
        this.nameOfHolder=nameOfHolder;
        this.phoneOfHolder=phoneOfHolder;
    }
    
    public String getClientInfo(){
        return nameOfHolder + " " + phoneOfHolder;
    }
    
    public String getTicketHolder(){
        return nameOfHolder;
    }
    
    public String getPhoneOfHolder(){
        return phoneOfHolder;
    }
   
}

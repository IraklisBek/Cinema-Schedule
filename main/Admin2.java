package project.main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import project.rooms.*;
import project.plays.*; 
import project.tickets.MovieTicket;

public class Admin2 {
    private JComboBox roomsList;
   
    private JFrame masterAdmin = new JFrame();

    private JMenuBar menu= new JMenuBar();
        
    private JMenu menuRoom = new JMenu("Room");        
    private JMenuItem addRoom = new JMenuItem("Add a room");
    private JMenuItem changeRoom = new JMenuItem("Change a room");
    private JMenuItem removeRoom = new JMenuItem("Remove a room");
        //JMenuItem showRoom = new JMenuItem("Show a room");
        //JMenuItem showRooms = new JMenuItem("Show all rooms");
        
    private JMenu menuPlay = new JMenu("Plays");
    private JMenuItem addPlay = new JMenuItem("Add a play");
    private JMenuItem changePlay = new JMenuItem("Change a play");
    private JMenuItem removePlay = new JMenuItem("Remove a play");
        
    private JMenu menuSchedule = new JMenu("Schedule");
    private JMenuItem addSchedule = new JMenuItem("Add a schedule");
    private JMenuItem changeSchedule = new JMenuItem ("Change a schedule");
    private JMenuItem removeSchedule = new JMenuItem ("Remove a schedule");
    
    private JDialog slave;
                                            
    private JTextField textScheduleName;
    private JLabel labelScheduleName;
    private JTextField textScheduleRoom;
    private JLabel labelScheduleRoom;
    private JTextField textScheduleDate;
    private JLabel labelScheduleDate; 
    private JTextField textScheduleRoomsDates;
    private JLabel labelScheduleRoomsDates;
    
    private JRadioButton cinemaButton;
    private JRadioButton theaterButton;
    private JRadioButton moviePlayButton;
    private JRadioButton theaterPlayButton;    
    private JButton finish;
    
    private Room room;
    private JTextField[] seats;
    private String[] daSeats;
    
    private Play play;
    
    private RoomsFeed feed;
    private static ArrayList<Room> rooms;  
    
 
    
    private PlaysFeed feedPlay;
    private ArrayList<Play> plays;
    
    private MovieTicket movieTicket;
    
    private ScheduleFeed feedSchedule;
    
    private JLabel[] theLabels= new JLabel[7] ;
    private JTextField[] theTexts = new JTextField[7];
    private String[] theLabelNames = {"Name: ", "Capacity: ", "Lines: ", "ScreenSize: ", "ScreenType: ", "SoundType: ", "DressingRooms: "};
    
    private JLabel[] thePlayLabels= new JLabel[5];
    private JTextField[] theTextsPlays = new JTextField[5];
    private String[] theLabelPlayNames ={"Name: ", "Description: ", "Director: ", "Actors: ", "Duration: "};    
    
    
    public Admin2(){
        //Κλασικά στοιχεία JFrame
        masterAdmin.setTitle("Admin");
        masterAdmin.setLocationRelativeTo(null); 
        masterAdmin.setSize(300,300);     
        masterAdmin.setVisible(true);
        JLabel welcome = new JLabel("Welcome");
        masterAdmin.add(welcome, BorderLayout.CENTER);
        //Αρχικοποιήσεις που θα χρειαστούν στη συνέχεια.
        feed = new RoomsFeed();
        rooms = new ArrayList<>();
        roomsList = new JComboBox();
        daSeats = new String[1000];
        
        feedPlay = new PlaysFeed();
        plays = new ArrayList<>();
        
        feedSchedule = new ScheduleFeed();
        //Εισαγωγή των κύριων στοιχείων του menu.
        menu.add(menuRoom);
        menu.add(menuPlay);
        menu.add(menuSchedule);
        
        
        /**
         * Γενικές κοινές εντολές και χρησιμότητα τους:
         * 
         * 
         * masterAdmin.add(panel ή button ή γενικά συστατικά); --> Προσθέτει συστατικά στο JFrame δηλαδή στο κύριο παράθυρο του GUI.
         * masterAdmin.getContentPane().removeAll(); --> Αφαιρεί όλα τα στοιχεία που υπάρχουν στο GUI
         * masterAdmin.pack(); --> Ομαδοποίηση των πινάκων.
         * panel.add(JLabel ή JTextField ή γενικά συστατικά); --> Bάζει σε πίνακα του GUI ετικέτες και μέρη για εισαγωγή δεδομένων.
         * addActionListener --> click σε συστατικό θα οδηγήσει σε σειρά ενεργειών 
         * +που θα εκτελούν εντολές που έχουν τοποθετηθεί σε μεθόδους μέσα στο ActionListener.
         * JOptionPane --> Bάζει σε ξεχωριστό παράθυρο ένα μήνυμα της επιλογής μας.
         * ΒorderLayout --> Bάζει τα συστατικά σε κάποια θέση της επιλογής μας.
         * GreedLayout --> Εισάγει σε πίνακα συστατικά το ένα μετά το άλλο χωρίςδυνατότητα τροποποίησης διαστάσεων.
         * LayoutFlow --> Εισάγει σε πίνακα συστατικά με διαστάσης της επιλογής μας.
         */
        
        
        //Στοιχείo του Room, addRoom όπου με το πάτημα του θα βγάζει σε interface
        //+όπου ο διαχειριστής θα μπορεί να προσθέσει στοιχεία μιας αίθουσας.
        addRoom.addActionListener(new ActionListener() {

            @Override 
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                String cinemaChoice = "Cinema room";
                String theaterChoice = "Theater room";
                cinemaButton = new JRadioButton(cinemaChoice);
                theaterButton = new JRadioButton(theaterChoice);         
                ButtonGroup group = new ButtonGroup();
                group.add(cinemaButton);
                group.add(theaterButton); 
                JButton buttonChoice = new JButton("Go");
                buttonChoice.addActionListener(new ActionListener() {
                
                    @Override
                    public void actionPerformed(ActionEvent ae) {  
                        JPanel panelData = new JPanel();//Ο πίνακας που θα εμφανίζει τα στοιχεία πρως εισαγωγή.
                        panelData.setVisible(true);
                        GridLayout layout = new GridLayout(8,5);
                        panelData.setLayout(layout);
                        //Πρόσθεση στοιχείων στο interface
                        for(int i=0; i<7; i++){
                            if(i<3){
                                theLabels[i] = new JLabel(theLabelNames[i]);
                                panelData.add(theLabels[i]);
                                //roomsList.add(theTexts[0]);
                                theTexts[i] = new JTextField();
                                panelData.add(theTexts[i]);
                            }else{
                                if(cinemaButton.isSelected() && i<6){
                                    theLabels[i] = new JLabel(theLabelNames[i]);
                                    panelData.add(theLabels[i]);
                                    theTexts[i] = new JTextField();
                                    panelData.add(theTexts[i]);                                    
                                }
                                else if(theaterButton.isSelected() && i==6){
                                    theLabels[i] = new JLabel(theLabelNames[i]);
                                    panelData.add(theLabels[i]);
                                    theTexts[i] = new JTextField();
                                    panelData.add(theTexts[i]);                                    
                                }else{
                                    break;
                                }
                            }
                        }       
                        JButton addSeatsPerLine = new JButton("Insert seats per line");
                        addSeatsPerLine.addActionListener(new ActionListener () {
                            
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                try{
                                    //Σε ξεχωριστό παράθυρο (slave) ο admin θα δίνει τις θέσεις ανά σειρά.
                                    int lines = Integer.parseInt(theTexts[2].getText());
                                    slave = new JDialog(masterAdmin, "Room view", true);
                                    slave.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    slave.setSize(440, 300);   
                                    slave.setLocation(masterAdmin.getX() + 200, masterAdmin.getY() + 100);
                                    FlowLayout layout = new FlowLayout();
                                    layout.setHgap(20);
                                    layout.setVgap(10);   
                                    slave.setLayout(layout);
                                    
                                    JLabel[] create = new JLabel[lines];//Πίνακας JLabel με αριθμό θέσεων = με αυτόν των γραμμών. 
                                    seats = new JTextField[lines];
                                    for(int i=0; i<lines; i++){
                                        int k=i+1;
                                        create[i] = new JLabel("Line " + k);//Ετικέτα που δείχνει αριθμό σειράς.
                                        slave.add(create[i]);
                                        seats[i] = new JTextField(10);//ΤextField για εισαγωγή αριθμού θέσεων.
                                        slave.add(seats[i]);
                                    }
                                }catch(NumberFormatException n){//Περίπτωση όπου ο admin δεν έχει δώσει πρώτα τον αριθμό των σειρών.
                                    JOptionPane.showMessageDialog(masterAdmin, "Please add number of lines first.",
                                        "", JOptionPane.ERROR_MESSAGE);                                       
                                }
                                JButton finish = new JButton("Finish with view");
                                finish.addActionListener(new ActionListener () {

                                    @Override
                                    public void actionPerformed(ActionEvent ae) {
                                        int s = Integer.parseInt(theTexts[2].getText());
                                        for(int i=0; i<s; i++){
                                            daSeats[i]=seats[i].getText();//Δίνουμε σε πίνακα τα περιεχόμενα των JTextFields. 
                                        }
                                        slave.setVisible(false);
                                    }
                                }); 
                                try{
                                    slave.add(finish, BorderLayout.PAGE_END);
                                    slave.setVisible(true);
                                }catch(NullPointerException np){
                                                                   
                                }
                            }
                        }); 
                        panelData.add(addSeatsPerLine, BorderLayout.CENTER);
                        JButton finish = new JButton();
                        finishButton(panelData, finish, 1, "Finish");
                        masterAdmin.add(panelData, BorderLayout.PAGE_END);
                        masterAdmin.pack();                        
                    }
                }); 
                JPanel panelChoices = new JPanel();
                JPanel pressChoice = new JPanel();
                                
                GridLayout layout2 = new GridLayout(1,10);
                panelChoices.setLayout(layout2);
                
                panelChoices.add(cinemaButton);
                panelChoices.add(theaterButton);
                pressChoice.add(buttonChoice);
                masterAdmin.add(panelChoices, BorderLayout.PAGE_START);
                masterAdmin.add(pressChoice, BorderLayout.CENTER);
                masterAdmin.pack();                
            }
        });
        menuRoom.add(addRoom);
        menuRoom.addSeparator();
        //Στοιχείo του Room, changeRoom όπου με το πάτημα του θα βγάζει σε interface
        //+όπου ο διαχειριστής θα μπορεί να αλλάζει στοιχεία μιας αίθουσας.        
        changeRoom.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelChange = new JPanel();//Ο πίνακας που θα έχει TextField το όνομα του room πρoς αφαίρεση.
                GridLayout layout = new GridLayout(8,5);
                panelChange.setLayout(layout);
                
                theLabels[0] = new JLabel("Enter the name of room:");
                panelChange.add(theLabels[0]);
                theTexts[0] = new JTextField();
                panelChange.add(theTexts[0]); 
                
                finish = new JButton("Ok");
                finish.addActionListener(new ActionListener() {
                    boolean flag=true;
                    @Override
                    public void actionPerformed(ActionEvent ae) { 
                        for(Room room : rooms){
                            if(room.getNameOfRoom().equals(theTexts[0].getText())){//Aν υπάρχει η αίθουσα,
                                flag=false;//+ το flag γίνεται false.
                                JPanel panelData = new JPanel();//Ο πίνακας που θα εμφανίζει τα στοιχεία πρως αφαίρεση.
                                panelData.setVisible(true);
                                GridLayout layout = new GridLayout(8,2);
                                panelData.setLayout(layout);
                                //Πρόσθεση στοιχείων στο interface/ Στα TextFields εμφανίζονται τα στοιχεία που έχει ήδη η αίθουσα.
                                theLabels[0] = new JLabel(theLabelNames[0]);
                                panelData.add(theLabels[0]);
                                theTexts[0] = new JTextField(room.getNameOfRoom());
                                panelData.add(theTexts[0]);
                                
                                theLabels[1] = new JLabel(theLabelNames[1]);
                                panelData.add(theLabels[1]);
                                theTexts[1] = new JTextField(room.getCapacity());
                                panelData.add(theTexts[1]);
                                
                                theLabels[2] = new JLabel(theLabelNames[2]);
                                panelData.add(theLabels[2]);
                                theTexts[2] = new JTextField(room.getLines());
                                panelData.add(theTexts[2]);  
                                
                                if(room instanceof CinemaRoom){
                                    CinemaRoom cinemaRoom = (CinemaRoom) room;
                                    theLabels[4] = new JLabel(theLabelNames[4]);
                                    panelData.add(theLabels[4]);
                                    theTexts[4] = new JTextField(cinemaRoom.getScreenSize());
                                    panelData.add(theTexts[4]);   

                                    theLabels[5] = new JLabel(theLabelNames[5]);
                                    panelData.add(theLabels[5]);
                                    theTexts[5] = new JTextField(cinemaRoom.getScreenType());
                                    panelData.add(theTexts[5]); 

                                    theLabels[6] = new JLabel(theLabelNames[6]);
                                    panelData.add(theLabels[6]);
                                    theTexts[6] = new JTextField(cinemaRoom.getSoundType());
                                    panelData.add(theTexts[6]);                                     
                                }
                                if(room instanceof TheaterRoom){
                                    TheaterRoom theaterRoom = (TheaterRoom) room;
                                    theLabels[7] = new JLabel(theLabelNames[7]);
                                    panelData.add(theLabels[7]);
                                    theTexts[7] = new JTextField(theaterRoom.getDressingRoomNumber());
                                    panelData.add(theTexts[7]);                                    
                                }
                                JButton finish = new JButton();
                                //panelData.add(roomsList, BorderLayout.PAGE_END);
                                finishButton(panelData, finish, 2, "Change");
                                masterAdmin.add(panelData, BorderLayout.CENTER);                               
                                masterAdmin.pack();//Ομαδοποίηση των πινάκων.
                            }
                        }
                        if(flag){//Περίπτωση λάθους ονόματος.
                            JOptionPane.showMessageDialog(masterAdmin, "Room: " + theTexts[0].getText() + " does not exists.","", JOptionPane.INFORMATION_MESSAGE);                                                           
                        }
                    }
                });                
                panelChange.add(finish);
                masterAdmin.add(panelChange, BorderLayout.PAGE_START);
                masterAdmin.pack();                 
            }
        }); 
        menuRoom.add(changeRoom);
        menuRoom.addSeparator();
        //Στοιχείo του Room, removeRoom όπου με το πάτημα του θα βγάζει σε interface
        //+όπου ο διαχειριστής θα μπορεί να αφαιρεί στοιχεία μιας αίθουσας.        
        removeRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelRemove = new JPanel();
                GridLayout layout = new GridLayout(8,5);
                panelRemove.setLayout(layout);
                
                theLabels[0] = new JLabel("Enter the name of room:");
                panelRemove.add(theLabels[0]);
                theTexts[0] = new JTextField();
                panelRemove.add(theTexts[0]);                
                finish = new JButton();
                finishButton(panelRemove,finish, 3, "Remove");
                masterAdmin.add(panelRemove, BorderLayout.PAGE_START);
                masterAdmin.pack(); 
            }
        }); 
        menuRoom.add(removeRoom);
        //addPlay, changePlay, removePlay όμοια με του Room.
        addPlay.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                String moviePlayChoice = "Movie play";
                String theaterPlayChoice = "Theater play";
                moviePlayButton = new JRadioButton(moviePlayChoice);
                theaterPlayButton = new JRadioButton(theaterPlayChoice);         
                ButtonGroup group = new ButtonGroup();
                group.add(moviePlayButton);
                group.add(theaterPlayButton); 
                JButton buttonPlayChoice = new JButton("Go");
                buttonPlayChoice.addActionListener(new ActionListener() {
                
                    @Override
                    public void actionPerformed(ActionEvent ae) {  
                        JPanel panelData = new JPanel();
                        panelData.setVisible(true);
                        GridLayout layout = new GridLayout(8,5);
                        panelData.setLayout(layout);
                        
                        for(int i=0; i<5; i++){
                            if(i<4){
                                thePlayLabels[i] = new JLabel(theLabelPlayNames[i]);
                                panelData.add(thePlayLabels[i]);
                                theTextsPlays[i] = new JTextField();
                                panelData.add(theTextsPlays[i]);
                            }else{
                                if(moviePlayButton.isSelected() && i==4){
                                    thePlayLabels[i] = new JLabel(theLabelPlayNames[i]);
                                    panelData.add(thePlayLabels[i]);
                                    theTextsPlays[i] = new JTextField();
                                    panelData.add(theTextsPlays[i]);                                    
                                }
                                if(theaterPlayButton.isSelected()){                                   
                                }
                            }
                        }
                        JButton finish = new JButton();
                        finishButton(panelData, finish, 4, "Add");
                        masterAdmin.add(panelData, BorderLayout.PAGE_END);
                        masterAdmin.pack();                       
                    }
                }); 
                JPanel panelChoices = new JPanel();
                JPanel pressChoice = new JPanel();
                                
                GridLayout layout2 = new GridLayout(1,10);
                panelChoices.setLayout(layout2);
                
                panelChoices.add(moviePlayButton);
                panelChoices.add(theaterPlayButton);
                pressChoice.add(buttonPlayChoice);
                masterAdmin.add(panelChoices, BorderLayout.PAGE_START);
                masterAdmin.add(pressChoice, BorderLayout.CENTER);
                masterAdmin.pack();                
            } 
        }); 
        menuPlay.add(addPlay);
        menuPlay.addSeparator();
        
        changePlay.addActionListener(new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelChangePlay = new JPanel();
                GridLayout layout = new GridLayout(8,5);
                panelChangePlay.setLayout(layout);
                
                thePlayLabels[0] = new JLabel("Enter the name of play:");
                panelChangePlay.add(thePlayLabels[0]);
                theTextsPlays[0] = new JTextField();
                panelChangePlay.add(theTextsPlays[0]);
                
                finish = new JButton("Ok");
                finish.addActionListener(new ActionListener() {
                    boolean flag=true;
                    @Override
                    public void actionPerformed(ActionEvent ae) { 
                        for(Play play : plays){
                            if(play.getNameOfPlay().equals(theTextsPlays[0].getText())){
                                flag=false;
                                JPanel panelData = new JPanel();
                                panelData.setVisible(true);
                                GridLayout layout = new GridLayout(8,5);
                                panelData.setLayout(layout);     
                                
                                thePlayLabels[0] = new JLabel(theLabelPlayNames[0]);
                                panelData.add(thePlayLabels[0]);
                                theTextsPlays[0] = new JTextField(play.getNameOfPlay());
                                panelData.add(theTextsPlays[0]);
                                
                                thePlayLabels[1] = new JLabel(theLabelPlayNames[1]);
                                panelData.add(thePlayLabels[1]);
                                theTextsPlays[1] = new JTextField(play.getDescription());
                                panelData.add(theTextsPlays[1]);
                                
                                thePlayLabels[2] = new JLabel(theLabelPlayNames[2]);
                                panelData.add(thePlayLabels[2]);
                                theTextsPlays[2] = new JTextField(play.getDirector());
                                panelData.add(theTextsPlays[2]);
                                
                                thePlayLabels[3] = new JLabel(theLabelPlayNames[3]);
                                panelData.add(thePlayLabels[3]);
                                theTextsPlays[3] = new JTextField(play.getActors());
                                panelData.add(theTextsPlays[3]);
                                
                               
                                if(play instanceof MoviePlay){
                                    MoviePlay moviePlay = (MoviePlay) play;
                                    thePlayLabels[4] = new JLabel(theLabelPlayNames[4]);
                                    panelData.add(thePlayLabels[4]);
                                    theTextsPlays[4] = new JTextField(moviePlay.getDuration());
                                    panelData.add(theTextsPlays[4]);                                    
                                }
                                if(room instanceof TheaterRoom){                                   
                                }                                                                
                                JButton finish = new JButton();
                                finishButton(panelData, finish, 5, "Change");
                                masterAdmin.add(panelData, BorderLayout.PAGE_END);
                                masterAdmin.pack(); 
                            }
                        }
                        if(flag){
                            JOptionPane.showMessageDialog(masterAdmin, "Play: " + theTextsPlays[0].getText() + " does not exists.","", JOptionPane.INFORMATION_MESSAGE);                                                             
                        }
                    }
                });                
                panelChangePlay.add(finish);
                masterAdmin.add(panelChangePlay, BorderLayout.PAGE_START);
                masterAdmin.pack();            
            }
        });
        menuPlay.add(changePlay);
        menuPlay.addSeparator();
        
        removePlay.addActionListener(new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelPlayRemove = new JPanel();
                GridLayout layout = new GridLayout(8,5);
                panelPlayRemove.setLayout(layout);
                thePlayLabels[0] = new JLabel("Enter the name of play:");
                panelPlayRemove.add(thePlayLabels[0]);
                theTextsPlays[0] = new JTextField();
                panelPlayRemove.add(theTextsPlays[0]);                
                finish = new JButton("Remove");
                finishButton(panelPlayRemove, finish, 6, "Remove: ");
                masterAdmin.add(panelPlayRemove, BorderLayout.PAGE_START);
                masterAdmin.pack();            
            }
        }); 
        menuPlay.add(removePlay);
        //add/change/removeSchedule όμοια με του Room όσον αφορά το κομμάτι του GUI.
        //Για διαφορές στις μεθόδους βλέπε στο ScheduleFeed class.
        addSchedule.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelSchedule = new JPanel();
                panelSchedule.setPreferredSize(new Dimension(250,220));
                
                JButton addRoom = new JButton("Add room");
                addRoom.setPreferredSize(new Dimension(20, 10));
                JButton addDate = new JButton("Add date");
                addDate.setPreferredSize(new Dimension(20, 10));
                JButton done = new JButton("Insert to schedule");
                
                labelScheduleName = new JLabel("Name: ");
                panelSchedule.add(labelScheduleName);
                textScheduleName = new JTextField();
                textScheduleName.setPreferredSize(new Dimension(230,25));
                textScheduleName.setHorizontalAlignment(JTextField.CENTER);               
                
                panelSchedule.add(textScheduleName);
                labelScheduleRoom = new JLabel("Room");
                panelSchedule.add(labelScheduleRoom);
                textScheduleRoom = new JTextField();
                textScheduleRoom.setPreferredSize(new Dimension(230,25));
                textScheduleRoom.setHorizontalAlignment(JTextField.CENTER);
                panelSchedule.add(textScheduleRoom);
              
                
                labelScheduleDate = new JLabel("Dates for Room ");
                panelSchedule.add(labelScheduleDate);
                textScheduleDate = new JTextField();
                textScheduleDate.setHorizontalAlignment(JTextField.CENTER);
                textScheduleDate.setPreferredSize(new Dimension(230,25));
                
                addDate.addActionListener(new ActionListener () {//Σε περίπτωση που ο admin θέλει να βάλει και άλλες ημερομηνίες για κάποια αίθουσα.

                    @Override
                    public void actionPerformed(ActionEvent ae) {   
                        feedSchedule.addaSchedule(textScheduleName.getText(), textScheduleRoom.getText(), textScheduleDate.getText()); 
                        textScheduleDate.setText("");                            
                    }
                });
                
                addRoom.addActionListener(new ActionListener () {//Σε περίπτωση που ο admin θέλει να βάλει και άλλες αίθουσες για κάποια ταινία.
                    @Override
                    public void actionPerformed(ActionEvent ae) {                        
                        feedSchedule.addaSchedule(textScheduleName.getText(), textScheduleRoom.getText(), textScheduleDate.getText()); 
                        textScheduleRoom.setText(""); 
                    }
                }); 
                  
                panelSchedule.add(textScheduleDate);
                done.addActionListener(new ActionListener () {//Τέλος

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        addRoom.setEnabled(true);
                        feedSchedule.addaSchedule(textScheduleName.getText(), textScheduleRoom.getText(), textScheduleDate.getText());                       
                        feedSchedule.showWholeSchedule();
                        //Aπόδοση τιμή εισητιρίου
                        for(Play play : plays){
                            if(play.getNameOfPlay().equals(textScheduleName.getText())){
                                for(Room room : rooms){
                                    if(room.getNameOfRoom().equals(textScheduleRoom.getText())){
                                        if(play instanceof MoviePlay){
                                            CinemaRoom cinemaRoom = (CinemaRoom) room;
                                            MovieTicket movieTicket = new MovieTicket();
                                            movieTicket.setPrice(cinemaRoom);
                                        }
                                    }
                                }
                            }
                        }                          
                        JOptionPane.showMessageDialog(masterAdmin, "Play: " + textScheduleName.getText() + " has benn added to schedule","", JOptionPane.INFORMATION_MESSAGE);                                                  
                    }
                }); 

                addRoom.setPreferredSize(new Dimension(120,20));
                panelSchedule.add(addRoom);
                addDate.setPreferredSize(new Dimension(120,20));
                panelSchedule.add(addDate);
                panelSchedule.add(done);      
                //panelSchedule.add(insert);
                panelSchedule.setVisible(true);
                masterAdmin.add(panelSchedule, BorderLayout.PAGE_START);
                masterAdmin.pack();                  
            }
        }); 
        menuSchedule.add(addSchedule);
        menuSchedule.addSeparator();
        
        changeSchedule.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelChangeSchedule = new JPanel();
                panelChangeSchedule.setVisible(true);
                GridLayout layout = new GridLayout(8,5);
                panelChangeSchedule.setLayout(layout);
                labelScheduleName = new JLabel("Enter the name of play:");
                
                panelChangeSchedule.add(labelScheduleName);
                textScheduleName = new JTextField();
                panelChangeSchedule.add(textScheduleName);
                finish = new JButton("Ok");     
                finish.addActionListener(new ActionListener () {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        masterAdmin.getContentPane().removeAll();
                        labelScheduleName.setVisible(false);
                        JPanel panelSchedule = new JPanel();
                        panelSchedule.setPreferredSize(new Dimension(250,220)); 
                        panelSchedule.setVisible(true);
                        
                        labelScheduleRoomsDates = new JLabel(textScheduleName.getText());
                        labelScheduleName.setSize(2323, 2323);
                        panelSchedule.add(labelScheduleName, BorderLayout.PAGE_START);
                        JLabel label = new JLabel("Room : Dates");
                        panelSchedule.add(label, BorderLayout.PAGE_END);
                        
                        textScheduleRoomsDates = new JTextField(feedSchedule.getRoomDates(textScheduleName.getText()).toString());
                        textScheduleRoomsDates.setPreferredSize(new Dimension(230,25));
                        panelSchedule.add(labelScheduleRoomsDates);
                        panelSchedule.add(labelScheduleName, BorderLayout.LINE_START);
                        panelSchedule.add(textScheduleRoomsDates);
                        
                        JButton finish = new JButton("Change");
                        finish.addActionListener(new ActionListener () {

                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                feedSchedule.changeASchedule(textScheduleName.getText(), textScheduleRoomsDates.getText());                            }
                        }); 
                        
                        panelSchedule.add(finish);
                        masterAdmin.add(panelSchedule);
                        masterAdmin.pack();
                    }
                });
                panelChangeSchedule.add(finish);
                masterAdmin.add(panelChangeSchedule, BorderLayout.PAGE_START);
                masterAdmin.pack();                  
            }           
        });
        menuSchedule.add(changeSchedule);
        menuSchedule.addSeparator();
        
        removeSchedule.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent ae) {
                masterAdmin.getContentPane().removeAll();
                JPanel panelScheduleRemove = new JPanel();
                GridLayout layout = new GridLayout(8,5);
                panelScheduleRemove.setLayout(layout);
                labelScheduleName = new JLabel("Enter the name of play:");
                panelScheduleRemove.add(labelScheduleName);
                textScheduleName = new JTextField();
                panelScheduleRemove.add(textScheduleName);
                
                finish = new JButton();
                finishButton(panelScheduleRemove, finish, 9, "Remove: ");
                masterAdmin.add(panelScheduleRemove, BorderLayout.PAGE_START);
                masterAdmin.pack();                 
            
            }
        });
        menuSchedule.add(removeSchedule);
        //remove all rooms kakh epilogh se periptwsh lathous press.
        masterAdmin.setJMenuBar(menu);  
        
        
    }

    
    public void finishButton(JPanel panel, JButton finish, int i, String label){
        finish = new JButton(label);
        finish.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if(i==1){
                        addRoom();
                        roomsList.add(theTexts[0]);
                        feed.outRooms();
                        JOptionPane.showMessageDialog(masterAdmin, "Room: " + theTexts[0].getText() + " has been added.","", JOptionPane.INFORMATION_MESSAGE);                        
                    }
                    if(i==2){
                        changeRoom();
                        //feed.changeARoom(theTexts);
                        masterAdmin.getContentPane().removeAll();
                        JOptionPane.showMessageDialog(masterAdmin, "Changes have been made for room: " + theTexts[0].getText(),"", JOptionPane.INFORMATION_MESSAGE);                                            
                        feed.outRooms();
                        return;                                
                    }
                    if(i==3){
                        boolean flag=true;
                        removeRoom();
                        for(Room room : rooms){
                            if(room.getNameOfRoom().equals(theTexts[0].getText())){   
                                flag=false;
                                feed.removeRoom(theTexts[0].getText()); 
                                JOptionPane.showMessageDialog(masterAdmin, "Room: " + theTexts[0].getText() + " has been removed.","", JOptionPane.INFORMATION_MESSAGE);    
                                feed.outRooms();
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(masterAdmin, "Room: " + theTexts[0].getText() + " does not exists.","", JOptionPane.INFORMATION_MESSAGE);                                 
                    }
                    if(i==4){
                        addPlay();
                        feedPlay.outPlays();
                        JOptionPane.showMessageDialog(masterAdmin, "Play: " + theTextsPlays[0].getText() + " has been added.","", JOptionPane.INFORMATION_MESSAGE);                                
                    }
                    if(i==5){
                        changePlay();
                        feedPlay.outPlays();
                        JOptionPane.showMessageDialog(masterAdmin, "Changes have been made for play: " + theTextsPlays[0].getText() + "","", JOptionPane.INFORMATION_MESSAGE);                                                        
                    }
                    if(i==6){
                        removePlay();
                        for(Play play : plays){
                            if(play.getNameOfPlay().equals(theTextsPlays[0].getText()) || rooms.isEmpty()){            
                                feed.removeRoom(theTextsPlays[0].getText()); 
                                JOptionPane.showMessageDialog(masterAdmin, "Play: " + theTextsPlays[0].getText() + " has been removed.","", JOptionPane.INFORMATION_MESSAGE);    
                                feedPlay.outPlays();
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(masterAdmin, "Play: " + theTextsPlays[0].getText() + " does not exists.","", JOptionPane.INFORMATION_MESSAGE);                           
                    }
                    if(i==9){
                        removeSchedule();
                        feedSchedule.outSchedule();
                    }
                } catch (IOException ex) {
                        
                }                       
            }
        });  
        
        panel.add(finish);
    }
    
    
        //Μέθοδοι οι οποίες καλούνται στα ActionListeners, 
        //+οι οποίες περιέχοθν μεθόδους απο τις κλάσεις του project
        //+προκειμένου να αγιοποιέιται η αντικειμενοστρέφια.
    
    

    public  void addRoom () throws IOException{
            if(cinemaButton.isSelected()){
                room = new CinemaRoom(theTexts[0].getText(), theTexts[1].getText(), theTexts[2].getText(), theTexts[3].getText(), theTexts[4].getText(), theTexts[5].getText());            
            }
            if(theaterButton.isSelected()){
                room = new TheaterRoom(theTexts[0].getText(), theTexts[1].getText(), theTexts[2].getText(), theTexts[6].getText());
            }else{
                //error
            }         
            try{
                int s = Integer.parseInt(theTexts[2].getText());
                for(int i=0; i<s; i++){
                    room.seatsPerLine(i, daSeats[i]);
                }   
            }catch(NumberFormatException b){
                                    JOptionPane.showMessageDialog(masterAdmin, "You did not add seats per line.",
                                        "", JOptionPane.ERROR_MESSAGE);                 
            }
            rooms.add(room);
            feed.addRoom(room);
            feed.showRooms();        
    }       
       
    private void changeRoom() throws IOException {
        //feed.changeARoom(name, texts); 
        room.setNameOfRoom(theTexts[0].getText());
        room.setCapacity(theTexts[1].getText());
        room.setLines(theTexts[2].getText());
        if(room instanceof CinemaRoom){
            CinemaRoom cinemaRoom = (CinemaRoom) room;
            cinemaRoom.setScreenSize(theTexts[3].getText());
            cinemaRoom.setScreenType(theTexts[4].getText());
            cinemaRoom.setSound(theTexts[5].getText());
        }
        if(room instanceof TheaterRoom){
            TheaterRoom theaterRoom = (TheaterRoom) room;
            theaterRoom.setDressingRoomNumber(theTexts[6].getText());
        }
    }
        
    public void removeRoom() throws IOException {          
        feed.removeRoom(theTexts[0].getText()); 
    }
    
    public void addPlay () throws IOException{
        if(moviePlayButton.isSelected()){
            play = new MoviePlay(theTextsPlays[0].getText(), theTextsPlays[1].getText(), theTextsPlays[2].getText(), theTextsPlays[3].getText(), theTextsPlays[4].getText());
        }
        if(theaterPlayButton.isSelected()){
            play = new TheaterPlay(theTextsPlays[0].getText(), theTextsPlays[1].getText(), theTextsPlays[2].getText(), theTextsPlays[3].getText());
        }else{
            //error
        }
        plays.add(play);
        feedPlay.addPlay(play);
        feedPlay.showPlays();       
    }   
    
    public void changePlay() {
        for(Play play : plays){
            if(play.getNameOfPlay().equals(theTextsPlays[0].getText())){
                feedPlay.changePlay(play, theTextsPlays[0].getText(), theTextsPlays[1].getText(), theTextsPlays[2].getText(), theTextsPlays[3].getText(), theTextsPlays[4].getText());
            }
        }   
    }
    
    public void removePlay() {
        feedPlay.removePlay(theTextsPlays[0].getText()); 
    }    
   
    public void removeSchedule() throws IOException {
        feedSchedule.removeSchedule(textScheduleName.getText());                                
    }
    
    public static void main(String[] args) {
        Admin2 start = new Admin2();
    }    
   
}
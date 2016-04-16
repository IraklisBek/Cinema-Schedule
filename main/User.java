package project.main;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import project.rooms.*;
import project.plays.*;

/**
 *
 * @author 
 */
public class User {
    
    private JFrame masterUser = new JFrame();
    private ScheduleFeed schedule = new ScheduleFeed();
    ArrayList<Play> playslist;
    public User(){
        masterUser.setTitle("User");
        masterUser.setLocationRelativeTo(null); 
        masterUser.setSize(300,150);     
        masterUser.setVisible(true);
        
        //Dhmioutrgia menou
        
        JMenuBar menu= new JMenuBar();
        
        JMenu menuFile = new JMenu("File");
        JMenuItem menuExit = new JMenuItem("Exit");
        
        menu.add(menuFile);
        
        masterUser.setJMenuBar(menu);
        
        //dimiourgia koumpiwn
                
        JButton cinBut = new JButton("Cinema Schedule");
        JButton theBut = new JButton("Theater Schedule");
        
        cinBut.addActionListener( new ActionListener() {

            @Override 
            public void actionPerformed(ActionEvent ae) {
                masterUser.getContentPane().removeAll();
                
               // playslist=feedplay.getPlays(); //kapws 8a pairnei to feedplay pou exei dimiourgisei o admin, mesw arxeiou
                ArrayList<String> plist = new ArrayList();
                plist.add("ad");
                plist.add("ef");
                plist.add("seg");
                final ArrayList<String> dlist = new ArrayList();
                dlist.add("asd");
                dlist.add("sdwf");
                
                
                //autes oi grammes apla test , kanonika 8a pairnei apo arxeio.
                final JList playlist = new JList(plist.toArray()) ;
                playlist.addListSelectionListener(new ListSelectionListener() {

            @Override
                    public void valueChanged(ListSelectionEvent arg0) {
                        if (!arg0.getValueIsAdjusting()) {
                            String k = playlist.getSelectedValue().toString();
                            final JList theschedule = new JList(dlist.toArray());
                            JButton gobut=new JButton("GO!");
                            
                            gobut.addActionListener(new ActionListener(){
                            
                                @Override
                                public void actionPerformed(ActionEvent ae){
                                    masterUser.getContentPane().removeAll();
                                    JTable table = new JTable();
                                    masterUser.add(table , BorderLayout.CENTER );
                                    
                                    
                                    }  
                                });
                            
                            
                            masterUser.add(gobut , BorderLayout.EAST);
                            masterUser.add(theschedule , BorderLayout.CENTER);
                            masterUser.pack();
                            
                            
                            
                            
                            
                            
                }
            }
        });
                JLabel movlist = new JLabel("Movie List:");
                masterUser.add(movlist, BorderLayout.PAGE_START);
                masterUser.add(playlist ,BorderLayout.WEST);
                masterUser.pack();
            }      
        }  
    );
        theBut.addActionListener( new ActionListener() {

            @Override 
            public void actionPerformed(ActionEvent ae) {
                masterUser.getContentPane().removeAll();
                
                //idia logiki me panw// playslist=feedplay.getPlays(); //kapws 8a pairnei to feedplay pou exei dimiourgisei o admin, mesw arxeiou
                ArrayList<String> plist = new ArrayList();
                plist.add("ad");
                plist.add("ef");
                plist.add("seg");
                //grammes 51-56 apla test , kanonika 8a pairnei tis tainies.
                JList playlist = new JList(plist.toArray()) ;
                JLabel thelist = new JLabel("Theater List:");
                masterUser.add(thelist, BorderLayout.PAGE_START);
                masterUser.add(playlist);
                masterUser.pack();
            }
       
        }  
    );
        
        JLabel welcome = new JLabel("Welcome!");
        JPanel choices = new JPanel();
        choices.add(cinBut);
        choices.add(theBut);
        
        masterUser.add(welcome , BorderLayout.CENTER);
        masterUser.add(choices , BorderLayout.PAGE_END);
        masterUser.pack();
    }
    
    public static void main(String[] args) {
        User start = new User();
    }    
}

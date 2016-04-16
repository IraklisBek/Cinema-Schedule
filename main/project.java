package project.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.Console;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.xml.bind.Marshaller.Listener;
import project.plays.*;
import project.rooms.*;


public class Project extends JFrame{
    
    private String adminLog = "Admin";
    private String userLog = "User";
    private Admin2 admin;
    private User user;
    
    public Project(){
        Listener listener = new Listener() {};
        
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setVisible(true);

        JRadioButton adminButton = new JRadioButton(adminLog);
        JRadioButton userButton = new JRadioButton(userLog); 
        ButtonGroup group = new ButtonGroup();
        group.add(adminButton);
        group.add(userButton); 
        
   
        JPanel panel1 = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder(" Log in as: ");
        panel1.setBorder(border);
        GridLayout layout = new GridLayout(5,10);
        panel1.setLayout(layout);

        panel1.add(userButton);
        panel1.add(adminButton);

        JPanel panel2 = new JPanel();
        JButton buttonLog = new JButton("Log in");
        buttonLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(adminButton.isSelected()){
                    admin = new Admin2();
                }else{
                    user = new User();
                }
            }
        });
        panel2.add(buttonLog);
 
        
        JMenuBar menu=new JMenuBar();
        JMenu menuFile=new JMenu("File");      
        JMenuItem menuFileQuit = new JMenuItem("Exit");
        menuFileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.ALT_MASK));
        menuFileQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
        menuFile.add(menuFileQuit);
        menu.add(menuFile);
        setJMenuBar(menu);        
                                
         
        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.PAGE_END);         
        pack();
              
    }

    
    public static void main(String[] args) {
        Project start = new Project();
    }
                   
}

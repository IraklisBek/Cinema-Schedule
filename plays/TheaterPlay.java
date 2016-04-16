package project.plays;

import project.rooms.TheaterRoom;
import java.util.ArrayList;
import java.util.HashMap;

public class TheaterPlay extends Play {//Προβολή Θεάτρικής Παράστασης
    TheaterValueEnum value;
    
    public TheaterPlay(String name, String description, String director, String actors){//, TheaterValueEnum value){
        super(name, description, director, actors);
        //this.value = value;
    }
    
    public TheaterValueEnum getTheaterValue(){
        return value;
    }
    
    public void setTheaterValue(TheaterValueEnum aValue){
        value=aValue;
    }
 
}

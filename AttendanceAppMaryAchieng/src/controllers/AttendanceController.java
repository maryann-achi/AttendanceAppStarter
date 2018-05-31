package controllers;

import helpers.InputHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author mga
 */
public class AttendanceController {
     private final Repository repository;
     List<Swipe>swipes;
    
    /**
     *
     */
        
    public AttendanceController() {
       
        InputHelper inputHelper = new InputHelper();
        char a = inputHelper.readCharacter("Load an already existing Customers File (Y/N)?");
        if (a == 'Y' || a == 'y') {
            String fileName = inputHelper.readString("Enter filename");               
            this.repository = new Repository(fileName);
        }
        else {
            this.repository = new Repository();
        }
    }
   
    /**
     *
     */
    public void run() {
        boolean finished = false;
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesByCardIdOrderedByDateTime(); // 
                    break;                    
                case 'D': 
                    listSwipeStatistics(); //
                    break;
                case 'E':
                    saveFile();
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");  
        System.out.print("\tE. Save file");
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDEQ");
    }    
    
    private void addSwipe() {
        InputHelper inputHelper = new InputHelper();
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");
        
         char a = inputHelper.readCharacter("Add Swipe: S" + " " + "Add Visitor Swipe: V");//select whether you want to input a swipe or a visitor swipe
        if (a == 'S') {
                         
        
        int newSwipeId = inputHelper.readInt("Enter the swipe Id: ");
        String newSwipeCardId = inputHelper.readString("Enter the card Id: ");
        String newSwipeRoom = inputHelper.readString("Enter the room: ");
        Calendar newSwipeSwipeDateTime = inputHelper.readDate("Enter the date and time: ", "yyyy/MM/dd HH:mm:ss");
        
        Swipe newSwipe = new Swipe(newSwipeId,newSwipeCardId, newSwipeRoom, newSwipeSwipeDateTime);
        repository.add(newSwipe);
    }
        else if (a=='V'){
        int newSwipeId = inputHelper.readInt("Enter the swipe Id: ");
        String newSwipeCardId = inputHelper.readString("Enter the card Id: ");
        String newSwipeRoom = inputHelper.readString("Enter the room: ");
        Calendar newSwipeSwipeDateTime = inputHelper.readDate("Enter the date and time: ", "yyyy/MM/dd HH:mm:ss");
        String newVisitorName = inputHelper.readString("Enter the visitor name: ");
        String newVisitorCompany = inputHelper.readString("Enter the visitor company: ");
        
        Swipe newSwipe = new VisitorSwipe(newSwipeId,newSwipeCardId, newSwipeRoom, newSwipeSwipeDateTime, newVisitorName, newVisitorCompany);
        repository.add(newSwipe);
    }
    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");
       
        Iterator it = repository.getItems().listIterator();
        while (it.hasNext()) {
            Swipe s = (Swipe) it.next();
            System.out.println(s);
        }        
    }      
      

    private void listSwipesByCardIdOrderedByDateTime() {  
        InputHelper inputHelper = new InputHelper();
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
        
        String aCardId = inputHelper.readString("Enter the card Id: ");
        ListIterator it = this.repository.getItems().listIterator();
        
        while (it.hasNext()){
            Swipe s = (Swipe) it.next();
            if (s.getCardId().equals(aCardId))   {
                List<Swipe> idSwipes = new ArrayList();
                 idSwipes.add(s);
                System.out.println(idSwipes.toString()); 
        }
            }
        }        
   

    private void listSwipeStatistics() {
        InputHelper inputHelper = new InputHelper();
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
        
        String aRoom = inputHelper.readString("Enter the room: ");
        Iterator it = repository.getItems().listIterator();
        while (it.hasNext()){
            Swipe s = (Swipe) it.next();{
            if (s.getRoom().equals(aRoom)){
                List<Swipe> roomSwipes = new ArrayList();
                roomSwipes.add(s);
                int noOfSwipes = roomSwipes.size();
                Swipe lastSwipe = roomSwipes.get(roomSwipes.size()-1);
                System.out.println(lastSwipe.toString() + " " +  "The number of Swipes for this room are: " + noOfSwipes);
                 }
        }
        }
    }
    /**
     *Create a new file
     */
    private void saveFile(){
    InputHelper inputHelper = new InputHelper();
    System.out.format("\033[31m%s\033[0m%n", "Save file");
    System.out.format("\033[31m%s\033[0m%n", "========================="); 
    char b = inputHelper.readCharacter("Do you want to save the file? Y/N");
  
    if (b == 'Y') {
            String fileName1 = inputHelper.readString("Enter filename");                         
            this.repository.store(fileName1);
    }    
    }
}


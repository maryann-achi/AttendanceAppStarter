/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositories.Repository;
import model.Swipe;
import model.VisitorSwipe;

/**
 *
 * @author machieng15
 */
public class DAOTextImpl implements DAOInterface{
    
    static final char DELIMITER=',';   

    
    @Override
    public Repository load(String filename) {
        Repository repository = new Repository();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { 
            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));        
                int id = Integer.parseInt(temp[0]);
                String cardId = stripQuotes(temp[1]);
                String room = stripQuotes(temp[2]);
                String startDateStr = stripQuotes(temp[3]);
                Date swipeDateTime;
                Calendar calendarDate = null;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                
                 try{
                    swipeDateTime = dateFormat.parse(startDateStr);
                    calendarDate = Calendar.getInstance();
                    calendarDate.setTime(swipeDateTime);
                }
                 catch (ParseException ex) {
                        Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                if  (temp.length <= 4){
                    Swipe swipe = new Swipe (id, cardId, room, calendarDate);
                    repository.add(swipe);
                }
                else{
                String visitorName = stripQuotes(temp[4]);
                String visitorCompany = stripQuotes(temp[5]);
                
                Swipe visitorSwipe = new VisitorSwipe (id, cardId, room, calendarDate, visitorName, visitorCompany);
                repository.add(visitorSwipe);
                }
                              
               
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException ex) {
            Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repository;
    }
       
    @Override
    public void store(String filename, Repository repository) {
       
        try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repository.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private String stripQuotes(String str) {
        return str.substring(1, str.length()-1);
    }
         }

        


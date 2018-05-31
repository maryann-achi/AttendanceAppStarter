package model;

import java.util.Calendar;

/**
 *
 * @author mga
 */
public class VisitorSwipe extends Swipe {
    
    static final char EOLN='\n';       
    static final String QUOTE="\"";
    
    /**
     *
     */
    protected String visitorName;

    /**
     *
     */
    protected String visitorCompany;

    /**
     *
     */
    public VisitorSwipe() {
        super();
        this.visitorName = "Unknown";
        this.visitorCompany = "Unknown";        
    }

    /**
     *
     * @param cardId
     * @param room
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(String cardId, String room, String visitorName, String visitorCompany) {
        super(cardId, room);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }

    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(int swipeId, String cardId, String room, Calendar swipeDateTime, String visitorName, String visitorCompany) {
        super(swipeId, cardId, room, swipeDateTime);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }
    
    // Methods required: getters, setters
    /**
     * @return the visitorName
     */
    public String getVisitorName(){
        return visitorName;
    }
    /**
     * @param visitorName
     * @set the visitorName
     */
    public void setVisitorName(String visitorName){
        this.visitorName = visitorName;
    }
    /**
     * @return the visitorCompany
     */
    public String getVisitorCompany(){
        return visitorCompany;
    }
    /**
     * @param visitorCompany
     * @set the visitorCompany
     */
    public void setVisitorCompany(String visitorCompany){
        this.visitorCompany = visitorCompany;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nName: " + this.visitorName +  " - Company: " + this.visitorCompany;
    }
    
    /**
     *
     * @param delimiter
     * @return
     */
    @Override
    public String toString(char delimiter) {
        final char EOLN='\n';       
        final String QUOTE="\"";        
        String str = QUOTE + super.toString() + QUOTE + delimiter + QUOTE + this.visitorName + QUOTE + delimiter + QUOTE + this.visitorCompany + QUOTE + EOLN;
        return str;
    }    

}

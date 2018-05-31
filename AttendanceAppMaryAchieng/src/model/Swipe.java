package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author mga
 */
public class Swipe {

    /**
     *
     */
    protected final int id;

    /**
     *
     */
    public String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;
    
    private static int lastSwipeIdUsed = 0;
    static final char EOLN='\n';       
    static final String QUOTE="\"";    
    public Comparator <Swipe> SwipeDateTimeComparator = new Comparator<Swipe>() {

        @Override
        public int compare(Swipe card1, Swipe card2) {

          Calendar SwipeDateTime1 = card1.getSwipeDateTime();
          Calendar SwipeDateTime2 = card2.getSwipeDateTime();

          //ascending order
          return SwipeDateTime1.compareTo(SwipeDateTime2);

          //descending order
          //return SwipeDateTime2.compareTo(SwipeDateTime1);
        }

    };        ;
    
    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    }    
    
    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     
    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }
    /**
     * @return the cardId
     */
    public String getCardId(){
        return this.cardId;
    }
    /**
     * @param cardId
     * @set the cardId
     */
    public void setCardId(String cardId){
    this.cardId = cardId;
    }
    /**
     * @return the room
     */
    public String getRoom(){
        return this.room;
    }
    /**
     * @param room
     * @set the room
     */
    public void setRoom(String room){
    this.room = room;
    }
    /**
     * @return the swipeDateTime
     */
    public Calendar getSwipeDateTime(){
    return swipeDateTime;
    }

    // Methods required: getters, setters, hashCode, equals, compareTo, comparator
    
    /**
     *
     * @param calendar
     * @return
     */
        
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    } 
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return getId() * 31 + getCardId().hashCode() * 31 + getRoom().hashCode() * 31 +  getSwipeDateTime().hashCode() * 31;
    }
    
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Swipe) {
            Swipe c = (Swipe)o;
            return  c.getId() == getId() &&
                    c.getCardId().equals(getCardId()) &&
                    c.getRoom().equals(getRoom()) && 
                    c.getSwipeDateTime().equals(getSwipeDateTime());
        } else {
            return false;
        }
    }
    
     public static Comparator<Swipe> CardIdComparator 
                      = new Comparator<Swipe>() {

        @Override
        public int compare(Swipe card1, Swipe card2) {

          String CardId1 = card1.getCardId();
          String CardId2 = card2.getCardId();

          //ascending order
          return CardId1.compareTo(CardId2);

          //descending order
          //return cardId2.compareTo(cardId1);
        }
};
                      
    /**
     *
     * @param compareSwipe
     * @return
     */
 
    public int compareTo(Swipe compareSwipe) {
	
		int swipe1Id = ((Swipe) compareSwipe).getId(); 
		
		//ascending order
		return this.id - swipe1Id;
		
		//descending order
		//return id - this.swipeId;
		
    }    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    
    /**
     *
     * @param delimiter
     * @return
     */
    public String toString(char delimiter) {
        final char EOLN='\n';       
        final String QUOTE="\"";        
        String str = QUOTE + this.id + QUOTE + delimiter + QUOTE + this.cardId + QUOTE + delimiter + QUOTE + this.room + QUOTE + delimiter + 
                QUOTE + formatSwipeDateTime(this.swipeDateTime) + QUOTE + EOLN;
        return str;
    }    
}

import java.sql.Timestamp;
import java.sql.Date;


public class ReservationEntry 
{
    private static String faculty; //Initialize
    private static String room;
    private static Date date;
    private static int seats;
    private static Timestamp timestamp;
    
    public ReservationEntry (String faculty, String room, Date date, int seats, Timestamp time)
    {
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = time;
    }

    //Getters
    
    public static String getFaculty()
    {
        return faculty;
    }
    public static String getRoom()
    {
        return room;
    }
    public static Date getDate()
    {
        return date;
    }
    public static int getSeats()
    {
        return seats;
    }
    public static Timestamp getTimestamp()
    {
        return timestamp;
    } 

    public String toString() //toString for convenience and clarity of information reading
    {
        return ("Reservation made for " + faculty + " for room " + room + " on " + date + " with " + seats + " seats."); // Timestamp: " + timestamp);
    }
}

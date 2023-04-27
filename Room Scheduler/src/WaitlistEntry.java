import java.sql.Timestamp;
import java.sql.Date;


public class WaitlistEntry //Waitlist class
{
    private static String faculty;
    private static Date date;
    private static int seats;
    private static Timestamp timestamp;
    
    public WaitlistEntry (String faculty, Date date, int seats, Timestamp time) //Constructor
    {
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = time;
    }


    //Getters
    public static String getFaculty()
    {
        return faculty;
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

    public String toString() //toString for readability and stuff
    {
        return ("" + faculty + " for " + date + " with " + seats + " seats. Made on: " + timestamp);
    }
}

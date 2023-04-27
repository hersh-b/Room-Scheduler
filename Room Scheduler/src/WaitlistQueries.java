import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Date;

public class WaitlistQueries { //Waitlist database aficionado
    
    private static Connection connection;
    private static ArrayList<String> wlByDate;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement wlDate;
    private static ResultSet resultSet;
    
    
    
    public static String addWaitlistEntry(WaitlistEntry wEntry) //Adds waitlist class to the waitlist database
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addWaitlist = connection.prepareStatement("insert into waitlists (facultyname, date, seats, timestamp) values ('"+wEntry.getFaculty()+"','"+wEntry.getDate()+"',"+wEntry.getSeats()+",'"+wEntry.getTimestamp()+"')");
            addWaitlist.executeUpdate();
            return "worked"; //Beautiful once again :D
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.out.println("Correct error");
            return "Already on waitlist"; // Faculty member already has a spot on the waitlist for the day
        }
    }

    
    
   
    public static ArrayList<String> getWaitlistsByDate() //Gets waitlist for waitlist tab
    {
        wlByDate = new ArrayList<String>();
        
        connection = DBConnection.getConnection();
        try
        {
            ArrayList<Date> dates = Dates.getDates(); //Gets arraylist of dates for organization
            for (Date date: dates) //sorts it by date then timestamp for cleanliness
            {
                wlByDate.add("Waitlists for " + String.valueOf(date));
                wlDate = connection.prepareStatement("select * from waitlists where date = '"+date+"' order by timestamp");
                resultSet = wlDate.executeQuery();
                while(resultSet.next()) //Add to output basically
                {
                    WaitlistEntry wEn = new WaitlistEntry(resultSet.getString(1),resultSet.getDate(2),resultSet.getInt(3),resultSet.getTimestamp(4));
                    wlByDate.add(wEn.toString());
                }
                wlByDate.add("\n");  //Clean :)
            }
            return wlByDate; //Return back to frame
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
}
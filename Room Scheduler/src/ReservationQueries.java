import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Date;

public class ReservationQueries { //Database manager
    
    private static Connection connection;
    private static ArrayList<String> resByDate;
    private static ArrayList<String> resByFac;
    private static ArrayList<String> roomResByDate;
    private static PreparedStatement addRes;
    private static PreparedStatement roomRes;
    private static PreparedStatement resDate;
    private static PreparedStatement resFac;
    private static ResultSet resultSet;
    
    
    
    public static String addReservationEntry(ReservationEntry resEntry) //Adds reservationentry class to database
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addRes = connection.prepareStatement("insert into reservations (facultyname, room, date, seats, timestamp) values ('"+resEntry.getFaculty()+"','"+resEntry.getRoom()+"','"+resEntry.getDate()+"',"+resEntry.getSeats()+",'"+resEntry.getTimestamp()+"')");
            addRes.executeUpdate();
            return "worked"; //Beautiful! :D
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return "failed"; //Bruh
        }
    }
    
    
    public static ArrayList<String> getRoomsReservedByDate(Date date) //Gets rooms that are taken
    {
        roomResByDate = new ArrayList<String>();
        connection = DBConnection.getConnection();
        try
        {
            //System.out.println("where date = '"+date+"'"); Debugging purposes
            roomRes = connection.prepareStatement("select room from reservations where date = '"+date+"'"); //Gets all rooms filtered by specific date
            resultSet = roomRes.executeQuery();
            while(resultSet.next())
            {
                roomResByDate.add(resultSet.getString(1));
            }
            return roomResByDate;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }

    
    public static ArrayList<String> getReservationByDate(Date date) //Gets reservations by date for reservation status panel
    {
        resByDate = new ArrayList<String>(); //Makes arraylist
        
        connection = DBConnection.getConnection();
        try
        {
            resDate = connection.prepareStatement("select * from reservations where date = '"+date+"'");
            resultSet = resDate.executeQuery();
            while(resultSet.next()) //Fills arraylist with selected sql data
            {
                ReservationEntry resEn = new ReservationEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5));
                resByDate.add(resEn.toString());
            }
            return resByDate;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<String> getReservationByFaculty() //For Final phase*
    {
        resByFac = new ArrayList<String>();
        
        connection = DBConnection.getConnection();
        try
        {
            resFac = connection.prepareStatement("select * from reservations order by facultyname");
            resultSet = resFac.executeQuery();
            while(resultSet.next())
            {
                resByFac.add(resultSet.getString(1));
            }
            return resByFac;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    
}

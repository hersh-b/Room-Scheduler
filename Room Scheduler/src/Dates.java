
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Dates
{
    private static Connection connection; 
    private static ArrayList<Date> dates = new ArrayList<Date>();
    private static PreparedStatement addDate;
    private static PreparedStatement getDates;
    private static ResultSet resultSet;
    

    public static String addDate(String date) //Adds date (For final phase)
    {
        connection = DBConnection.getConnection();
        try
        {
            addDate = connection.prepareStatement("insert into dates (date) values (?)");
            addDate.setString(1, date);
            addDate.executeUpdate();
            return "worked";
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return "failed";
        }
        
    }
    
    public static ArrayList<Date> getDates() //Gets list of dates
    {
        dates = new ArrayList<Date>();
        connection = DBConnection.getConnection();
        try
        {
            getDates = connection.prepareStatement("select date from dates order by date"); //Selects all dates from date in order
            resultSet = getDates.executeQuery();
            
            while(resultSet.next())
            {
                dates.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            //System.out.println("Something went wrong w/ the dates sql.");
        }
        //System.out.println(dates);
        return dates;
        
    }
    
}

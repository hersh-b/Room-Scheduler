import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomQueries { //Manages room and database interactions
    
    private static Connection connection;
    private static ArrayList<String> rooms;
    private static ArrayList<String> roomNum;
    private static PreparedStatement getRoomSizes;
    private static PreparedStatement getRooms;
    private static PreparedStatement callRoom;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static PreparedStatement roomSeats;
    private static ResultSet resultSet;
    
    public static ArrayList<String> getAllRoomSizes() //Gets a list of all the rooms sizes
    {
        rooms = new ArrayList<String>();
        
        connection = DBConnection.getConnection();
        try
        {
            getRoomSizes = connection.prepareStatement("select seats from rooms order by seats asc");
            resultSet = getRoomSizes.executeQuery();
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
            
            return rooms;

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<String> getAllRooms() //Gets a list of all the rooms names
    {
        rooms = new ArrayList<String>();
        
        connection = DBConnection.getConnection();
        try
        {
            getRooms = connection.prepareStatement("select name from rooms order by seats asc");
            resultSet = getRooms.executeQuery();
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
            
            return rooms;

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public static String getRoomNumber(int index) //Gets the name of the room based on the given index of the list
    {
        roomNum = new ArrayList<String>();
        connection = DBConnection.getConnection();
        try
        {
            callRoom = connection.prepareStatement("select name from rooms order by seats asc");
            resultSet = callRoom.executeQuery();
            while(resultSet.next())
            {
                roomNum.add(resultSet.getString(1));
            }
            
            return roomNum.get(index);

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    
    public static int roomSize(String roomName) //Gives a specific room's size based on it's name
    {
        
        int size = 0;
        connection = DBConnection.getConnection();
        try
        {
            roomSeats = connection.prepareStatement("select seats from rooms where name = '"+roomName+"'");
            resultSet = roomSeats.executeQuery();
            if (resultSet.next())
            {
                size = resultSet.getInt(1);
            }
            return size;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return 0;
        }
    }
    
    public static void addRoom(RoomEntry room) //Adds room (for final phase*)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (name, seats) values (room.getName, room.getSeats)");
            resultSet = addRoom.executeQuery();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropRoom(RoomEntry room) //Drop room (for final phase*)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("remove from rooms where name = '"+room.getName()+"'");
            resultSet = dropRoom.executeQuery();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}

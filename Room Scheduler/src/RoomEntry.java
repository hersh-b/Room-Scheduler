/*
I'm pretty sure this is for final phase only since we
aren't allowed to make our own in-code database and have to
use the derby and sql database
*/
public class RoomEntry 
{
    private static String name;
    private static int seats;
    
    public RoomEntry(String name, int seats)
    {
        this.name = name;
        this.seats = seats;
    }
    
    public static String getName()
    {
        return name;
    }

    public static int getSeats()
    {
        return seats;
    }
}

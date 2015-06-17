import org.omg.CORBA.DATA_CONVERSION;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteJDBC
{
    private List<String> stops;

    public SQLiteJDBC() {

        try (Connection c = getConnection();)
        {
            stops = new ArrayList<String>();
            c.setAutoCommit(false);

            PreparedStatement stmt = c.prepareStatement("SELECT stop_name FROM stops WHERE stop_name LIKE '%METROLINK STATION%';");
            ResultSet rs = stmt.executeQuery();

            while ( rs.next() ) {
                String  name = rs.getString("stop_name");
                stops.add(name);
            }

            rs.close();
            stmt.close();
            c.close();

        } catch (SQLException e ) {
            throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public int listStops()
    {
        int count = 0;
        while (count < stops.size()) {
            System.out.println( "Stop #" + Integer.toString(count) + ": " + stops.get(count));
            count++;
        }

        System.out.println("");

        return count;
    }

    public String nextTrainAt(int stop, Date present) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-6:00"));

        long millInDay = 24 * 60 * 60 * 1000;

        Date now = new Date((present.getTime() % millInDay) + 3600000 + millInDay);

        Date firstOfDay = new Date(now.getTime() + millInDay);
        Date nextStop = null;

        try (Connection c = getConnection();)
        {

            c.setAutoCommit(false);

            PreparedStatement stmt = c.prepareStatement("SELECT stop_id FROM stops WHERE stop_name='" + stops.get(stop) + "';");
            ResultSet rs = stmt.executeQuery();
            rs.next();

            String stopId = rs.getString("stop_id");

            stmt = c.prepareStatement("SELECT arrival_time FROM stop_times WHERE stop_id='" + stopId + "';");

            System.out.println("\nPlease wait. Retrieving data...");

            rs = stmt.executeQuery();

            while ( rs.next()) {
                try {
                    Date time = sdf.parse(rs.getString("arrival_time"));
                    if (time.before(firstOfDay)) firstOfDay = time;
                    if (time.after(now)) {
                        if (nextStop == null || time.before(nextStop)) nextStop = time;
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
                }
            }

            if (nextStop == null) nextStop = firstOfDay;

            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e ) {
            throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
        }

        String minutes;

        if ((nextStop.getTime() - now.getTime())/60000 == 1) minutes = " minute.";
        else minutes = " minutes.";

        return "\nThe next train is arriving in " + String.valueOf((nextStop.getTime() - now.getTime())/60000) + minutes;

    }

    private static Connection getConnection () throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
        }

        return DriverManager.getConnection("jdbc:sqlite:/Users/Sunny/IdeaProjects/JavaData/src/main/resources/metrolink.db");
    }
}
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SQLiteJDBCTest {

    @Test
    public void Train0In2Minutes() {
        SQLiteJDBC db = new SQLiteJDBC();

        String trainMessage = db.nextTrainAt(0, new Date(9*60*60*1000+42*60*1000 - 24*60*60*1000));

        assertEquals("\nThe next train is arriving in 2 minutes.", trainMessage);
    }

    @Test
    public void Train22In1Minute() {
        SQLiteJDBC db = new SQLiteJDBC();

        String trainMessage = db.nextTrainAt(22, new Date(15*60*60*1000+8*60*1000 - 24*60*60*1000));

        assertEquals("\nThe next train is arriving in 1 minute.", trainMessage);
    }

}

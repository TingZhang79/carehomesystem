import java.sql.SQLException;

public class StageManager {
    public static AbleCareHome ableCareHome;
    public static JdbcConnect connect;

    static {
        try {
            ableCareHome = new AbleCareHome();
            connect = new JdbcConnect();
            connect.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

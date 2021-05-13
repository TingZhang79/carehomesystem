import java.sql.*;
public class JdbcConnect {

    private Connection connection;
    private Statement statement;
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/AbleCareHome","root","/*Mysql_Admin123!@#");
            this.statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public ResultSet Search(String sql){
        ResultSet result;
        try {
            result = statement.executeQuery(sql);
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean boolSearch(String sql){
        ResultSet result;
        try {
            result = statement.executeQuery(sql);
            result.last();
            int rowCount = result.getRow();
            if (rowCount == 0)
                return false;
            else
                return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ResultSet resultSearch(String sql){
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean updateData(String sql){
        try {
            int rowNum = statement.executeUpdate(sql);
            if(rowNum != 0)
                return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}


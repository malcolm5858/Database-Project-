import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private String id;
    private String password;
    private String Priviledge;
    private String loginTime;
    private String logoutTime;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");

    public User(String id) {
        this.id = id;
        try {
            Connection conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
            Statement passstmt = conn.createStatement();
            this.password = passstmt.executeQuery("Select Password From User Where id = " + id).getString("Password");
            Statement Priviledgestmt = conn.createStatement();
            this.Priviledge = Priviledgestmt.executeQuery("Select Priviledge From User Where id = " + id)
                    .getString("Priviledge");
            Statement loginTimestmt = conn.createStatement();
            this.loginTime = loginTimestmt.executeQuery("Select LoginTime From User Where id = " + id)
                    .getString("LoginTime");
            Statement logoutTimestmt = conn.createStatement();
            this.logoutTime = logoutTimestmt.executeQuery("Select LogoutTIme From User Where id = " + id)
                    .getString("LogoutTime");
            passstmt.close();
            Priviledgestmt.close();
            loginTimestmt.close();
            loginTimestmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Could Not execute query's" + sqle);
        }
    }

    public User(String password, String id, String Priviledge) {
        try {
            Connection conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    "Insert into User values('" + id + "', '" + Priviledge + "', '" + password + "', '0', '0')");
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Cannot add Tuple" + sqle);
        }
        this.id = id;
        this.password = password;
        this.Priviledge = Priviledge;
        this.loginTime = "0";
        this.logoutTime = "0";
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPrivilege() {
        return this.Priviledge;
    }

    public String getLoginTime() {
        return this.loginTime;
    }

    public String getLogoutTime() {
        return this.logoutTime;
    }

    public void setLoginTime() {
        loginTime = dtf.format(LocalDateTime.now());
        try {
            Connection conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("Update User  Set loginTime = " + loginTime + "Where id = " + id);
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Could not enter tuple " + sqle);
        }
    }

    public void setLogoutTime() {
        logoutTime = dtf.format(LocalDateTime.now());
        try {
            Connection conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("Update User  Set logoutTime = " + logoutTime + "Where id = " + id);
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("Could not enter tuple " + sqle);
        }
    }
}

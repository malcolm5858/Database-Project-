import java.sql.Connection;
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
        // TODO: get vals from database set id to 0 if user not found
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
        // TODO: add data to database
    }

    public void setLogoutTime() {
        logoutTime = dtf.format(LocalDateTime.now());
        // TODO: add data to database
    }
}

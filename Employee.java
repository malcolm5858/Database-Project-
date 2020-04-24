import java.sql.*;
import javax.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee {
    final String url = "jdbc:postgresql://localhost:5432/grocery_store1";
    final String user = "postgres";
    final String password = "1234";
    private String name;
    private String id;
    private String employee;

    public Employee(String id) {
        this.id = id;
        String sql = "select first_name, last_name " + "from employee " + "where ID = ?";
        String sql2 = "select * " + "from employee " + "where ID = ?";
        String name = "";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setString(1, this.id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.name = rs.getString("first_name") + " " + rs.getString("last_name");
            ps2.setString(1, this.id);
            ResultSet rs2 = ps.executeQuery();
            rs2.next();
            this.employee = this.id + " " + rs2.getString("first_name") + " " + rs2.getString("last_name");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return this.name;
    }

    public String getEmployee() {
        return this.employee;
    }
}
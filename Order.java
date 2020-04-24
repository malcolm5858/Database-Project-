import java.sql.*;
import javax.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Order {
    final String url = "jdbc:postgresql://localhost:5432/grocery_store1";
    final String user = "postgres";
    final String password = "1234";

    private String orderNumber;
    private String order;
    private int saleValue;

    public Order(String ord) {
        String sql = "select SaleValue" + "from order " + "where OrderNumber = ?";
        String sql2 = "select * " + "from order " + "where OrderNumber = ?";
        this.orderNumber = ord;
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2)) {
            ps.setString(1, this.orderNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.saleValue = rs.getInt("SaleValue");
            ps2.setString(1, this.orderNumber);
            ResultSet rs2 = ps.executeQuery();
            rs2.next();
            this.order = this.orderNumber + " " + rs2.getInt("CustomerID") + " " + rs2.getInt("EmployeeID") + " "
                    + rs2.getInt("Model") + " " + rs2.getInt("SaleValue");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getOrder() {
        return this.order;
    }

    public int getSaleValue() {
        return this.saleValue;
    }

}
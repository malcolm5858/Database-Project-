import java.sql.*;
import javax.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//CREATE Table Model(ModelNumber INTEGER, SalePrice INTEGER, Primary KEY (ModelNumber));

public class Model {

    private String mod;
    private int ModelNumber;
    private int saleValue;

    public Model(int ModelNumber) {
        this.ModelNumber = ModelNumber;
        String sql = "select SalePrice, last_name " + "from employee " + "where ModelNumber = ?";
        try (Connection conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, this.ModelNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            this.mod = ModelNumber + " " + rs.getInt("SalePrice");
            this.saleValue = rs.getInt("SalePrice");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getModel() {
        return this.mod;
    }

    public int getSalePrice() {
        return this.saleValue;
    }

}
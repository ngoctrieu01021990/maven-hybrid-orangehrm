package JavaJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerTestConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Get connection...");

        //Lấy ra đối tượng Connection kết nối vào DB
        Connection conn = SQLServerConfig.getSQLServerConnection();
        System.out.println("Open connection" + conn);

        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM [nopcommerce].[dbo].[Customer]";

        // Thực thi câu lệnh SQL trả về đối tượng ResultSet
        ResultSet rs = statement.executeQuery(sql);

        //Duyệt trên KQ trả về
        while (rs.next()) {
            //Di chuyển con trỏ xuông bản ghi kế tiếp
            int empId = rs.getInt(1);
            String employeeUsername = rs.getString(2);
            String employeeEmail = rs.getString("Email");

            System.out.println("=======================");
            System.out.println("Emp ID: " + empId);
            System.out.println("Emp Username: " + employeeUsername);
            System.out.println("Emp Email" + employeeEmail);
        }

        // Đóng kết nối
        conn.close();
        System.out.println("-----------------Close connection------------------");

    }
}

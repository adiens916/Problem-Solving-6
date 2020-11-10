package model;

import controller.dataClass.ReturnInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReturnModel {

    private final Connection con = DatabaseConnector.connection;
    private Statement stmt,stmt2,stmt3,stmt4;
    private ResultSet rs,rs2,r3;

    public int returnCar(ReturnInfo state) {
        int result = 0;
        try {
            stmt = con.createStatement();
            stmt2 = con.createStatement();
            stmt4 = con.createStatement();
            String query2 = " select * from customer_rent_list where campingcar_id='" + state.carId + "'";
            rs2 = stmt2.executeQuery(query2);

            String rent_id = null;
            String a1 = null;
            String a2 = null;
            String a3 = null;
            String a4 = null;
            String a5 = null;
            String a6 = null;
            String a7 = null;
            String a8 = null;
            String a9 = null;
            if (rs2.next()) {
                rent_id = rs2.getString(1);
                a1 = rs2.getString(2);
                a2 = rs2.getString(3);
                a3 = rs2.getString(4);
                a4 = rs2.getString(5);
                a5 = rs2.getString(6);
                a6 = rs2.getString(7);
                a7 = rs2.getString(8);
                a8 = rs2.getString(9);
                a9 = rs2.getString(10);
            }

            String query = "insert into campingcar_return values('"
                    + state.front + "','" + state.right + "','" + state.left + "','" + state.back + "','"
                    + state.fix + "','" + state.carId + "','" + rent_id + "')";
            result = stmt.executeUpdate(query);

            if (result == 1) {
                try {
                    String query4 = "insert into customer_rent_old_list values('"
                            + rent_id + "','" + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" + a5 + "','" + a6 + "','" + a7 + "','" + a8 + "','" + a9 + "')";
                    int result4 = stmt4.executeUpdate(query4);
                    stmt3 = con.createStatement();
                    String query3 = "DELETE FROM customer_rent_list WHERE rent_id = '" + rent_id + "'and c_license_id='1111111'";
                    int result2 = stmt3.executeUpdate(query3);
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return result;
    }
}

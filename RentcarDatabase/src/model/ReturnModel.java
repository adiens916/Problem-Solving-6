package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import controller.dataClass.ResultState;
import controller.dataClass.ReturnInfo;

public class ReturnModel {

    private final Connection con = DatabaseConnector.connection;
    private Statement statement;
    private ResultSet resultSet;

    /*반환 버튼 클릭시 데이터 베이스에 업데이트 해주는 메소드*/
    public ResultState returnCar(ReturnInfo state) {
        try {
            statement = con.createStatement();
            String query2 = " select * from customer_rent_list where campingcar_id='" + state.carId + "'";
            resultSet = statement.executeQuery(query2);

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
            
            if (resultSet.next()) {
                rent_id = resultSet.getString(1);
                a1 = resultSet.getString(2);
                a2 = resultSet.getString(3);
                a3 = resultSet.getString(4);
                a4 = resultSet.getString(5);
                a5 = resultSet.getString(6);
                a6 = resultSet.getString(7);
                a7 = resultSet.getString(8);
                a8 = resultSet.getString(9);
                a9 = resultSet.getString(10);
            }

            String query = "insert into campingcar_return values('"
                    + state.front + "','" + state.right + "','" + state.left + "','" + state.back + "','"
                    + state.fix + "','" + state.carId + "','" + rent_id + "')";
            int result = statement.executeUpdate(query);

            if (result == 1) {
                try {
                    String queryLogging = "insert into customer_rent_old_list values('"
                            + rent_id + "','" + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" + a5 + "','" + a6 + "','" + a7 + "','" + a8 + "','" + a9 + "')";
                    statement.executeUpdate(queryLogging);
                    String queryRetrieving = "DELETE FROM customer_rent_list WHERE rent_id = '" + rent_id + "'and c_license_id='1111111'";
                    statement.executeUpdate(queryRetrieving);
                    return ResultState.SUCCESS;
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return ResultState.FAILURE;
    }
}

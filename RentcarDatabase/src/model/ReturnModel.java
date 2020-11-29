package model;

import model.dataClass.ResultStateDataClass;
import model.dataClass.ReturnDataClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReturnModel {

    private final Connection con = DatabaseConnector.getConnection();

    /*반환 버튼 클릭시 데이터 베이스에 업데이트 해주는 메소드*/
    public ResultStateDataClass returnCar(ReturnDataClass state) {
        try {
            Statement statement = con.createStatement();
            String query2 = " select * from customer_rent_list where campingcar_id='" + state.campingCarId + "'";
            ResultSet resultSet = statement.executeQuery(query2);

            String rent_id = null;
            String[] rentedCarInfo = new String[9];
            
            if (resultSet.next()) {
                rent_id = resultSet.getString(1);
                for (int i = 0; i < rentedCarInfo.length; i++) {
                    rentedCarInfo[i] = resultSet.getString(i + 2);
                }
            }

            String query = "insert into campingcar_return values('"
                    + state.front + "','" + state.right + "','" + state.left + "','" + state.back + "','"
                    + state.fix + "','" + state.campingCarId + "','" + rent_id + "')";
            int result = statement.executeUpdate(query);

            if (result == 1) {
                try {
                    String queryLogging = "insert into customer_rent_old_list values('" + rent_id + "','" +
                            rentedCarInfo[0] + "','" +
                            rentedCarInfo[1] + "','" +
                            rentedCarInfo[2] + "','" +
                            rentedCarInfo[3] + "','" +
                            rentedCarInfo[4] + "','" +
                            rentedCarInfo[5] + "','" +
                            rentedCarInfo[6] + "','" +
                            rentedCarInfo[7] + "','" +
                            rentedCarInfo[8] + "')";
                    statement.executeUpdate(queryLogging);
                    String queryRetrieving = "DELETE FROM customer_rent_list WHERE rent_id = '" + rent_id +
                            "'AND c_license_id='1111111'";
                    statement.executeUpdate(queryRetrieving);
                    return ResultStateDataClass.SUCCESS;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return ResultStateDataClass.FAILURE;
    }
}

package model;

import java.sql.*;
import java.util.ArrayList;
import controller.dataClass.CampingCarInfo;

public class CampingCarModel {
    /* SQL 연결 */
    private final Connection connection = DatabaseConnector.connection;
    private Statement statement;
    private ResultSet resultSet;

    /* [모델] 현재 등록된 캠핑카들의 리스트를 만들어 반환 */
    public ArrayList<CampingCarInfo> readCampingCarList() {
        ArrayList<CampingCarInfo> campingCarList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String query2 = " select * from campingcar_list"; /* SQL 문 */
            resultSet = statement.executeQuery(query2);

            /* SQL을 통해 가져온 데이터를 데이터 클래스 형태로 재구성 */
            while (resultSet.next()) {
                CampingCarInfo campingCar = toCampingCarFromResultSet(resultSet);
                campingCarList.add(campingCar);
            }
        } catch(Exception e1) {
            System.out.println(e1);
        }
        return campingCarList;
    }

    public String createCampingCar(CampingCarInfo campingCar) {
        /* 컨트롤러를 통해 받아온 데이터를 모델에서 입력 */
        try {
            statement = connection.createStatement();
            String query="insert into campingcar_list(" +
                    "cc_name,cc_number,cc_sits,cc_manufacture,cc_manufacture_year," +
                    "cc_mileage,cc_rent_price,cc_regist_date,campingcar_rent_company_id)" +
                    " values('"+
                    campingCar.name+"','"+
                    campingCar.number+"','"+
                    campingCar.seats+"','"+
                    campingCar.manufacturer+"','"+
                    campingCar.builtDate+"','"+
                    campingCar.mileage+"','"+
                    campingCar.rentalFee+"','"+
                    campingCar.registryDate +"','"+
                    campingCar.companyId+"')";
            /* 성공 여부를 반환 */
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return "SUCCESS";
            }
        }catch(Exception e1) {
            /* 데이터 중 하나라도 비어있던 경우 */
            if(campingCar.isNull()) {
                /* 다른 상황을 알려주는 결과값을 반환 */
                return "NULL";
            }
            System.out.println(e1);
        }
        return "FAILURE";
    }

    /* 컨트롤러에서 넘겨 준 id를 찾아서 같은 id를 갖는 캠핑카를 반환 */
    public CampingCarInfo readCampingCar(String id) {
        CampingCarInfo campingCar = new CampingCarInfo();
        try {
            statement = connection.createStatement();
            String query=" select * from campingcar_list where campingcar_list_id='"+id+"';"; /* SQL 문 */
            resultSet = statement.executeQuery(query);
            /* 같은 값이 있는 경우 캠핑카 데이터 클래스 형태로 반환 */
            if(resultSet.next()) {
                campingCar = toCampingCarFromResultSet(resultSet);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return campingCar;
    }

    public String updateCampingCar(CampingCarInfo campingCar) {
        try {
            statement = connection.createStatement();
            /* 입력받은 데이터를 SQL 형태로 구성 */
            String query="update campingcar_list set cc_name='"+campingCar.name+
                    "',cc_number='"+campingCar.number+
                    "',cc_sits='"+campingCar.seats+
                    "',cc_manufacture='"+campingCar.manufacturer+
                    "',cc_manufacture_year='"+campingCar.builtDate+
                    "',cc_mileage='"+campingCar.mileage+
                    "',cc_rent_price='"+campingCar.rentalFee+
                    "',cc_regist_date='"+campingCar.registryDate +
                    "',campingcar_rent_company_id='"+campingCar.companyId+"'" +
                    " where campingcar_list_id='"+campingCar.id+"'";
            /* 성공 여부를 저장 */
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return "SUCCESS";
            }
        }catch(Exception e1) {
            /* 데이터가 비어있던 경우의 상태 */
            if(campingCar.isNull()) {
                return "NULL";
            }
            System.err.println(e1);
        }
        return "FAILURE";
    }

    public String deleteCampingCar(String id) {
        int result1 = 0;
        int result2 = 0;
        try {
            statement = connection.createStatement();
            String query="DELETE FROM campingcar_list WHERE campingcar_list_id = '"+id+"'";
            result1 = statement.executeUpdate(query);
            query="DELETE FROM rentcar_list WHERE rent_id = '"+id+"'";
            result2 = statement.executeUpdate(query);
        } catch (Exception e1) {
            //System.out.println(e1);
        }
        /* 캠핑카 목록과 대여 중인 캠핑카 목록 둘 다에서 삭제된 경우에만 성공
        * (!) 그런데 대여 중인 캠핑카를 삭제하면 문제가 될 수 있다.
        * (!) 대여 중인지를 먼저 파악하고, 대여 중이면 삭제를 거부해야 한다. */
        if (result1 == 1 && result2 == 1) {
            return "SUCCESS";
        } else {
            return "FAILURE";
        }
    }

    /* [모델] ResultSet을 캠핑카 데이터 클래스 형태로 바꿔주는 기능 */
    private CampingCarInfo toCampingCarFromResultSet(ResultSet result) throws Exception {
        CampingCarInfo campingCar = new CampingCarInfo();
        campingCar.id = Integer.toString(result.getInt(1));
        campingCar.name = result.getString(2);
        campingCar.number = result.getString(3);
        campingCar.seats = result.getString(4);
        campingCar.manufacturer = result.getString(5);
        campingCar.builtDate = result.getString(6);
        campingCar.mileage = result.getString(7);
        campingCar.rentalFee = result.getString(8);
        campingCar.registryDate = result.getString(9);
        campingCar.companyId = result.getString(10);
        return campingCar;
    }
}
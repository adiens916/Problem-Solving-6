package model;

import controller.dataClass.CampingCarInfo;

import java.sql.ResultSet;
import java.sql.SQLException;



public class CampingCarModel extends AbstractModel<CampingCarInfo>{

	String getCreateQuery(CampingCarInfo campingCar) {
        return "insert into campingcar_list(" +
                "cc_name,cc_number,cc_sits,cc_manufacture,cc_manufacture_year," +
                "cc_mileage,cc_rent_price,cc_regist_date,campingcar_rent_company_id)" +
                " values('"+
                campingCar.campingCarName+"','"+
                campingCar.campingCarNumber+"','"+
                campingCar.campingCarSits+"','"+
                campingCar.campingCarManufacutre+"','"+
                campingCar.campingCarManufactureYear+"','"+
                campingCar.campingCarMileage+"','"+
                campingCar.campingCarRentprice+"','"+
                campingCar.campingCarRegitstdate +"','"+
                campingCar.campingCarRentCompanyId+"')";
    }
	
	
	String getReadAllQuery() {
        return "select * from campingcar_list";
    }
	
    String getReadQuery(String id) {
        return " select * from campingcar_list where campingcar_list_id='"+id+"';";
    }
    
    String getUpdateQuery(CampingCarInfo campingCar) {
        return "update campingcar_list set cc_name='"+campingCar.campingCarName+
                "',cc_number='"+campingCar.campingCarNumber+
                "',cc_sits='"+campingCar.campingCarSits+
                "',cc_manufacture='"+campingCar.campingCarManufacutre+
                "',cc_manufacture_year='"+campingCar.campingCarManufactureYear+
                "',cc_mileage='"+campingCar.campingCarMileage+
                "',cc_rent_price='"+campingCar.campingCarRentprice+
                "',cc_regist_date='"+campingCar.campingCarRegitstdate +
                "',campingcar_rent_company_id='"+campingCar.campingCarRentCompanyId+"'" +
                " where campingcar_list_id='"+campingCar.campingCarId+"'";
    }
    
    String getDeleteQuery(String id) {
        return "DELETE FROM campingcar_list WHERE campingcar_list_id = '"+id+"'";
    }
	
	
    boolean isNullData(CampingCarInfo campingCar) {
        return campingCar.isNull();
    }

    @Override
    CampingCarInfo toInfoFromResultSet(ResultSet resultSet) throws SQLException{
        CampingCarInfo campingCar = new CampingCarInfo();
        campingCar.campingCarRentCompanyId = Integer.toString(resultSet.getInt(1));
        campingCar.campingCarName = resultSet.getString(2);
        campingCar.campingCarNumber = resultSet.getString(3);
        campingCar.campingCarSits = resultSet.getString(4);
        campingCar.campingCarManufacutre = resultSet.getString(5);
        campingCar.campingCarManufactureYear = resultSet.getString(6);
        campingCar.campingCarMileage = resultSet.getString(7);
        campingCar.campingCarRentprice = resultSet.getString(8);
        campingCar.campingCarRegitstdate = resultSet.getString(9);
        campingCar.campingCarRentCompanyId = resultSet.getString(10);
        return campingCar;
    }
}
	
	
	
	/*
	
	////////////////////////////////////////////////
	// SQL 연결 
    private final Connection connection = DatabaseConnector.getConnection();
    private Statement statement;
    private ResultSet resultSet;

    //
    public ResultState createCampingCar(CampingCarInfo campingCar) {
        // 컨트롤러를 통해 받아온 데이터를 모델에서 입력 
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
            // 성공 여부를 반환 
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return ResultState.SUCCESS;
            }
        }catch(Exception e1) {
            // 데이터 중 하나라도 비어있던 경우 
            if(campingCar.isNull()) {
                // 다른 상황을 알려주는 결과값을 반환 
                return ResultState.NULL;
            }
            System.out.println(e1);
        }
        return ResultState.FAILURE;
    }

    
    
    //
    // 컨트롤러에서 넘겨 준 id를 찾아서 같은 id를 갖는 캠핑카를 반환 
    public CampingCarInfo readCampingCar(String id) {
        CampingCarInfo campingCar = new CampingCarInfo();
        try {
            statement = connection.createStatement();
            String query=" select * from campingcar_list where campingcar_list_id='"+id+"';"; // SQL 문 
            resultSet = statement.executeQuery(query);
            // 같은 값이 있는 경우 캠핑카 데이터 클래스 형태로 반환 
            if(resultSet.next()) {
                campingCar = toCampingCarFromResultSet(resultSet);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return campingCar;
    }

    
    //
    // [모델] 현재 등록된 캠핑카들의 리스트를 만들어 반환 
    public ArrayList<CampingCarInfo> readCampingCarList() {
        ArrayList<CampingCarInfo> campingCarList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String query2 = " select * from campingcar_list"; // SQL 문
            resultSet = statement.executeQuery(query2);
            // SQL을 통해 가져온 데이터를 데이터 클래스 형태로 재구성 
            while (resultSet.next()) {
                CampingCarInfo campingCar = toCampingCarFromResultSet(resultSet);
                campingCarList.add(campingCar);
            }
        } catch(Exception e1) {
            System.out.println(e1);
        }
        return campingCarList;
    }

    
    
    //
    public ResultState updateCampingCar(CampingCarInfo campingCar) {
        try {
            statement = connection.createStatement();
            // 입력받은 데이터를 SQL 형태로 구성 
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
            // 성공 여부를 저장 
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return ResultState.SUCCESS;
            }
        }catch(Exception e1) {
            // 데이터가 비어있던 경우의 상태 
            if(campingCar.isNull()) {
                return ResultState.NULL;
            }
            System.err.println(e1);
        }
        return ResultState.FAILURE;
    }

    //
    public ResultState deleteCampingCar(String id) {
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

        if (result1 == 1 && result2 == 1) {
            return ResultState.SUCCESS;
        } else {
            return ResultState.FAILURE;
        }
    }

    // [모델] ResultSet을 캠핑카 데이터 클래스 형태로 바꿔주는 기능 
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
    
    
    */
    

package model;

import controller.dataClass.GarageInfo;
import controller.dataClass.ResultState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GarageModel {

    private final Connection connection = DatabaseConnector.connection;
    private Statement statement;
    private ResultSet resultSet;

    public ResultState createGarage(GarageInfo garage) {
        try {
            statement = connection.createStatement();
            String query="insert into garage(garage_id,g_name,g_address,g_number,g_manager,g_email)"
                    + " values('"+garage.id
                    +"','"+garage.name
                    +"','"+garage.address
                    +"','"+garage.number
                    +"','"+garage.manager
                    +"','"+garage.emailAddress
                    +"')";
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return ResultState.SUCCESS;
            }
        }catch(Exception e1) {
            if(garage.isNull()) {
                return ResultState.NULL;
            }
        }
        return ResultState.FAILURE;
    }

    public GarageInfo readGarage(String garageId) {
        GarageInfo garage = new GarageInfo();
        try {
            statement = connection.createStatement();
            String query2=" select * from garage where garage_id='"+garageId+"';"; /* SQL 문 */
            resultSet = statement.executeQuery(query2);

            if(resultSet.next()) {
                garage = toGarageInfoFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return garage;
    }

    public ArrayList<GarageInfo> readGarageList() {
        ArrayList<GarageInfo> garageList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String query2=" select * from garage"; /* SQL 문 */
            resultSet = statement.executeQuery(query2);

            while(resultSet.next()) {
                GarageInfo garage = toGarageInfoFromResultSet(resultSet);
                garageList.add(garage);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return garageList;
    }

    public ResultState updateGarage(GarageInfo garage) {
        try {
            statement = connection.createStatement();
            String query="update garage set g_name='" +garage.name
                    +"',g_address='"+garage.address
                    +"',g_number='"+garage.number
                    +"',g_manager='"+garage.manager
                    +"',g_email='"+garage.emailAddress
                    +"' where garage_id='"+garage.id
                    +"';";
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return ResultState.SUCCESS;
            }
        }catch(Exception e1) {
            if(garage.isNull()) {
                return ResultState.NULL;
            }
        }
        return ResultState.FAILURE;
    }

    public ResultState deleteGarage(String garageId) {
        try {
            statement = connection.createStatement();
            String query="DELETE FROM garage WHERE garage_id = '"+garageId+"'";
            int rowCount = statement.executeUpdate(query);
            if (rowCount != 0) {
                return ResultState.SUCCESS;
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return ResultState.FAILURE;
    }

    private GarageInfo toGarageInfoFromResultSet(ResultSet resultSet) throws SQLException{
        GarageInfo garage = new GarageInfo();
        garage.id = Integer.toString(resultSet.getInt(1));
        garage.name = resultSet.getString(2);
        garage.address = resultSet.getString(3);
        garage.number = resultSet.getString(4);
        garage.manager = resultSet.getString(5);
        garage.emailAddress = resultSet.getString(6);
        return garage;
    }
}

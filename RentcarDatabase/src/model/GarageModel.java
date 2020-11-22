package model;

import controller.dataClass.GarageInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GarageModel extends ManagementModel<GarageInfo> {

    @Override
    String getCreateQuery(GarageInfo garage) {
        return "insert into garage(garage_id,g_name,g_address,g_number,g_manager,g_email)"
                + " values('"+garage.id
                +"','"+garage.name
                +"','"+garage.address
                +"','"+garage.number
                +"','"+garage.manager
                +"','"+garage.emailAddress
                +"')";
    }

    @Override
    String getReadAllQuery() {
        return "select * from garage";
    }

    @Override
    String getReadQuery(String id) {
        return " select * from garage where garage_id='"+id+"';"; /* SQL 문 */
    }

    @Override
    String getUpdateQuery(GarageInfo garage) {
        return "update garage " +
                "set g_name='" + garage.name
                +"',g_address='"+garage.address
                +"',g_number='"+garage.number
                +"',g_manager='"+garage.manager
                +"',g_email='"+garage.emailAddress
                +"' where garage_id='"+garage.id
                +"';";
    }

    @Override
    String getDeleteQuery(String id) {
        return "DELETE FROM garage WHERE garage_id = '"+id+"'";
    }

    @Override
    boolean isNullData(GarageInfo garage) {
        return garage.isNull();
    }

    @Override
    GarageInfo createInfo() {
        return new GarageInfo();
    }

    @Override
    GarageInfo toInfoFromResultSet(ResultSet resultSet) throws SQLException{
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

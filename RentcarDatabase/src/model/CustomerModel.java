package model;

import model.dataClass.CustomerDataClass;
import model.dataClass.ResultStateDataClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerModel extends AbstractModel<CustomerDataClass>  {

    // SQL 연결
    private final Connection con = DatabaseConnector.getConnection();

    @Override
    public ResultStateDataClass create(CustomerDataClass info) {
        if (isRegisteredCustomer(info.licenseId)) {
            return ResultStateDataClass.REGISTERED;
        } else {
            return super.create(info);
        }
    }

    @Override
    String getCreateQuery(CustomerDataClass customer) {
        return "insert into customer(license_id,c_name,c_address,c_number,c_email)" +
                " values('"+
                customer.licenseId +"','"+
                customer.name +"','"+
                customer.address +"','"+
                customer.number +"','"+
                customer.emailAddress+"')";
    }

    @Override
    String getReadAllQuery() {
        return "SELECT * FROM customer";
    }

    @Override
    String getReadQuery(String id) {
        return "SELECT * FROM customer where license_id='" + id + "';";
    }

    @Override
    String getUpdateQuery(CustomerDataClass customer) {
        return "update customer set c_name='"+customer.name+"'"
                + ",c_address='"+customer.address
                + "',c_number='"+customer.number+"'"
                + ",c_email='"+customer.emailAddress
                +"' where license_id='"+customer.licenseId+"'";
    }

    @Override
    String getDeleteQuery(String id) {
        return "DELETE FROM customer WHERE license_id = '"+id+"'";
    }

    @Override
    boolean isNullData(CustomerDataClass customer) {
        return customer.isNull();
    }

    @Override
    ArrayList<CustomerDataClass> toInfoFromResultSet(ResultSet resultSet) throws Exception {
    	ArrayList<CustomerDataClass> customerList = new ArrayList<>();
    	
    	while(resultSet.next()) {
    		CustomerDataClass customer = new CustomerDataClass();
            customer.licenseId = Integer.toString(resultSet.getInt(1));
            customer.name = resultSet.getString(2);
            customer.address = resultSet.getString(3);
            customer.number = resultSet.getString(4);
            customer.emailAddress = resultSet.getString(5);
            customerList.add(customer);
    	}
       
        return customerList;
    }

    private boolean isRegisteredCustomer(String customerId) {
        try {
            Statement statement = con.createStatement();
            String query = "select license_id from customer";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                if (customerId.equals(resultSet.getString(1))) {
                    return true;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

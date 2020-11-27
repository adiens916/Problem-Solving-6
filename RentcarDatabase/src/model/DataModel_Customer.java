package model;

import controller.dataClass.CustomerInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataModel_Customer {

    // SQL 연결
    private final Connection con = DatabaseConnector.getConnection();
    private Statement stmt,stmt2;
    private ResultSet rs,rs2;

    public ArrayList<CustomerInfo> getCustomerList() {
        ArrayList<CustomerInfo> customerList = new ArrayList<>();
        try {
            stmt2 = con.createStatement();
            String query2=" select * from customer"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);

            while(rs2.next()) {
                CustomerInfo customer = toCustomerFromResultSet(rs2);
                customerList.add(customer);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return customerList;
    }

    private CustomerInfo toCustomerFromResultSet(ResultSet result) {
        CustomerInfo customer = new CustomerInfo();
        try {
            customer.licenseId = Integer.toString(result.getInt(1));
            customer.name = result.getString(2);
            customer.address = result.getString(3);
            customer.number = result.getString(4);
            customer.emailAddress = result.getString(5);
        } catch(Exception e1) {
            System.out.println(e1);
        }
        return customer;
    }

    public CustomerInfo searchCustomerById(String customerId) {
        CustomerInfo customer = new CustomerInfo();
        try {
            stmt2 = con.createStatement();
            String query2=" select * from customer where license_id='"+customerId+"';"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);
            if(rs2.next()) {
                customer = toCustomerFromResultSet(rs2);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return customer;
    }

    public int addCustomer(CustomerInfo customer) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="insert into customer(license_id,c_name,c_address,c_number,c_email)" +
                    " values('"+
                    customer.licenseId +"','"+
                    customer.name +"','"+
                    customer.address +"','"+
                    customer.number +"','"+
                    customer.emailAddress+"')";
            result =  stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(customer.isNull()){
                result = 2;
            } else if (isRegisteredCustomer(customer.licenseId)) {
                result = 3;
            } else {
                System.out.println(e1);
            }
        }
        return result;
    }

    public int updateCustomer(CustomerInfo customer) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="update customer set c_name='"+customer.name+"'"
                    + ",c_address='"+customer.address
                    + "',c_number='"+customer.number+"'"
                    + ",c_email='"+customer.emailAddress
                    +"' where license_id='"+customer.licenseId+"'";
            result = stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(customer.isNull()) {
                result = 2;
            }
        }
        return result;
    }

    public int deleteCustomer(String customerId) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="DELETE FROM customer WHERE license_id = '"+customerId+"'";
            result = stmt.executeUpdate(query);
        } catch (Exception e1) {
            System.out.println(e1);
        }
        return result;
    }

    private boolean isRegisteredCustomer(String customerId) {
        try {
            stmt2 = con.createStatement();
            String query2 = "select license_id from customer";
            rs = stmt2.executeQuery(query2);
            while(rs.next()) {
                if (customerId.equals(rs.getString(1))) {
                    return true;
                }
            }
        }catch(Exception e2) {
            System.out.println(e2);
        }
        return false;
    }
    
    public String toStringFromCustomerInfo(CustomerInfo customer) {
        String str = customer.licenseId + "\t" +
                customer.name + "\t" +
                customer.address + "\t" +
                customer.number + "\t" +
                customer.emailAddress +"\n";
        return str;
    }

}


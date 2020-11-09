package model;

import controller.dataClass.CustomerInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataModel_Customer {

    // SQL 연결
    static Connection con;
    Statement stmt,stmt2;
    ResultSet rs,rs2;
    String Driver="";
    String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
    String userid="madang";
    String pwd="madang";

    public DataModel_Customer() {
        conDB();
    }

    public void conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println("드라이버 로드 성공");
        } catch(ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            // System.out.println("데이터베이스 연결 준비...");
            con=DriverManager.getConnection(url, userid, pwd);
            //System.out.println("데이터베이스 연결 성공");
        } catch(SQLException e1) {
            e1.printStackTrace();
        }
    }

    public String getCustomerList() {
        StringBuilder results = new StringBuilder();
        try {
            stmt2 = con.createStatement();
            String query2=" select * from customer"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);

            while(rs2.next()) {
                String str = rs2.getInt(1)
                        + "\t" + rs2.getString(2)
                        + "\t" + rs2.getString(3)
                        + "\t" + rs2.getString(4)
                        + "\t" + rs2.getString(5)
                        +"\n";
                results.append(str);
            }
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return results.toString();
    }

    public String searchCustomerById(String customerId) {
        StringBuilder results = new StringBuilder();
        try {
            stmt2 = con.createStatement();
            String query2=" select * from customer where license_id='"+customerId+"';"; /* SQL 문 */
            rs2 = stmt2.executeQuery(query2);
            String str = null;
            if(rs2.next()) {
                str = rs2.getInt(1)
                        + "\t" + rs2.getString(2)
                        + "\t" + rs2.getString(3)
                        + "\t" + rs2.getString(4)
                        + "\t" + rs2.getString(5)+"\n";
            }
            results.append(str);
        }catch(Exception e1) {
            System.out.println(e1);
        }
        return results.toString();
    }

    public int addCustomer(CustomerInfo customer) {
        int result = 0;
        try {
            stmt = con.createStatement();
            String query="insert into customer(license_id,c_name,c_address,c_number,c_email)"
                    + " values('"+customer.licenseId
                    +"','"+customer.name
                    +"','"+customer.address
                    +"','"+customer.number
                    +"','"+customer.emailAddress+"')";
            result =  stmt.executeUpdate(query);
        }catch(Exception e1) {
            if(isNullData(customer)){
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
            if(isNullData(customer)) {
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

    private boolean isNullData(CustomerInfo customer) {
        return customer.name.length() == 0
                || customer.address.length() == 0
                || customer.number.length() == 0
                || customer.emailAddress.length() == 0
                || customer.licenseId.length() == 0;
    }
}


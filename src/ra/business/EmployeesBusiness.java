package ra.business;

import ra.entity.Employees;
import ra.unti.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesBusiness {
    public static List<Employees> findAllEmployees() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employees> employees = null;
        try {
        conn = ConnectionDB.openConnection();
        callSt = conn.prepareCall("{call find_all_employee()}");
        ResultSet rs = callSt.executeQuery();
        employees = new ArrayList<Employees>();
        while (rs.next()) {
            Employees emp = new Employees();
            emp.setEmpID(rs.getString("emp_id"));
            emp.setDeptID(rs.getInt("dept_id"));
            emp.setEmpName(rs.getString("emp_name"));
            emp.setDateOfBirth(rs.getDate("emp_birth_date"));
            emp.setEmpSex(rs.getBoolean("emp_sex"));
            emp.setEmpAddress(rs.getString("emp_address"));
            emp.setEmpEmail(rs.getString("emp_email"));
            emp.setEmpPhone(rs.getString("emp_phone"));
            emp.setEmpAvatar(rs.getString("emp_avartar"));
            emp.setEmpStatus(rs.getBoolean("emp_status"));
            employees.add(emp);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
    public static Employees findEmployeeByID(String empID) {
        Connection conn = null;
        CallableStatement callSt = null;
        Employees emp = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("{call find_emp_by_id(?)}");
            callSt.setString(1, empID);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                emp = new Employees();
                emp.setEmpID(rs.getString("emp_id"));
                emp.setDeptID(rs.getInt("dept_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setDateOfBirth(rs.getDate("emp_birth_date"));
                emp.setEmpSex(rs.getBoolean("emp_sex"));
                emp.setEmpAddress(rs.getString("emp_address"));
                emp.setEmpEmail(rs.getString("emp_email"));
                emp.setEmpPhone(rs.getString("emp_phone"));
                emp.setEmpAvatar(rs.getString("emp_avartar"));
                emp.setEmpStatus(rs.getBoolean("emp_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return emp;
    }

    public static List<Employees> findEmployeeBySex(boolean empSex) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employees> employees = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_emp_by_sex(?)}");
            callSt.setBoolean(1,empSex);
            ResultSet rs = callSt.executeQuery();
            employees = new ArrayList<>();
            while (rs.next()) {
                Employees emp = new Employees();
                emp.setEmpID(rs.getString("emp_id"));
                emp.setDeptID(rs.getInt("dept_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setDateOfBirth(rs.getDate("emp_birth_date"));
                emp.setEmpSex(rs.getBoolean("emp_sex"));
                emp.setEmpAddress(rs.getString("emp_address"));
                emp.setEmpEmail(rs.getString("emp_email"));
                emp.setEmpPhone(rs.getString("emp_phone"));
                emp.setEmpAvatar(rs.getString("emp_avartar"));
                emp.setEmpStatus(rs.getBoolean("emp_status"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }

    public static List<Employees> findEmployeeByDeptId(int deptId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employees> employees = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_emp_by_dept_id(?)}");
            callSt.setInt(1,deptId);
            ResultSet rs = callSt.executeQuery();
            employees = new ArrayList<>();
            while (rs.next()) {
                Employees emp = new Employees();
                emp.setEmpID(rs.getString("emp_id"));
                emp.setDeptID(rs.getInt("dept_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setDateOfBirth(rs.getDate("emp_birth_date"));
                emp.setEmpSex(rs.getBoolean("emp_sex"));
                emp.setEmpAddress(rs.getString("emp_address"));
                emp.setEmpEmail(rs.getString("emp_email"));
                emp.setEmpPhone(rs.getString("emp_phone"));
                emp.setEmpAvatar(rs.getString("emp_avartar"));
                emp.setEmpStatus(rs.getBoolean("emp_status"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
    public static boolean saveEmployee(Employees emp) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean success = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_emp(?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, emp.getEmpID());
            callSt.setInt(2, emp.getDeptID());
            callSt.setString(3, emp.getEmpName());
            callSt.setDate(4, new java.sql.Date(emp.getDateOfBirth().getTime()));
            callSt.setBoolean(5,emp.isEmpSex());
            callSt.setString(6, emp.getEmpAddress());
            callSt.setString(7, emp.getEmpEmail());
            callSt.setString(8, emp.getEmpPhone());
            callSt.setString(9, emp.getEmpAvatar());
            callSt.setBoolean(10, emp.getEmpStatus());
            callSt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return success;
    }
    public static boolean updateEmployee(Employees emp) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean success = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_emp(?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, emp.getEmpID());
            callSt.setInt(2, emp.getDeptID());
            callSt.setString(3, emp.getEmpName());
            callSt.setDate(4, new java.sql.Date(emp.getDateOfBirth().getTime()));
            callSt.setBoolean(5,emp.isEmpSex());
            callSt.setString(6, emp.getEmpAddress());
            callSt.setString(7, emp.getEmpEmail());
            callSt.setString(8, emp.getEmpPhone());
            callSt.setString(9, emp.getEmpAvatar());
            callSt.setBoolean(10, emp.getEmpStatus());
            callSt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }
    public static boolean deleteEmployee(Employees emp) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean success = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_emp(?)}");
            callSt.setString(1, emp.getEmpID());
            callSt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return success;
    }
    public static int countEmployeesByAge(int age1, int age2) {
        Connection conn = null;
        CallableStatement callSt = null;
        int count = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statistic_emp_by_age(?,?,?)}");
            callSt.setInt(1, age1);
            callSt.setInt(2, age2);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            count = callSt.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return count;
    }
    public static Employees findEmpByEmailOrPhone(String search_value){
        Connection conn = null;
        CallableStatement callSt = null;
        Employees emp = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_emp_by_email_or_by_phone(?)}");
            callSt.setString(1, search_value);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                emp = new Employees();
                emp.setEmpID(rs.getString("emp_id"));
                emp.setDeptID(rs.getInt("dept_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setDateOfBirth(rs.getDate("emp_birth_date"));
                emp.setEmpSex(rs.getBoolean("emp_sex"));
                emp.setEmpAddress(rs.getString("emp_address"));
                emp.setEmpEmail(rs.getString("emp_email"));
                emp.setEmpPhone(rs.getString("emp_phone"));
                emp.setEmpAvatar(rs.getString("emp_avartar"));
                emp.setEmpStatus(rs.getBoolean("emp_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return emp;
    }
    public static List<Employees> findAllEmpByAgeASC(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employees> emps = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_emp_by_age_asc()}");
            ResultSet rs = callSt.executeQuery();
            emps = new ArrayList<>();
            while (rs.next()) {
                Employees emp = new Employees();
                emp.setEmpID(rs.getString("emp_id"));
                emp.setDeptID(rs.getInt("dept_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setDateOfBirth(rs.getDate("emp_birth_date"));
                emp.setEmpSex(rs.getBoolean("emp_sex"));
                emp.setEmpAddress(rs.getString("emp_address"));
                emp.setEmpEmail(rs.getString("emp_email"));
                emp.setEmpPhone(rs.getString("emp_phone"));
                emp.setEmpAvatar(rs.getString("emp_avartar"));
                emp.setEmpStatus(rs.getBoolean("emp_status"));
                emps.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emps;
    }
}

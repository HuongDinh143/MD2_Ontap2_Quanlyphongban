package ra.business;

import ra.entity.Departments;
import ra.unti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBusiness {
    public static List<Departments> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Departments> departments = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_dept()}");
            callSt.executeQuery();
            ResultSet rs = callSt.getResultSet();
            departments = new ArrayList<>();
            while (rs.next()) {
                Departments department = new Departments();
                department.setDeptId(rs.getInt("dept_id"));
                department.setDeptName(rs.getString("dept_name"));
                department.setDeptDesc(rs.getString("dept_description"));
                department.setDeptStatus(rs.getBoolean("dept_status"));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return departments;
    }
    public static boolean save(Departments department) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_department(?,?,?)}");
            callSt.setString(1, department.getDeptName());
            callSt.setString(2, department.getDeptDesc());
            callSt.setBoolean(3, department.isDeptStatus());
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static Departments findDepartmentById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Departments department = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_dept_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                department = new Departments();
                department.setDeptId(rs.getInt("dept_id"));
                department.setDeptName(rs.getString("dept_name"));
                department.setDeptDesc(rs.getString("dept_description"));
                department.setDeptStatus(rs.getBoolean("dept_status"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }
    public static boolean updateDepartment(Departments department) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_department(?,?,?,?)}");
            callSt.setInt(1, department.getDeptId());
            callSt.setString(2, department.getDeptName());
            callSt.setString(3, department.getDeptDesc());
            callSt.setBoolean(4, department.isDeptStatus());
            callSt.executeUpdate();
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static boolean delete(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_dept(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static List<Departments> findDepartmentByName(String deptName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Departments> findDepartments = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("{call find_dept_by_name(?)}");
            callSt.setString(1, deptName);
            ResultSet rs = callSt.executeQuery();
            findDepartments = new ArrayList<>();
            while (rs.next()) {
                Departments department = new Departments();
                department.setDeptId(rs.getInt("dept_id"));
                department.setDeptName(rs.getString("dept_name"));
                department.setDeptDesc(rs.getString("dept_description"));
                department.setDeptStatus(rs.getBoolean("dept_status"));
                findDepartments.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return findDepartments;
    }
}

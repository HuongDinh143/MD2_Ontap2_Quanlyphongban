package ra.entity;

import ra.business.DepartmentBusiness;

import java.util.List;
import java.util.Scanner;

public class Departments {
    private int deptId;
    private String deptName;
    private String deptDesc;
    private boolean deptStatus = false;

    public Departments() {
    }

    public Departments(int deptId, String deptName, String deptDesc, boolean deptStatus) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptDesc = deptDesc;
        this.deptStatus = deptStatus;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public boolean isDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(boolean deptStatus) {
        this.deptStatus = deptStatus;
    }

    public void inputData(Scanner scanner) {
        this.deptName = inputDeptName(scanner);
        this.deptDesc = inputDeptDesc(scanner);
        this.deptStatus = true;

    }

    public static String inputDeptName(Scanner scanner) {
        System.out.println("Nhập vào tên phòng ban");
        String deptName = scanner.nextLine().trim();
        List<Departments> departmentsList = DepartmentBusiness.findAll();
        do {
            if (deptName.isEmpty()) {
                System.err.println("Tên phòng ban không được bỏ trống");
            } else if (departmentsList != null) {
                boolean isExist = false;
                for (Departments department : departmentsList) {
                    if (deptName.equals(department.getDeptName())) {
                        System.err.println("Tên phòng ban không được trùng vui lòng nhập phòng ban mới");
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return deptName;
                }
            } else {
                return deptName;
            }
        } while (true);
    }

    public static String inputDeptDesc(Scanner scanner) {
        System.out.println("Mời bạn nhập mô tả phòng ban");
        return scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Department{" +
                "Mã phòng ban:" + deptId +
                ", Tên: '" + deptName + '\'' +
                ", Mô tả: '" + deptDesc + '\'' +
                ", Trạng thái: " + deptStatus +
                '}';
    }
}

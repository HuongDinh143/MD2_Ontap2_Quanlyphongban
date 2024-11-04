package ra.entity;

import ra.business.DepartmentBusiness;
import ra.business.EmployeesBusiness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Employees {
    private String empID;
    private int deptID;
    private String empName;
    private Date dateOfBirth;
    private boolean empSex;
    private String empAddress;
    private String empEmail;
    private String empPhone;
    private String empAvatar;
    private Boolean empStatus = true;

    public Employees() {
    }

    public Employees(String empID, int deptID, String empName, Date dateOfBirth, boolean empSex, String empAddress, String empEmail, String empPhone, String empAvatar, Boolean empStatus) {
        this.empID = empID;
        this.deptID = deptID;
        this.empName = empName;
        this.dateOfBirth = dateOfBirth;
        this.empSex = empSex;
        this.empAddress = empAddress;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empAvatar = empAvatar;
        this.empStatus = empStatus;
    }

    public String getEmpID() {
        return this.empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public int getDeptID() {
        return this.deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isEmpSex() {
        return this.empSex;
    }

    public void setEmpSex(boolean empSex) {
        this.empSex = empSex;
    }

    public String getEmpAddress() {
        return this.empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpEmail() {
        return this.empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPhone() {
        return this.empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAvatar() {
        return this.empAvatar;
    }

    public void setEmpAvatar(String empAvatar) {
        this.empAvatar = empAvatar;
    }

    public Boolean getEmpStatus() {
        return this.empStatus;
    }

    public void setEmpStatus(Boolean empStatus) {
        this.empStatus = empStatus;
    }

    public void inputDataEmp(Scanner scanner) {
        this.empID = inputEmpID(scanner);
        this.deptID = inputDeptId(scanner);
        this.empName = inputEmpName(scanner);
        this.dateOfBirth = inputDateOfBirth(scanner);
        this.empSex = inputEmpSex(scanner);
        this.empAddress = inputEmpAddress(scanner);
        this.empEmail = inputEmpEmail(scanner);
        this.empPhone = inputPhoneNumber(scanner);
        this.empAvatar = inputAvatar(scanner);
        this.empStatus = true;

    }

    public static String inputEmpID(Scanner scanner) {
        String empId;
        Employees hasEmp;
        do {
            System.out.println("Nhập vào mã nhân viên, tối đa 5 ký tự");
            empId = scanner.nextLine();
            if (empId.isEmpty() || empId.length() > 5) {
                System.err.println("Mã nhân viên không được bỏ trống và không quá 5 ký tự, vui lòng nhập lại");
                continue;
            }
            hasEmp = EmployeesBusiness.findEmployeeByID(empId);
            if (hasEmp != null) {
                System.err.println("Mã nhân viên đã tồn tại, vui lòng nhập lại");
            } else {
                return empId;
            }

        } while (true);
    }

    public static int inputDeptId(Scanner scanner) {

        do {
            List<Departments> departmentsList = DepartmentBusiness.findAll();
            if (departmentsList.isEmpty()) {
                System.err.println("Khong có phòng ban nào");
                return -1;
            }
            System.out.println("Mời bạn lựa chọn mã phòng ban");
            departmentsList.forEach(department -> {
                System.out.printf("%d - %s\n",
                        department.getDeptId(), department.getDeptName());
            });
            System.out.println("Lựa chọn của bạn: ");
            try {
                int deptId = Integer.parseInt(scanner.nextLine());
                Departments hasDept = DepartmentBusiness.findDepartmentById(deptId);
                if (hasDept != null) {
                    return deptId;
                }else {
                    System.err.println("Mã phòng ban không tồn tại, vui lòng nhập lại");
                }
            }catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }

        } while (true);
    }
    public static String inputEmpName(Scanner scanner) {
        System.out.println("Nhập vào tên nhân viên: ");
        String empName = scanner.nextLine().trim();
        do {
            if (empName.isEmpty()) {
                System.err.println("Tên nhân viên không được bỏ trống, vui lòng nhập lại");
            }else if (empName.length() > 100) {
                System.err.println("Tên nhân viên không được dài quá 100 ký tự, vui lòng nhập lại");
            }else {
                return empName;
            }
        }while (true);
    }
    public static Date inputDateOfBirth(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date empDateOfBirth = null;
        do{
            System.out.println("Nhập vào ngày tháng năm sinh của sinh viên yyyy-MM-dd");
            String inputDate = scanner.nextLine().trim();
            try {
                empDateOfBirth = dateFormat.parse(inputDate);
                return empDateOfBirth;

            }catch (ParseException e) {
                System.err.println("Ngày sinh không hợp lệ, vui lòng nhập lại");
            }

        }while (true);
    }
    public static boolean inputEmpSex(Scanner scanner) {
        System.out.println("Mời bạn nhập giới tính nhân viên: ");
        do {
            try {
                return  Boolean.parseBoolean(scanner.nextLine());
            } catch (Exception ex) {
                System.err.println("Vui lòng nhập true hoặc false");
            }
        }while (true);
    }
    public static String inputEmpAddress(Scanner scanner) {
        System.out.println("Mời bạn nhập địa chỉ nhân viên");
        do {
            String inputAddress = scanner.nextLine().trim();
            if (inputAddress.isEmpty()) {
                System.err.println("Địa chỉ nhân viên không được bỏ trống");
            }else {
                return inputAddress;
            }
        }while (true);
    }
    public static String inputEmpEmail(Scanner scanner) {
        String emailRegex = "[\\w]+@[\\w]+\\.[\\w]{2,3}";
        System.out.println("Mời bạn nhập địa chỉ email: ");
        do {
            String inputEmail = scanner.nextLine();
            if (inputEmail.matches(emailRegex)) {
                return inputEmail;
            }else {
                System.err.println("email không đúng định dạng, vui lòng nhập lại");
            }
        }while (true);
    }
    public static String inputPhoneNumber(Scanner scanner) {
        String regexPhone = "^(0\\d{10})$";
        System.out.println("Mời bạn nhập số điện thoại");
        do {
            String inputPhone = scanner.nextLine();
            if (inputPhone.matches(regexPhone)) {
                return inputPhone;
            }else {
                System.err.println("Số điện thoại không đúng định dạng, vui lòng nhập lại");
            }
        }while (true);
    }
    public static String inputAvatar(Scanner scanner){
        System.out.println("Nhập là link ảnh");
        return scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Employees{" +
                "Mã NV='" + empID + '\'' +
                ", Mã Phòng ban=" + deptID +
                ", Tên='" + empName + '\'' +
                ", Ngày sinh=" + dateOfBirth +
                ", Giới tính=" + empSex +
                ", Địa chỉ='" + empAddress + '\'' +
                ", Email='" + empEmail + '\'' +
                ", Phone='" + empPhone + '\'' +
                ", Trạng thái=" + empStatus +
                '}';
    }
}


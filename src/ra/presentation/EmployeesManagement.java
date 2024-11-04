package ra.presentation;

import ra.business.DepartmentBusiness;
import ra.business.EmployeesBusiness;
import ra.entity.Departments;
import ra.entity.Employees;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeesManagement {
    public static void displayEmployeesManagement(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("*********************EMPLOYEE MANAGEMENT*****************");
            System.out.println("1. Danh sách nhân viên");
            System.out.println("2. Danh sách nhân viên theo giới tính (nam/nữ)");
            System.out.println("3. Danh sách nhân viên theo phòng ban (hiển thị theo tên phòng ban)");
            System.out.println("4. Thêm mới nhân viên");
            System.out.println("5. Cập nhật nhân viên");
            System.out.println("6. Xóa nhân viên");
            System.out.println("7. Thống kê nhân viên theo khoảng tuổi");
            System.out.println("8. Tìm kiếm nhân viên theo tên hoặc email hoặc số điện thoại");
            System.out.println("9. Sắp xếp nhân viên theo tuổi tăng dần");
            System.out.println("10. Thoát");
            System.out.println("Lựa chọn của bạn");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayEmployees();
                        break;
                    case 2:
                        displayEmployeesBySex(scanner);
                        break;
                    case 3:
                        displayEmployeesByDeptId(scanner);
                        break;
                    case 4:
                        createEmployee(scanner);
                        break;
                    case 5:
                        updateEmployee(scanner);
                        break;
                    case 6:
                        deleteEmp(scanner);
                        break;
                    case 7:
                        statisticEmpByAge(scanner);
                        break;
                    case 8:
                        findEmpByEmailOrByPhone(scanner);
                        break;
                    case 9:
                        sortEmpByAgeASC();
                        break;
                    case 10:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-10");
                }

            } catch (NumberFormatException e) {
                System.err.println("Vui lòng chọn nhập vào số nguyên");
            }
        } while (isExist);
    }

    public static void displayEmployees() {
        List<Employees> employeesList = EmployeesBusiness.findAllEmployees();
        employeesList.forEach(System.out::println);
    }

    public static void displayEmployeesBySex(Scanner scanner) {
        System.out.println("Nhập vào giới tính cần tìm: Nam: true, Nữ: false");
        try {
            boolean search_emp_sex = Boolean.parseBoolean(scanner.nextLine());
            List<Employees> employeesList = EmployeesBusiness.findEmployeeBySex(search_emp_sex);
            System.out.printf("Danh sách nhân viên %s\n", search_emp_sex ? "Nam" : "Nữ");
            employeesList.forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.err.println("Vui lòng nhập true hoặc false");
        }
    }

    public static void displayEmployeesByDeptId(Scanner scanner) {
        System.out.println("Mời bạn lựa chọn mã phòng ban");
        List<Departments> departmentsList = DepartmentBusiness.findAll();
        boolean flag = true;
        do {
            try {
                departmentsList.forEach(department -> {
                    System.out.printf("%d - %s", department.getDeptId(), department.getDeptName());
                });
                System.out.println("Lựa chọn của bạn");
                int deptId = Integer.parseInt(scanner.nextLine());
                List<Employees> employeesList = EmployeesBusiness.findEmployeeByDeptId(deptId);
                System.out.println("Danh sách nhân viên có trong" +
                        DepartmentBusiness.findDepartmentById(deptId).getDeptName());
                employeesList.forEach(System.out::println);
                flag = false;
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        } while (flag);
    }

    public static void createEmployee(Scanner scanner) {
        Employees employee = new Employees();
        employee.inputDataEmp(scanner);
        boolean result = EmployeesBusiness.saveEmployee(employee);
        if (result) {
            System.out.println("Thêm mới nhân viên thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }

    public static void updateEmployee(Scanner scanner) {
        System.out.println("Nhập vào mã nhân viên cần cập nhật");
        String updateEmpId = scanner.nextLine();
        Employees employee = EmployeesBusiness.findEmployeeByID(updateEmpId);

        if (employee != null) {
            boolean flag = true;
            do {
                System.out.println("1. Cập nhật mã phòng ban");
                System.out.println("2. Cập nhật tên");
                System.out.println("3. Cập nhật ngày sinh (YYYY-MM-DD)");
                System.out.println("4. Cập nhật giới tính (true: nam, false: nữ)");
                System.out.println("5. Cập nhật địa chỉ");
                System.out.println("6. Cập nhật email");
                System.out.println("7. Cập nhật số điện thoại");
                System.out.println("8. Cập nhật avatar");
                System.out.println("9. Cập nhật trạng thái (true: hoạt động, false: không hoạt động)");
                System.out.println("10. Thoát");
                System.out.println("Lựa chọn của bạn:");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập mã phòng ban mới:");
                            employee.setDeptID(Integer.parseInt(scanner.nextLine()));
                            break;
                        case 2:
                            System.out.println("Nhập tên mới:");
                            employee.setEmpName(scanner.nextLine());
                            break;
                        case 3:
                            System.out.println("Nhập ngày sinh mới (YYYY-MM-DD):");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            dateFormat.setLenient(false);
                            Date dateOfBirth = dateFormat.parse(scanner.nextLine());
                            employee.setDateOfBirth(dateOfBirth);
                            break;
                        case 4:
                            System.out.println("Nhập giới tính mới (true: nam, false: nữ):");
                            employee.setEmpSex(Boolean.parseBoolean(scanner.nextLine()));
                            break;
                        case 5:
                            System.out.println("Nhập địa chỉ mới:");
                            employee.setEmpAddress(scanner.nextLine());
                            break;
                        case 6:
                            System.out.println("Nhập email mới:");
                            employee.setEmpEmail(scanner.nextLine());
                            break;
                        case 7:
                            System.out.println("Nhập số điện thoại mới:");
                            employee.setEmpPhone(scanner.nextLine());
                            break;
                        case 8:
                            System.out.println("Nhập avatar mới:");
                            employee.setEmpAvatar(scanner.nextLine());
                            break;
                        case 9:
                            System.out.println("Nhập trạng thái mới (true: hoạt động, false: không hoạt động):");
                            employee.setEmpStatus(Boolean.parseBoolean(scanner.nextLine()));
                            break;
                        case 10:
                            flag = false;
                            break;
                        default:
                            System.err.println("Vui lòng nhập từ 1-10");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Vui lòng nhập đúng định dạng số.");
                } catch (ParseException e) {
                    System.err.println("Ngày sinh không hợp lệ, vui lòng nhập theo định dạng YYYY-MM-DD.");
                }
            } while (flag);

            boolean result = EmployeesBusiness.updateEmployee(employee);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Mã nhân viên không tồn tại.");
        }
    }
    public static void deleteEmp(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần xóa");
        String deleteEmpId = scanner.nextLine();
        Employees employee = EmployeesBusiness.findEmployeeByID(deleteEmpId);
        if (employee != null) {
            boolean result = EmployeesBusiness.deleteEmployee(employee);
            if (result) {
                System.out.println("Xóa nhân viên thành công");
            }else {
                System.err.println("Cập nhật nhân viên thất bại");
            }
        }else {
            System.err.println("Mã nhân viên không tồn tại");
        }
    }
    public static void statisticEmpByAge(Scanner scanner) {
        System.out.println("Nhập vào độ khoảng tuổi cần tìm");
        boolean flag = true;
        int countEmp = 0;
        do {
            try {
                System.out.println("Tuổi nhỏ");
                int age1 = Integer.parseInt(scanner.nextLine());
                System.out.println("Tuối lớn");
                int age2 = Integer.parseInt(scanner.nextLine());
                countEmp= EmployeesBusiness.countEmployeesByAge(age1, age2);
                System.out.printf("Số nhân viên có trong độ tuổi %d - %d là %d\n",age1,age2,countEmp);
                flag = false;
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên");
            }
        }while (flag);
    }
    public static void findEmpByEmailOrByPhone(Scanner scanner) {
        System.out.println("Nhập email hoặc số điện thoại cần tìm");
        String searchValue = scanner.nextLine();
        Employees searchResult = EmployeesBusiness.findEmpByEmailOrPhone(searchValue);
        if (searchResult != null) {
            System.out.println(searchResult.toString()); // In ra thông tin kết quả tìm thấy
        } else {
            System.out.println("Không tìm thấy nhân viên với email hoặc số điện thoại đã nhập.");
        }
    }
    public static void sortEmpByAgeASC(){
        List<Employees> employeesList = EmployeesBusiness.findAllEmpByAgeASC();
        if (employeesList != null) {
            employeesList.forEach(System.out::println);
        }
    }

}

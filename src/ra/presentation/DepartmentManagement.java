package ra.presentation;

import ra.business.DepartmentBusiness;
import ra.entity.Departments;

import java.util.List;
import java.util.Scanner;

public class DepartmentManagement {
    public static void displayDepartmentsManagement(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("*********************DEPARTMENT MANAGEMENT**************");
            System.out.println("1. Danh sách phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Cập nhật phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban theo tên");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayDepartments();
                        break;
                    case 2:
                        createDepartment(scanner);
                        break;
                    case 3:
                        updateDepartments(scanner);
                        break;
                    case 4:
                        deleteDepartment(scanner);
                        break;
                    case 5:
                        finDepartmentsByName(scanner);
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-6");
                }

            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        } while (isExit);
    }

    public static void displayDepartments() {
        List<Departments> departmentsList = DepartmentBusiness.findAll();
        departmentsList.forEach(System.out::println);
    }

    public static void createDepartment(Scanner scanner) {
        Departments department = new Departments();
        department.inputData(scanner);
        boolean result = DepartmentBusiness.save(department);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }

    public static void updateDepartments(Scanner scanner) {
        System.out.println("Nhập vào mã phòng ban cần cập nhật");
        try {
            int deptIdUpdate = Integer.parseInt(scanner.nextLine());
            Departments updateDept = DepartmentBusiness.findDepartmentById(deptIdUpdate);
            if (updateDept != null) {
                boolean isExit = true;
                do {
                    System.out.println("1. Cập nhật tên");
                    System.out.println("3. Cập nhật mô tả phòng ban");
                    System.out.println("4. Cập nhật trạng thái");
                    System.out.println("5. Thoát");
                    System.out.println("Lựa chọn của bạn: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            updateDept.setDeptName(scanner.nextLine());
                            break;
                        case 3:
                            updateDept.setDeptDesc(scanner.nextLine());
                            break;
                        case 4:
                            updateDept.setDeptStatus(Boolean.parseBoolean(scanner.nextLine()));
                            break;
                        case 5:
                            isExit = false;
                            break;
                        default:
                            System.err.println("Vui lòng nhập từ 1-5");
                    }
                } while (isExit);
                boolean result = DepartmentBusiness.updateDepartment(updateDept);
                if (result) {
                    System.out.println("Cập nhật thành công");
                } else {
                    System.err.println("Cập nhật thất bại");
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Vui lòng nhập đúng định dạng dữ liệu");
        }

    }

    public static void deleteDepartment(Scanner scanner) {
        System.out.println("Nhập vào mã phòng ban cần xóa");
        try {
            int deptIdDelete = Integer.parseInt(scanner.nextLine());
            Departments deleteDept = DepartmentBusiness.findDepartmentById(deptIdDelete);
            if (deleteDept != null) {
                boolean result = DepartmentBusiness.delete(deptIdDelete);
                if (result) {
                    System.out.println("Xóa phòng ban thành công");
                } else {
                    System.err.println("Xóa phòng ban thất bại");
                }
            } else {
                System.err.println("Mã phòng ban không tồn tại");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static void finDepartmentsByName(Scanner scanner) {
        System.out.println("Nhập vào tên phòng ban cần tìm kiếm");
        String findDepartmentName = scanner.nextLine();
        List<Departments> departmentsList = DepartmentBusiness.findDepartmentByName(findDepartmentName);
        System.out.printf("Tìm được %d kết quả theo từ khóa %s: \n", departmentsList.size(), findDepartmentName);
        departmentsList.forEach(System.out::println);
    }
}

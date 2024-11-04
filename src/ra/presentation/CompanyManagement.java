package ra.presentation;

import java.util.Scanner;

public class CompanyManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*********************COMPANY MANAGEMENT*****************");
            System.out.println("1. Quản lý phòng ban");
            System.out.println("2. Quản lý nhân viên");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        DepartmentManagement.displayDepartmentsManagement(scanner);
                        break;
                    case 2:
                        EmployeesManagement.displayEmployeesManagement(scanner);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("vui lòng nhập từ 1-3");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên");
            }

        } while (true);
    }


}

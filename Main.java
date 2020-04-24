import java.sql.*;
import java.util.Scanner;

import javax.sql.*;

public class Main {
    public static void main(String[] args) {
        String username = "postgres";
        String password = "1234";

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pstgres", username,
                    password);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        // Login
        System.out.println("ERP Software");
        System.out.println("Please enter Username");
        String userUsername = scanner.nextLine();
        System.out.println("Please enter Password");
        String userPassword = scanner.nextLine();
        scanner.close();
        System.out.println("Checking credentials");
        User user = Login.checkLogin(userUsername, userPassword);
        if (user == null) {
            System.out.println("username or password incorrect shuting down ERP program");
            System.exit(0);
        } else {
            printMenu(user);
        }

    }

    public static void printMenu(User user) {
        // Menu
        String Privilege = user.getPrivilege();
        int pagePicked = 0;

        /*
         * 1 Create new employee 2 Grant access 3 Run Report 4 View Customer 5 Create
         * Order 6 view sales report 7 View employee 8 Access or update model 9 Access
         * or update inventory 10 logout
         */
        System.out.println("Pick Page");
        Scanner scanner = new Scanner(System.in);
        switch (Privilege) {
            case "admin":
                System.out.println("1    Create a new employee");
                System.out.println("2    Grant access");
                System.out.println("3    Run Report");
                System.out.println("4    Logout");
                pagePicked = scanner.nextInt();
                if (pagePicked == 4) {
                    pagePicked = 10;
                }
                break;
            case "SalesPerson":
                System.out.println("1    View customer");
                System.out.println("2    Create an Order");
                System.out.println("3    View sales reports");
                System.out.println("4    Logout");
                pagePicked = scanner.nextInt();
                if (pagePicked == 4) {
                    pagePicked = 10;
                } else {
                    pagePicked += 3;
                }
                break;
            case "HR":
                System.out.println("1    View employee");// limited view (ID, Name)
                System.out.println("2    Sales report");
                System.out.println("3    Logout");
                pagePicked = scanner.nextInt();
                switch (pagePicked) {
                    case 1:
                        pagePicked = 7;
                        break;
                    case 2:
                        pagePicked = 6;
                        break;
                    case 3:
                        pagePicked = 10;
                        break;
                }
                break;

            case "engineer":
                System.out.println("1    View employee");// limited view(ID, Name)
                System.out.println("2    Access or update model");
                System.out.println("3    Access or update inventory");
                System.out.println("4    Logout");
                pagePicked = scanner.nextInt();
                switch (pagePicked) {
                    case 1:
                        pagePicked = 7;
                        break;
                    case 2:
                        pagePicked = 8;
                        break;
                    case 3:
                        pagePicked = 9;
                        break;
                    case 4:
                        pagePicked = 10;
                        break;
                }
                break;
        }
        scanner.close();
        // Take the page that was picked and open that menu
        switch (pagePicked) {
            case 1:
                createNewEmployee(user);
                break;
            case 2:
                grantAccess(user);
                break;
            case 3:
                runReport(user);
                break;
            case 4:
                viewCustomer(user);
                break;
            case 5:
                createOrder(user);
                break;
            case 6:
                viewSalesReport(user);
                break;
            case 7:
                viewEmployee(user);
                break;
            case 8:
                AccessOrUpdateModel(user);
                break;
            case 9:
                AccessOrUpdateInventory(user);
                break;
            case 10:
                logout(user);
                break;
        }
    }

    // Menu Item Functions
    // 1
    public static void createNewEmployee(User user) {

    }

    // 2
    public static void grantAccess(User user) {

    }

    // 3
    public static void runReport(User user) {

    }

    // 4
    public static void viewCustomer(User user) {

    }

    // 5
    public static void createOrder(User user) {

    }

    // 6
    public static void viewSalesReport(User user) {

    }

    // 7
    public static void viewEmployee(User user) {

    }

    // 8
    public static void AccessOrUpdateModel(User user) {

    }

    // 9
    public static void AccessOrUpdateInventory(User user) {

    }

    // 10
    public static void logout(User user) {
        Login.logout(user);
        System.out.println("ERP program Shutting down");
    }
}
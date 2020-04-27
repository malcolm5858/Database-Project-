import java.sql.*;
import java.util.Scanner;

import javax.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(Constants.url, Constants.user, Constants.password);
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
        // Employee(EmployeeID INTEGER, FirstName Varchar(30), Lastname Varchar(30), SSN
        // INTEGER, Salary FLOAT(4), PayType Varchar(30), jobType Varchar(30)
        System.out.println("Creating a new employee");
        System.out.println("Enter ID");
        int empid = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter First Name");
        String fName = scanner.nextLine();
        System.out.println("Enter Last Name");
        String lName = scanner.nextLine();
        System.out.println("Enter SSN");
        String ssn = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Salary");
        String salery = scanner.nextLine();
        System.out.println("Enter Pay type");
        String ptype = scanner.nextLine();
        System.out.println("Enter Job Type");
        String jtype = scanner.nextLine();
        PreparedStatement pStmt = conn.prepareStatement("insert into Employee values(?,?,?,?,?,?,?)");
        pStmt.SetInt(1, empid);
        pStmt.SetString(2, fName);
        pStmt.SetString(3, lName);
        pStmt.SetInt(4, ssn);
        pStmt.SetString(5, salery);
        pStmt.SetString(6, ptype);
        pStmt.SetString(7, jtype);
        try {
            pStmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Could not insert employee");
        }
    }

    // 2
    public static void grantAccess(User user) {

    }

    // 3
    public static void runReport(User user) {

    }

    // 4
    public static void viewCustomer(User user) {
        if (user.getPrivilege == "Admin")
            stmt.executeQuery("select * from Customer");
        else
            stmt.executeQuery("select FirstName , LastName from Customer");
    }

    // 5
    public static void createOrder(User user) {
        // Order CREATE TABLE Order(OrderNumber INTEGER, CustomerID INTEGER, EmployeeID
        // INTEGER, Model INTEGER, SaleValue INTEGER,
        System.out.println("Creating order");
        System.out.println("Enter OrderNumber");
        int ordnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter CustomerID");
        String custid = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter EmployeeID");
        String emplid = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Model");
        String mod = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter SaleValue");
        String saleval = scanner.nextInt();
        scanner.nextLine();
        PreparedStatement pStmt = conn.prepareStatement("insert into Order values(?,?,?,?.?)");
        pStmt.SetInt(1, ordnum);
        pStmt.SetInt(2, custid);
        pStmt.SetInt(3, emplid);
        pStmt.SetInt(4, mod);
        pStmt.SetInt(5, saleval);
        try {
            pStmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Could not insert order");
        }
    }

    // 6
    public static void viewSalesReport(User user) {

    }

    // 7
    public static void viewEmployee(User user) {
          //Employee(EmployeeID INTEGER, FirstName Varchar(30), Lastname Varchar(30), SSN INTEGER, Salary FLOAT(4), PayType Varchar(30), jobType Varchar(30)
        if (user.getPrivilege == "Admin"){
            ResultSet rset = stmt.executeQuery("select * from Employee");
            while(rset.next()){
                System.out.println(System.out.println(rset.getInt(EmployeeID) + "  " + rset.getString("FirstName") + "  " + rset.getString("LastName")+ "  " 
                + rset.getInt("SSN") + "  " + rset.getFloat("Salary")+ "  " +rset.getString("PayType")+ "  " +rset.getString("jobType"));
            }
        }
        else
            ResultSet rset = stmt.executeQuery("select FirstName , LastName from Employee");
            while(rset.next()){
                System.out.println(rset.getInt(EmployeeID) + "  " + rset.getString("FirstName") + "  " + rset.getString("LastName"));
    }

    // 8
    public static void AccessOrUpdateModel(User user) {
        System.out.printn("Enter 1 for access or 2 for update");
        int input = scanner.nextInt();
        scanner.nextLine();

        if(input == 1){
            //CREATE Table Model(ModelNumber INTEGER, SalePrice INTEGER, Primary KEY (ModelNumber));
            System.out.println("Accessing the Model data table");
            ResultSet rset = stmt.executeQuery("select * from Model");
            while(rset.next()){
                System.out.println(rset.getInt("ModleNumber")+ "  " +rset.getInt("SalePrice"));
            }
            
        }else if (input == 2){
            System.out.println("Enter Model Number of a model you want to update");
            int modid = scanner.nextInt();
            scanner.nextLine();
            PreparedStatement p = conn.prepareStatement("Update Model 
                                                        Set SalePrice = ?
                                                        where ModelNumber = ?");
            System.out.println("Enter the new sale price");
            int salep = scanner.nextInt();
            p.setInt(1,modid);
            p.setInt(2,salep);
            p.executeUpdate();
            scanner.nextLine();

        }else{
            System.out.println("Invalid input");
        }

    }

    // 9
    public static void AccessOrUpdateInventory(User user) {

    }

    // 10
    public static void logout(User user) {
        Login.logout(user);
        System.out.println("ERP program Shutting down");
    }

    scanner.close();
}
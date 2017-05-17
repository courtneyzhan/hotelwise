/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise;

import hotelwise.view.*;
import hotelwise.model.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author courtney
 */
public class Hotelwise {

    public static CustomerLogInForm loginForm;
    public static RoomSearchForm searchForm;
    public static AvailableRoomsListForm roomsListForm;
    public static ConfirmationPaymentForm confirmForm;
    public static CustomerRegisterForm cusRegisterForm;
    public static WelcomeForm welcomeForm;
    public static StaffLogInForm staffLoginForm;
    public static StaffHomeForm staffHomeForm;
    public static StaffBookingsForm staffBookingsForm;

    public static AppData appData;
    public static Connection conn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("args = " + OsUtils.getOsName());

        //Establishing connection to database and opening database.
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.err.println("Database not exists, quit.");
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        appData = AppData.loadData();
        if (appData == null) {
            System.out.println("No AppData is loaded, starting a new ");
            appData = new AppData();
            appData.seed();
            AppData.saveData(appData);
        }

        // Initializing the forms that will be used in the program. At the start only the Customer Log In Form is visible.
        loginForm = new CustomerLogInForm();
        loginForm.setVisible(false);

        searchForm = new RoomSearchForm();
        searchForm.setVisible(false);

        roomsListForm = new AvailableRoomsListForm();
        roomsListForm.setVisible(false);

        confirmForm = new ConfirmationPaymentForm(appData);
        confirmForm.setVisible(false);

        cusRegisterForm = new CustomerRegisterForm(appData);
        cusRegisterForm.setVisible(false);

        welcomeForm = new WelcomeForm();
        welcomeForm.setVisible(true);

        staffLoginForm = new StaffLogInForm();
        staffLoginForm.setVisible(false);
        
        staffHomeForm = new StaffHomeForm();
        staffHomeForm.setVisible(false);
        
        staffBookingsForm = new StaffBookingsForm(appData);
        staffBookingsForm.setVisible(false);
    }

    //Login method; in the customer log in form, it checks the inputted email and password against registered customers in the db
    public static void login(String username, String password) {
        //DEBUG
        System.out.println(username);
        System.out.println(password);
        //System.out.println(appData.findCustomerByEmail("a@b.com"));
        boolean userFound = false;
        Customer loggedInCustomer = new Customer();
        for (Customer customer : appData.getCustomerList()) {
            if (customer.getEmail().equals(username) && customer.getPassword().equals(password)) {
                userFound = true;
                loggedInCustomer = customer;
                break;
            }
        }

        if (userFound) {
            // authenticated, go to next form
            searchForm.setCustomer(loggedInCustomer);
            roomsListForm.setCustomer(loggedInCustomer);
            confirmForm.setCustomer(loggedInCustomer);
            searchForm.refreshUI();
            roomsListForm.refreshUI();
            confirmForm.refreshUI();

            loginForm.setVisible(false);
            searchForm.setVisible(true);
        } else {
            //login unsuccessful, will ask user to try again.
            //DEBUG
            System.out.println("Failed to login");
            loginForm.setVisible(true);
        }

    }

    // The database way using JDBC - not required for this assignment
    // Login method; in the customer log in form, it checks the inputted email and password against registered customers in the db
    public static void loginByDatabase(String username, String password) {
        //DEBUG
        System.out.println(username);
        System.out.println(password);

        boolean userFound = false;
        // we get login request, now shall check database (username and password)
        String sql = "SELECT * FROM Customers WHERE email = ? AND password = ?;";
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                userFound = true;
                //DEBUG
                System.out.println("Customer found with id: " + id);
            }
            rs.close();
            pstmt.close();
        } catch (Exception ex) {
            System.out.println("Error on querying database: " + ex + "\n" + ex.getStackTrace());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {

            }
        }

        if (userFound) {
            // authenticated, go to next form
            loginForm.setVisible(false);
            searchForm.setVisible(true);
        } else {
            //login unsuccessful, will ask user to try again.
            //DEBUG
            System.out.println("Failed to login");
            loginForm.setVisible(true);
        }
    }

    public static void roomSearch(Integer numOfGuests, String arrivalDate, String departureDate, String roomType) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        java.util.Date checkInDate, checkOutDate;
        try {
            checkInDate = (java.util.Date) format.parse(arrivalDate);
            checkOutDate = (java.util.Date) format.parse(departureDate);
        } catch (java.text.ParseException ex) {
            // System.out.println("Calling search form.showIncorrectError");
            searchForm.showIncorrectError("Invalid dates entered: '" + arrivalDate + "', '" + departureDate + "'");
            return;
        }

        Calendar currentDateCal = Calendar.getInstance(); //Get the current date
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Brisbane");
        currentDateCal.setTimeZone(timeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //format it as per your requirement
        String dateNow = formatter.format(currentDateCal.getTime());
        System.out.println("Now the date is :=>  " + currentDateCal.getTime());
        java.util.Date today = currentDateCal.getTime();

        Long time = today.getTime();
        Date todayMidnight = new Date(time - time % (24 * 60 * 60 * 1000));

        if (checkOutDate.before(new java.util.Date(checkInDate.getTime() + time % (24 * 60 * 60 * 1000)))) {
            searchForm.showIncorrectError("Check out will after Check in date");
            System.out.println("Wrong ionput");
        }

        if (checkInDate.before(todayMidnight)) {
            // TODO   having trouble update the error message on form and display
            searchForm.showIncorrectError("Arrival dates should be after today's date");
            System.out.println("TOO EARLY");
        }

        int daysbetween = (int) (checkOutDate.getTime() - checkInDate.getTime());
        int duration = (int) TimeUnit.DAYS.convert(daysbetween, TimeUnit.MILLISECONDS);
        //TODO PASSES IN ZERO
        Hotelwise.confirmForm.setDurationDays(duration);
        confirmForm.setNumOfGuests(numOfGuests);
        System.out.println("numOfGuests = " + numOfGuests);
        System.out.println("Number of guests: " + numOfGuests + ", Arrival Date: " + checkInDate + ", Departure Date: " + checkOutDate + ",  Room Type ID: " + roomType + ", Days between: " + TimeUnit.DAYS.convert(daysbetween, TimeUnit.MILLISECONDS));
        boolean roomFound = false;
        Room chosenRoom;
        Float unitPrice = appData.findRoomTypeByName(roomType).getPrice();
        Float total = unitPrice * duration;

        for (Room room : appData.getRoomList()) {
            if (room.getRoomType().equals(roomType) && (room.getBookStatus() != "Active" || room.getBookStatus() != "Confirmed")) {
                roomFound = true;
                chosenRoom = room;
                roomsListForm.showPanel(roomType);

                confirmForm.setPrice(total);
                confirmForm.setRoomType(appData.findRoomTypeByName(roomType));
                confirmForm.setCheckInDate(checkInDate);
                confirmForm.setCheckOutDate(checkOutDate);
                confirmForm.setTotalPrice(total);
                break;

            }
        }

        if (roomFound) {
            // authenticated, go to next form
            searchForm.setVisible(false);
            roomsListForm.setVisible(true);
            System.out.println("shown");
        } else {
            //search form displays error and asks user to redo form
            System.out.println("Display error on search form ...");
            searchForm.setVisible(true);

        }
    }

    public static void roomSearchByDatabase(Integer numOfGuests, String arrivalDate, String departureDate, Integer roomType) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        java.util.Date checkInDate, checkOutDate;
        try {
            checkInDate = (java.util.Date) format.parse(arrivalDate);
            checkOutDate = (java.util.Date) format.parse(departureDate);
        } catch (java.text.ParseException ex) {
            // System.out.println("Calling search form.showIncorrectError");
            searchForm.showIncorrectError("Invalid dates entered: '" + arrivalDate + "', '" + departureDate + "'");
            return;
        }

        int daysbetween = (int) (checkOutDate.getTime() - checkInDate.getTime());
        int duration = (int) TimeUnit.DAYS.convert(daysbetween, TimeUnit.MILLISECONDS);
        Hotelwise.confirmForm.setDurationDays(duration);
        //Hotelwise.confirmForm.setNumOfGuests(numOfGuests);

        System.out.println("Number of guests: " + numOfGuests + ", Arrival Date: " + checkInDate + ", Departure Date: " + checkOutDate + ",  Room Type ID: " + roomType + ", Days between: " + TimeUnit.DAYS.convert(daysbetween, TimeUnit.MILLISECONDS));
        boolean roomFound = false;
        // we get login request, now shall check database (username and password)
        String sql = "SELECT * FROM Rooms WHERE room_type_id = ?;";
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            roomType = roomType + 1;
            // set the value
            pstmt.setInt(1, roomType);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("room_type_id");
                roomFound = true;
                System.out.println("Room found: " + id);
                //roomsListForm.showPanel(id);
                //confirmForm.setPrice(id);

            }
            rs.close();
            pstmt.close();
        } catch (Exception ex) {
            System.out.println("Error on querying database: " + ex + "\n" + ex.getStackTrace());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {

            }
        }

        if (roomFound) {
            // authenticated, go to next form
            searchForm.setVisible(false);
            roomsListForm.setVisible(true);
            System.out.println("shown");
        } else {
            //search form displays error and asks user to redo form
            System.out.println("Display error on search form ...");
            searchForm.setVisible(true);

        }
    }

    public static void staffLogin(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        //System.out.println(appData.findCustomerByEmail("a@b.com"));
        boolean userFound = false;
        for (User user : appData.getUserList()) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                userFound = true;
                break;
            }
        }

        if (userFound) {
            // authenticated, go to next form
            staffLoginForm.setVisible(false);
            staffHomeForm.setVisible(true);
        } else {
            //login unsuccessful, will ask user to try again.
            //DEBUG
            System.out.println("Failed to login");
            staffLoginForm.setVisible(true);
        }
    }
}

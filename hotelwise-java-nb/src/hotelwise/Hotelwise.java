/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise;

import hotelwise.model.RoomType;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
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
    
    public static Connection conn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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

        // Initializing the forms that will be used in the program. At the start only the Customer Log In Form is visible.
        loginForm = new CustomerLogInForm();
        loginForm.setVisible(true);

        searchForm = new RoomSearchForm();
        searchForm.setVisible(false);

        roomsListForm = new AvailableRoomsListForm();
        roomsListForm.setVisible(false);
        
        confirmForm = new ConfirmationPaymentForm();
        confirmForm.setVisible(false);
    }
    //Login method; in the customer log in form, it checks the inputted email and password against registered customers in the db
    public static void login(String username, String password) {
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

    static void roomSearch(Integer numOfGuests, String arrivalDate, String departureDate, Integer roomType) throws Exception {
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
        Hotelwise.confirmForm.setNumOfGuests(numOfGuests);

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
                roomsListForm.hidePanel(id);
                confirmForm.setPrice(id);
                
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
}

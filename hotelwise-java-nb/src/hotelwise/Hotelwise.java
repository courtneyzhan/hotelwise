/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author courtney
 */
public class Hotelwise {

    public static CustomerLogInForm loginForm;
    public static RoomSearchForm searchForm;

    public static Connection conn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.err.println("Database not exists, quit.");
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        // TODO code application logic here
        searchForm = new RoomSearchForm();
            searchForm.setVisible(false);

        loginForm = new CustomerLogInForm();
        loginForm.setVisible(true);
    }

    public static void login(String username, String password) {
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
        } else {

            System.out.println("Display error on login form ...");
            loginForm.setVisible(true);
            loginForm.showLoginError();

        }
    }

    static void roomSearch(Integer numOfGuests, String arrivalDate, String departureDate, Integer roomType) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        java.util.Date checkInDate = (java.util.Date)format.parse(arrivalDate);
        java.util.Date checkOutDate = (java.util.Date)format.parse(departureDate);
        long daysbetween = checkOutDate.getTime() - checkInDate.getTime();
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
                int id = rs.getInt("id");
                roomFound = true;
                System.out.println("Room found: " + id);

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
        } else {

            System.out.println("Display error on search form ...");
            searchForm.setVisible(true);

        }
    }
}

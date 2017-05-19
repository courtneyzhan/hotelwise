/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seajewel;

import seajewel.model.AppData;
import seajewel.model.User;
import seajewel.model.Room;
import seajewel.model.Booking;
import seajewel.model.Customer;
import seajewel.view.StaffManageBookingsForm;
import seajewel.view.WelcomeForm;
import seajewel.view.RoomSearchForm;
import seajewel.view.StaffFinishForm;
import seajewel.view.CustomerFinishBookForm;
import seajewel.view.AvailableRoomsListForm;
import seajewel.view.StaffBookingsForm;
import seajewel.view.StaffHomeForm;
import seajewel.view.ConfirmationPaymentForm;
import seajewel.view.CustomerLogInForm;
import seajewel.view.CustomerRegisterForm;
import seajewel.view.StaffLogInForm;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public static CustomerFinishBookForm cusFinishBookForm;
    public static StaffManageBookingsForm staffManageBookingsForm;
    public static StaffFinishForm staffFinishForm;

    public static AppData appData;
    public static Connection conn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("args = " + OsUtils.getOsName());
        appData = AppData.loadData();
        if (appData == null) {
            System.out.println("No AppData is loaded, starting a new ");
            appData = new AppData();
            appData.seed();
            AppData.saveData(appData);
        }

        // Initializing the forms that will be used in the program. At the start only the Welcome Form is visible.
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

        staffBookingsForm = new StaffBookingsForm();
        staffBookingsForm.setVisible(false);

        cusFinishBookForm = new CustomerFinishBookForm();
        cusFinishBookForm.setVisible(false);
        
        staffManageBookingsForm = new StaffManageBookingsForm();
        staffManageBookingsForm.setVisible(false);
        
        staffFinishForm = new StaffFinishForm();
        staffFinishForm.setVisible(false);
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

    public static void staffLogin(String username, String password) {
        boolean userFound = false;
        for (User user : appData.getUserList()) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                userFound = true;
                break;
            }
        }

        if (userFound) {
            // authenticated, go to next form
            staffBookingsForm.refreshData();
            staffLoginForm.setVisible(false);
            staffHomeForm.setVisible(true);
        } else {
            //login unsuccessful, will ask user to try again.
            //DEBUG
            System.out.println("Failed to login");
            staffLoginForm.setVisible(true);
        }
    }

    /**
     * Assume a booking is recorded in system for stays. After customers left (checkout), its booking is marked completed 
     * 
     * @param roomType
     * @param checkInDate
     * @param checkOutDate
     * @return 
     */
    public static boolean isRoomTypeAvailable(String roomType, java.util.Date checkInDate, java.util.Date checkOutDate) {

        int bookedCountForThePeriod = 0;
        int availableRooms = 0;

        for (Room room : appData.getRoomList()) {
            if (room.getRoomType().equals(roomType)) {
                availableRooms += 1;
            }
        }

        for (int i = 0; i < appData.getBookingList().size(); i++) {

            Booking booking = appData.getBookingList().get(i);
            if (!booking.getRoomType().equals(roomType)) { // don't worry, is the type of room customer interested
                continue;
            }
            if (booking.isActive()) { // a booking is valid and not completed.
                // we are checking booking for target room types
                java.util.Date startB = booking.getCheckInDate();
                java.util.Date endB = booking.getCheckOutDate();

                if (endB.before(checkInDate) || startB.after(checkOutDate)) {
                    // no confilcts; 
                } else {
                    bookedCountForThePeriod += 1;
                }
            }
        }

        return (availableRooms > bookedCountForThePeriod);
    }

    /**
     * Room for (room type) is available,
     *
     * @param roomType
     * @param checkInDate
     * @param checkOutDate
     * @param numOfGuests
     */
    public static void displayRoomDetails(String roomType, java.util.Date checkInDate, java.util.Date checkOutDate, int numOfGuests) {
        long millisecsBetween =  checkOutDate.getTime() - checkInDate.getTime();
        int duration = (int) TimeUnit.DAYS.convert(millisecsBetween, TimeUnit.MILLISECONDS);
        Hotelwise.confirmForm.setDurationDays(duration);
        Float unitPrice = appData.findRoomTypeByName(roomType).getPrice();
        Float total = unitPrice * duration;

        roomsListForm.showPanel(roomType);
        confirmForm.setPrice(total);
        confirmForm.setRoomType(appData.findRoomTypeByName(roomType));
        confirmForm.setCheckInDate(checkInDate);
        confirmForm.setCheckOutDate(checkOutDate);
        confirmForm.setTotalPrice(total);
        confirmForm.setGuests(numOfGuests);
        confirmForm.setNumOfGuests(numOfGuests);

        searchForm.setVisible(false);
        roomsListForm.setVisible(true);

    }

    public static Booking displayBookingDetails(Integer bookingId) {
        Booking currentBooking = appData.getBookingList().get(bookingId-1);
        return currentBooking;
    }

}

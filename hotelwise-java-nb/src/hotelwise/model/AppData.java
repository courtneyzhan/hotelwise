/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise.model;

import static hotelwise.Hotelwise.appData;
import hotelwise.OsUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppData implements java.io.Serializable {

    private List<User> userList;
    private List<RoomType> roomTypeList;
    private List<Room> roomList;
    private List<Customer> customerList;
    private List<Booking> bookingList;

    public static final String DATA_FILE = OsUtils.isWindows() ? "C:/temp/appdata.ser " : "/tmp/appdata.ser";

    public void seed() {
        userList = new ArrayList<User>();
        roomTypeList = new ArrayList<RoomType>();
        roomList = new ArrayList<Room>();
        customerList = new ArrayList<Customer>();
        bookingList = new ArrayList<Booking>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        // seed Users here
        User newUser = new User();
        newUser.setId(1);
        newUser.setLogin("staff");
        newUser.setPassword("asdf");
        userList.add(newUser);

        //seed RoomType
        int roomTypeID = 1;
        RoomType singleRoom = new RoomType();      
        singleRoom.setId(roomTypeID++);
        singleRoom.setName("Single");
        singleRoom.setPrice((float) 25.0);
        singleRoom.setFacility("Wifi");
        singleRoom.setDescription("Single");
        roomTypeList.add(singleRoom);
        
        RoomType singleOceanRoom = new RoomType();
        singleOceanRoom.setId(1);
        singleOceanRoom.setName("Single with Ocean View");
        singleOceanRoom.setPrice((float) 50.0);
        singleOceanRoom.setFacility("Wifi");
        singleOceanRoom.setDescription("description");
        roomTypeList.add(singleOceanRoom);
        
        RoomType doubleRoom = new RoomType();
        singleRoom.setId(roomTypeID++);
        doubleRoom.setName("Double");
        doubleRoom.setPrice((float) 75.0);
        doubleRoom.setFacility("Wifi");
        doubleRoom.setDescription("description");
        roomTypeList.add(doubleRoom);
        
        RoomType familyRoom = new RoomType();
        singleRoom.setId(roomTypeID++);
        familyRoom.setName("Family");
        familyRoom.setPrice((float) 100.0);
        familyRoom.setFacility("Wifi");
        familyRoom.setDescription("description");
        roomTypeList.add(familyRoom);
        
        //seed Room
        Room room101 = new Room();
        room101.setRoomType("Single");
        room101.setRoomNumber("101");
        room101.setFloor("1");
        room101.setStatus("Available");
        roomList.add(room101);
        
        Room room102 = new Room();
        room102.setRoomType("Single");
        room102.setRoomNumber("102");
        room102.setFloor("2");
        room102.setStatus("Available");
        roomList.add(room101);

        Room room201 = new Room();
        room201.setRoomType("Double");
        room201.setRoomNumber("201");
        room201.setFloor("2");
        room201.setStatus("Available");
        roomList.add(room201);

        Room room202 = new Room();
        room202.setRoomType("Double");
        room202.setRoomNumber("202");
        room202.setFloor("2");
        room202.setStatus("Available");
        roomList.add(room202);
        
        Room room301 = new Room();
        room301.setRoomType("Double");
        room301.setRoomNumber("301");
        room301.setFloor("3");
        room301.setStatus("Available");
        roomList.add(room301);

        Room room302 = new Room();
        room302.setRoomType("Single with Ocean View");
        room302.setRoomNumber("302");
        room302.setFloor("3");
        room302.setStatus("Not Available");
        roomList.add(room302);
        
        Room room401 = new Room();
        room401.setRoomType("Family");
        room401.setRoomNumber("401");
        room401.setFloor("4");
        room401.setStatus("Available");
        roomList.add(room401);
        
        //seed Customer
        Customer newCustomer = new Customer();
        newCustomer.setId(1);
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Su");
        try {
            newCustomer.setDob((java.util.Date) format.parse("2017-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(AppData.class.getName()).log(Level.SEVERE, null, ex);
        }
        newCustomer.setAddress1("asdf");
        newCustomer.setAddress2("asdf");
        newCustomer.setEmail("jsu@email.com");
        newCustomer.setPassword("asdf");
        newCustomer.setCreditCardNumber("12345678");
        newCustomer.setCreditCardMonth(01);
        newCustomer.setCreditCardYear(01);
        customerList.add(newCustomer);
    }

    public static void saveData(AppData appData) {
        System.out.println("About to serialize data");
        try {
            FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(appData);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + DATA_FILE);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static AppData loadData() {
        System.out.println("Loading from serialized data store ...");
        AppData appData = null;
        try {
            FileInputStream fileIn = new FileInputStream(DATA_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            appData = (AppData) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        } catch (Exception e) {
            System.out.println("Failed to load data file " + DATA_FILE);
        }
        if (appData != null) {
            System.out.println("Customer ... " + appData.getCustomerList().size());
            System.out.println("Booking ... " + appData.getBookingList().size());
        }
        return appData;
    }

    public RoomType findRoomTypeByName(String name) {
        for (RoomType roomType : appData.getRoomTypeList()) {
            if (roomType.getName().equals(name)) {
                return roomType;
            }
        }
        return null;
    }

    public Room findRoomByRoomNumber(String roomNumber) {
        for (Room room : appData.getRoomList()) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    public Customer findCustomerByEmail(String email) {
        for (Customer customer : appData.getCustomerList()) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public void createBooking(Customer customer, RoomType roomType, java.util.Date checkInDate, java.util.Date checkOutDate, float totalPrice, int numOfGuests) {
        Booking booking = new Booking();
        booking.setRoomType(roomType.getName());
        booking.setCustomerId(customer.getId());
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setTotalPrice(totalPrice);
        booking.setNumOfGuests(numOfGuests);
        this.bookingList.add(booking);
        AppData.saveData(this);
        System.out.println("Booking CREATED");
    }

    public void registerNewCustomer(String firstName, String lastName, Date dob, String address1, String address2, String email, String password, String creditCardNumber, int creditCardMonth, int creditCardYear) {
        Customer cus = new Customer();
        cus.setId(customerList.size() + 1);
        cus.setFirstName(firstName);
        cus.setLastName(lastName);
        cus.setDob(dob);
        cus.setAddress1(address1);
        cus.setAddress2(address2);
        cus.setEmail(email);
        cus.setPassword(password);
        System.out.println("adding");
        customerList.add(cus);

        AppData.saveData(this);
        System.out.println("Customer CREATED");
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<RoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(List<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

}

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

/**
 *
 * @author zhimin
 */
public class AppData implements java.io.Serializable {


    private List<User> userList;
    private List<RoomType> roomTypeList;
    private List<Room> roomList;
    private List<Customer> customerList;
    public static final String DATA_FILE = OsUtils.isWindows() ? "C:/temp/appdata.ser " : "/tmp/appdata.ser";

    public void seed() {
        userList = new ArrayList<User>();
        roomTypeList = new ArrayList<RoomType>();
        roomList = new ArrayList<Room>();
        customerList = new ArrayList<Customer>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        // seed Users here
        User newUser = new User();
        newUser.setId(1);
        newUser.setLogin("staff");
        newUser.setPassword("testwise");
        userList.add(newUser);

        //seed RoomType
        RoomType singleRoom = new RoomType();
        singleRoom.setId(1);
        singleRoom.setName("Single");
        singleRoom.setPrice((float) 25.0);
        singleRoom.setFacility("Wifi");
        singleRoom.setDescription("description");
        roomTypeList.add(singleRoom);

        //seed Room
        Room newRoom = new Room();
        newRoom.setRoomType("Single");
        newRoom.setRoomNumber("100");
        newRoom.setFloor("1");
        roomList.add(newRoom);

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
            System.out.println("Employee class not found");
            c.printStackTrace();
        } catch (Exception e) {
            System.out.println("Failed to load data file " + DATA_FILE);
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
    
    
    public void registerNewCustomer(String firstName, String lastName, Date dob, String address1, String address2, String email, String password, String creditCardNumber, int creditCardMonth, int creditCardYear) {
        Customer cus = new Customer();
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

}

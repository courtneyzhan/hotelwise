/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhimin
 */
public class AppData implements java.io.Serializable {
    
    private List<User> userList; 
    private List<RoomType> roomTypeList;
    
    public static final String DATA_FILE = "/tmp/appdata.ser";
    
    
    public void seed() {
        userList = new ArrayList<User>();
        roomTypeList = new ArrayList<RoomType>();
        
        // seed data here
        User newUser = new User();
        newUser.setId(1);
        newUser.setLogin("staff");
        newUser.setPassword("testwise");
        userList.add(newUser);   
        
        // ... 
    }
  
    
    public static void saveData(AppData appData) { 
      System.out.println("About to serialize data");
      try {
         FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(appData);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      } catch(IOException i) {
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
      } catch(IOException i) {
         i.printStackTrace();
      } catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
      } catch (Exception e) {
         System.out.println("Failed to load data file " + DATA_FILE);       
      }
      return appData;
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

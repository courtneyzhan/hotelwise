/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise.model;

import static hotelwise.Hotelwise.conn;
import static hotelwise.Hotelwise.roomsListForm;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dominic
 */
public class RoomType implements java.io.Serializable  {
    private int id;
    private String name;
    private float price;
    private String facility;
    private String description;
    
    
    /**
     * 
     * @return all the room type objects
     */
    public static List<RoomType> all() {
        List allRoomTypes = new ArrayList<RoomType>();
        
        String sql = "SELECT * FROM room_types ;";
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // a row for a room type;
                RoomType newRoomType = new RoomType();
                newRoomType.id = rs.getInt("id");
                newRoomType.name = rs.getString("name");
                newRoomType.facility = rs.getString("facility");
                newRoomType.price = rs.getFloat("price");
                
                allRoomTypes.add(newRoomType);
                
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
        return allRoomTypes;
    }
    
    public static RoomType findByName(String name) {
        // call SQL 
        RoomType theObj = new RoomType();
     
         String sql = "SELECT * FROM room_types WHERE name = ?;";
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
            PreparedStatement pstmt = conn.prepareStatement(sql);     
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                theObj.id = rs.getInt("id");
                theObj.name = rs.getString("name");
                theObj.facility = rs.getString("facility");
                theObj.price = rs.getFloat("price");
                
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
       
        return theObj;
    }
    
        public static RoomType findById(int id) {
        // call SQL 
        RoomType theObj = new RoomType();
     
         String sql = "SELECT * FROM room_types WHERE id = ?;";
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:hotelwise_test.db");
            PreparedStatement pstmt = conn.prepareStatement(sql);     
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                theObj.id = rs.getInt("id");
                theObj.name = rs.getString("name");
                theObj.facility = rs.getString("facility");
                theObj.price = rs.getFloat("price");
                
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
       
        return theObj;
    }
    
    
    // RoomType.findByName("family").price
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the facility
     */
    public String getFacility() {
        return facility;
    }

    /**
     * @param facility the facility to set
     */
    public void setFacility(String facility) {
        this.facility = facility;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


}

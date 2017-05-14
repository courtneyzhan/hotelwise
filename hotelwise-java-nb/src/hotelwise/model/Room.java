/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise.model;

/**
 *
 * @author dominic
 */
public class Room implements java.io.Serializable  {
    private String roomNumber;
    private String floor;
    private int roomTypeId;

    /**
     * @return the roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the floor
     */
    public String getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * @return the roomTypeId
     */
    public int getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * @param roomTypeId the roomTypeId to set
     */
    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}

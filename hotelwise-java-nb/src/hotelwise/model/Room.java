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
public class Room implements java.io.Serializable {

    private String roomType;
    private String roomNumber;
    private String floor;
    private String status; //null = not booked, Confirmed = booking created, 
        //Active = booking happening now, Completed = booking already finished

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

}

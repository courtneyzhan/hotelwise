/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelwise.model;

import java.util.Date;

/**
 *
 * @author dominic
 */
public class Booking implements java.io.Serializable {

    //TODO change num of adults to numOfGuests and delete num of children
    private String roomType;
    private int customerId;
    private Date checkInDate;
    private Date checkOutDate;
    private float totalPrice;
    private int numOfGuests;
    private String status;
    
    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the checkInDate
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * @param checkInDate the checkInDate to set
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * @return the checkOutDate
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * @param checkOutDate the checkOutDate to set
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the numOfAdults
     */
    public int getNumOfGuests() {
        return numOfGuests;
    }

    /**
     * @param numOfAdults the numOfAdults to set
     */
    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        if ("Cancelled".equalsIgnoreCase(status) || "Completed".equalsIgnoreCase(status)) {
            return false;
        } else {
            return true;
        }
    }
    
    

}

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
public class Booking {

    private int roomId;
    private int roomTypeId;
    private int customerId;
    private Date checkInDate;
    private Date checkOutDate;
    private float totalPrice;
    private int numOfAdults;
    private int numOfChildren;
    private String paidStatus;

    /**
     * @return the roomId
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
    public int getNumOfAdults() {
        return numOfAdults;
    }

    /**
     * @param numOfAdults the numOfAdults to set
     */
    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    /**
     * @return the numOfChildren
     */
    public int getNumOfChildren() {
        return numOfChildren;
    }

    /**
     * @param numOfChildren the numOfChildren to set
     */
    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }

    /**
     * @return the paidStatus
     */
    public String getPaidStatus() {
        return paidStatus;
    }

    /**
     * @param paidStatus the paidStatus to set
     */
    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

}

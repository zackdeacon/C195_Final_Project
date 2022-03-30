package Model;

import java.util.Date;

public class appointment {

    private int appointmnetID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private int contactID;
    private int customerID;
    private int userID;

    public appointment(int appointmentID, String title, String description, String location, String type, Date start, Date end, int contactID, int customerID, int userID){
        this.appointmnetID = appointmentID;
        this.title=title;
        this.description=description;
        this.location=location;
        this.type=type;
        this.start=start;
        this.end=end;
        this.contactID=contactID;
        this.customerID=customerID;
        this.userID=userID;
    }

    public int getAppointmnetID() {
        return appointmnetID;
    }

    public void setAppointmnetID(int appointmnetID) {
        this.appointmnetID = appointmnetID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

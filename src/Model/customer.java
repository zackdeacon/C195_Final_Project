package Model;

public class customer {

    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private int division_ID;

    public customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID){
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.division_ID = division_ID;
    }

    public int getCustomer_ID(){return customer_ID;}
    public void setCustomer_ID(int id){
        this.customer_ID = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }
}

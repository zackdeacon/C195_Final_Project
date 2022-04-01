package Model;

public class customer {

    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private String division;
    private String country;
    private int countryID;
    private int divisionID;


    public customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, String division, String country, int countryID, int divisionID){
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.division = division;
        this.country = country;
        this.countryID = countryID;
        this.divisionID = divisionID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
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

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return (customer_Name);
    }
}

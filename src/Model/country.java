package Model;

public class country {

    private int countryID;
    private String countryName;

    public country(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString(){
        return (countryName);
    }
}
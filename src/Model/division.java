package Model;

public class division {

    private int divisionID;
    private int countryID;
    private String division;

    public division(int divisionID, int countryID, String division){
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.division = division;

    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString(){
        return (division);
    }

}

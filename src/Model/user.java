package Model;

public class user {
    private int User_ID;
    private String User_Name;
    private String Password;

    public user(int User_ID, String User_Name, String Password) {
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
    }

    public int getUserID() {
        return User_ID;
    }

    public void setUserID(int User_ID) {
        this.User_ID = User_ID;
    }

    public String getUserName() {
        return User_Name;
    }

    public void setUserName(String User_Name){
        this.User_Name = User_Name;
    }

    public String getPassword(){
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString(){
        return (User_Name);
    }
}

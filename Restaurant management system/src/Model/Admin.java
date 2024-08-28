package Model;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String email;

    public Admin() {

    }

    //getter and setter
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail()
    {
        return  email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public Admin(int adminId, String username, String password) {

        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public Admin(int adminId, String username, String password, String email){}

    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", username=" + username + ", password=" + password +"]";
    }


}





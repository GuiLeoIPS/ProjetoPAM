package pt.ips.pam.projetopam;

public class User {

    private int idUser;
    private String username;
    private String email;
    private String password;
    private String number; //Phone number
    private boolean admin;

    public User() {
    }

    public User(String username, String email, String password, String number, boolean admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.number = number;
        this.admin = admin;
    }

    public int getIdUser() {return idUser;}
    public void setIdUser(int idUser) {this.idUser = idUser;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}

    public boolean isAdmin() {return admin;}
    public void setAdmin(boolean admin) {this.admin = admin;}

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", admin=" + admin +
                '}';
    }
}

package domain;

public class User {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String pscode;

    public User() {
    }

    public User(String name, String password, String email, String phone, String pscode) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.pscode = pscode;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pscode='" + pscode + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPscode() {
        return pscode;
    }

    public void setPscode(String pscode) {
        this.pscode = pscode;
    }
}

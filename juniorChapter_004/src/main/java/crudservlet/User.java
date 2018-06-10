package crudservlet;

import java.time.LocalDate;
/**
 * class User.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.05.2018
 */
public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String login;
    private LocalDate createDate;
    private long addressId;

    public User() {
        this.createDate = LocalDate.now();
    }

    public long getAdressId() {
        return addressId;
    }

    public void setAdressId(long adressId) {
        this.addressId = adressId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

package control.repository.entity;

import java.util.List;
/**
 * class User.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.06.2018
 */
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Address address;
    private Role role;
    private List<MusicType> musicTypes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<MusicType> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }
}

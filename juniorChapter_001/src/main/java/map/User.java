package map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result +  (name != null ? name.hashCode() : 0);
        result = 37 * result + children;
        result = 37 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }


}

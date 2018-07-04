package control.repository.entity;

import java.util.Objects;
/**
 * class MusicType.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.06.2018
 */
public class MusicType {
    private long id;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MusicType musicType = (MusicType) o;
        return id == musicType.id && Objects.equals(type, musicType.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }
}

package jdbc.entity;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
public class Entry {

    private int field;

    @XmlElement(name = "field")
    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }


}

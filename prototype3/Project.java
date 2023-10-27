package prototype3;
import java.io.Serializable;


//this class is a PLACEHOLDER CLASS
public class Project implements Serializable {
    
	private static final long serialVersionUID = -5695355434022433547L;
	private String name;
    private int data;

    public Project(String name, int data) {
        this.name = name;
        this.data = data;
    }

	public String getName() {
        return name;
    }

    public int getData() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Project: " + name + ", Data: " + data;
    }
}

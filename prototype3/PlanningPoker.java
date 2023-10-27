package prototype3;
import java.io.Serializable;


//this class is a PLACEHOLDER CLASS
public class PlanningPoker implements Serializable {

	private static final long serialVersionUID = -6583172374653407817L;
	private int session_id;
    private String item_name;
    private int item_estimate;

    public PlanningPoker(int session_id, String item_name, int item_estimate) {
        this.session_id = session_id;
        this.item_name = item_name;
        this.item_estimate = item_estimate;
    }

    public int getSessionId() {
        return session_id;
    }

    public String getItemName() {
        return item_name;
    }

    public int getItemEstimate() {
        return item_estimate;
    }

    public void setSessionId(int session_id) {
        this.session_id = session_id;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public void setItemEstimate(int item_estimate) {
        this.item_estimate = item_estimate;
    }

    @Override
    public String toString() {
        return "PlanningPoker: Session ID - " + session_id + ", Item Name - " + item_name + ", Item Estimate - " + item_estimate;
    }
}

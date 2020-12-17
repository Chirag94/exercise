package pojos;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

	List<LineItem> lineItems = new ArrayList<LineItem>();

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
}

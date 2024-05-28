package application;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.Serializable;

public class OrderableTabPane extends TabPane implements Serializable {
	private static final long serialVersionUID = 416640771842913762L;

	public OrderableTabPane() {
		super();
	}

	public OrderableTabPane(Tab... arg0) {
		super(arg0);
	}
	
	public void setTabOrderNumbers() {
		//after re-organizing booklist to match current tablist order,
		//reorganize tablist so that the next change will be noticeable
        
	    for (int i = 0; i < this.getTabs().size(); i++) {
	    	OrderableTab currentTab = (OrderableTab) this.getTabs().get(i);
	    	currentTab.setTabOrder(i + 1);
	    }
	}
}

package application;

import javafx.scene.Node;
import javafx.scene.control.Tab;

import java.io.Serializable;

public class OrderableTab extends Tab implements Comparable<OrderableTab>, Serializable {
	private static final long serialVersionUID = -8889856041943795467L;
	private int tabOrder = 0;
	private String tabName = "Tab: " + tabOrder;
	
	public OrderableTab() {
		super();
	}

	public OrderableTab(int order, String name, String string, Node node) {
		super(string, node);
		tabOrder = order;
		tabName = name;
	}

	public OrderableTab(String arg0, Node arg1) {
		super(arg0, arg1);
	}

	public int getTabOrder() {
		return tabOrder;
	}

	public void setTabOrder(int tabOrder) {
		this.tabOrder = tabOrder;
		setTabName();
	}

	public String getTabName() {
		return "Tab: " + tabOrder;
	}

	public void setTabName() {
		tabName = "Tab: " + tabOrder;
	}

	@Override
	public int compareTo(OrderableTab o) {
		if (this.getTabOrder() > o.getTabOrder()) {
			return 1;
		} else if (this.getTabOrder() < o.getTabOrder()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return getTabName();
	}

}

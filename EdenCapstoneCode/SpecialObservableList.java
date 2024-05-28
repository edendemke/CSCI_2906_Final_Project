package application;

import java.io.Serializable;

import javafx.collections.ObservableListBase;

public class SpecialObservableList<Tab> extends ObservableListBase<Tab> implements Serializable {
	private static final long serialVersionUID = 8944079761695646325L;

	public SpecialObservableList() {
		super();
	}

	@Override
	public int size() {
		return this.size();
	}

	@Override
	public Tab get(int index) {
		return this.get(index);
	}

}

package hu.neuron.java.refactory.vo;

import java.util.List;

public class ResponseWrapper<T> {

	private List<T> data;

	public ResponseWrapper(List<T> data) {
		super();
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}

package com.monstarlab.junit;

public class BaseLetter {

	Integer size;

	public BaseLetter() {
	}

	public String draw() {
		getSize();
		return new String("");
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}

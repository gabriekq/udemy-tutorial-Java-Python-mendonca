package com.mendonca.course2;

public class Entity {

	private int val;
	public String type;
	
	private Entity() {
		this(0,"id");
	}
	
    public Entity(int val, String type) {
		this.val = val;
		this.type = type;
	}
	

	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}

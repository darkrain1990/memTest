package test;

import java.io.Serializable;

public class TMember implements Serializable{
	
	private static final long serialVersionUID = 9174194101246733501L;
	
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}


}

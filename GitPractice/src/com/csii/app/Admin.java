package com.csii.app;

public class Admin {
	private String sex;
	
	private String alias;
	
	private Integer id;
	
	private String name;
	
	private String img;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", img=" + img + "]";
	}

}

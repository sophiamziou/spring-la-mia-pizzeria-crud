package org.java.demo.pojo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Devi inserire il nome")
	private String name;

	private String description;

	private String img;
	
	@Min(value = 1, message = "Il prezzo deve essere uguale o superiore a 1")
	private double price;
	
	public Pizza() {}
	public Pizza(String name, String description, String img, double price) {
		setName(name);
		setDescription(description);
		setImg(img);	
		setPrice(price);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "(" + getId() + ") " + getName()
				+ "\n" + getDescription() 
				+ "\n" + getPrice()
				+ "\n" + getImg();
	}
	
}

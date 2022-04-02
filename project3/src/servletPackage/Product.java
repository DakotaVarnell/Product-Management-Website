//Name: Dakota Varnell
//Course: CS3381
//Project1


package servletPackage;

//import java.util.Iterator;

public class Product{

	private String id;
	private String name;
	private String brand;
	private Double cost;
	private String instrType;
	private Integer quantity;
	private String image;
	
	
	//Default Constructor
	public Product()
	{
		id = "not set";
		name = "not set";
		brand = "not set";
		cost = 0.0;
		instrType = "not set";
		quantity = 0;
		image = "not set";
		
	}
	
	//Parameterized Constructor
	public Product(String id, String name, String brand, Double cost, String instrType,Integer quantity, String image)
	{
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.cost = cost;
		this.instrType = instrType;
		this.quantity = quantity;
		this.image = image;
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getInstrType() {
		return instrType;
	}

	public void setInstrType(String instrType) {
		this.instrType = instrType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public boolean equals(String id)
	{
		//I understand I could've used the equals method for a lot of my other methods, 
		//but I didn't think to create one until now(2/14/22) bc everything is working fine
		//and I didn't want to break anything by going back and changing it all now right before
		//submission, but I plan on changing it before proj2, hope that illustrates why
		//I didn't properly utilize a method of my own creation
		Boolean toReturn = false;
		String CurrentId = this.getId();
		
		if(CurrentId.equals(id))
		{
			toReturn = true;
			return toReturn;
		}
		else {
			return toReturn;
		}
		
		
	}

	
	//To String Method
	public String toString()
	{
		return id+"     "+name+"     "+brand+"     "+cost+"     "+instrType+"     "+quantity+"     "+image;		
	}
	
	
	
}



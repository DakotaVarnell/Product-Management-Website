//Name: Dakota Varnell
//Project1
//Course: CS3381

package servletPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;




public class ProductCollection {

	private ArrayList <Product> products;
	private String filename = "";

	
	//Default constructor
	public ProductCollection()
	{
		products = new ArrayList<Product>();
		filename = null;
	}
	
	//Parameterized Constructor, allows us to read and write
	public ProductCollection(String filename)
	{
		this.filename = filename;
	}
	
	//Add new Instrument to our collection
	public void addInstrument(Product p)
	{
		products.add(p);
	}
	
	//Find new instrument using inputted id
	public Product findInstrument(String id)
	{
		//Iterator to traverse the collection
		Iterator <Product> iter = products.iterator();
		Product toReturn = new Product();
		
		//Show the product that was being searched for
		//System.out.println("The item you were looking for is directly below: ");
		
		while(iter.hasNext())
		{
			toReturn = iter.next();
			
				if(toReturn.getId().equals(id))
				{
					return toReturn;
				}
				else if(iter.hasNext() != true)
				{
						//System.out.println("Item not Found: ");
				}
			
		}

		
		return toReturn;
		
	}
	
	//Update quantity of certain product by using inputted id as if one had been bought
	public Product updateStatus(String id)
	{
		//Iterator to traverse the collection
		Iterator <Product> iter = products.iterator();
		Product toReturn = new Product();
		
		while(iter.hasNext())
		{
			toReturn = iter.next();
			
			if(toReturn.getId().equals(id))
			{
				//Decrement quantity by 1 of product
				toReturn.setQuantity(toReturn.getQuantity()-1);
			}
		}
		
		return toReturn;
	}
	
	//Return a collection of products by the type of instrument
	public ProductCollection retrieveCollection(String type)
	{
		//Iterator to traverse the collection
		Iterator<Product> iter = products.iterator();
		ProductCollection toReturn = new ProductCollection();
		Product temp = new Product();
		
		while(iter.hasNext())
		{
			temp = iter.next();
			
			//add all products of a certain type to a new collection and return it
			//Like a category
			if(temp.getInstrType().equals(type))
			{
				toReturn.addInstrument(temp);
			}
		}
		
		return toReturn;
	}
	
	//delete an entire product as if it wasn't sold anymore
	public void deleteProduct(String id)
	{
		//Iterator to traverse the collection
		Iterator <Product> iter = products.iterator();
		Product toRemove = new Product();
		
		while(iter.hasNext())
		{
			toRemove = iter.next();
			
				if(toRemove.getId().equals(id))
				{
					products.remove(toRemove);
					break;
				}
		}
	}

	
	//When the instrument or accessory is being bought, suggest a collection that they may also be interested in
	public ProductCollection suggestCollection(Product p)
	{
		//Iterator to traverse the collection
		Iterator<Product> iter = products.iterator();
		ProductCollection toSuggest = new ProductCollection();
		Product temp = new Product();
		
		//Title Prompt to tell them what they are looking at
		//System.out.println("Since you enjoyed : " + p.getBrand() + " " + p.getName());
		//System.out.println("You might also like:");
		
		//else if chain to determine the type of product that was purchased using input p
		if(p.getInstrType().equals("Guitar"))
		{
			//If guitar, suggest picks or strings
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if(temp.getInstrType().equals("Picks") || temp.getInstrType().equals("Strings"))
				{
					toSuggest.addInstrument(temp);
				}
			}
		}
		else if(p.getInstrType().equals("Amp"))
		{
			//If Amp, suggest cable or Pedal
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if(temp.getInstrType().equals("Cable") || temp.getInstrType().equals("Pedal"))
				{
					toSuggest.addInstrument(temp);
				}
			}
		}
		else if(p.getInstrType().equals("Drums"))
		{
			//If Drums, suggest drumsticks
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if(temp.getInstrType().equals("Drum Sticks"))
				{
					toSuggest.addInstrument(temp);
				}
			}
		}
		else if(p.getInstrType().equals("Woodwind"))
		{
			//If woodwind, suggest reeds
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if(temp.getInstrType().equals("Reeds"))
				{
					toSuggest.addInstrument(temp);
				}
			}
		}
		else
		{
			//otherwise suggest music stand or metronome
			while(iter.hasNext())
			{
				temp = iter.next();
				
				if(temp.getInstrType().equals("Metronome") || temp.getInstrType().equals("Stand"))
				{
					toSuggest.addInstrument(temp);
				}
			}
		}
		
		return toSuggest;
	}
	
	//Method that allows us to read from a text file and input each line as a new product
	public void toRead()
	{
		
			//Try catch that contains the buffered reader objects that allow reading of a file
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String line;
				ArrayList<Product> temp = new ArrayList<Product>();
				
				
				while((line = reader.readLine()) != null)
				{
					//System.out.println(line);
					//Tokenize each part of our line
					String[] tokens = line.split(",");
					//Set p equal to the information witin each line
					Product p = new Product(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]),tokens[4], Integer.parseInt(tokens[5]),tokens[6]);
					//add p to the temp collection
					temp.add(p);
					
				}
				
				//set the products collection equal to the temp
				products = temp;
				
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 


	}
		
	//Method that allows us to write to a text file
	public void toWrite()
	{
		//Try catch that contains our Buffered Writer objects
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			Iterator<Product> iter = products.iterator();
			
			while(iter.hasNext())
			{
				//line = next product in collection
				Product line = iter.next();
				//Write to our text file the information contained in  our collection
				writer.write(line.getId() + "," + line.getName() + "," + line.getBrand() + "," + line.getCost() + "," + line.getInstrType() + "," + line.getQuantity() + "," + line.getImage() + "\n");
			}
			writer.close();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//To string method that converts this entire collection into a string
	public String toString()
	{
		String toReturn = "";
		Iterator<Product> iter = products.iterator();
		
		while(iter.hasNext())
		{
			toReturn += iter.next().toString() + "\n";
			
		}
		
		return toReturn;
	}


	
	
	
	
	
	
	
}

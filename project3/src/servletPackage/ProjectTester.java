//Name: Dakota Varnell
//Course: CS3381
//Project1

package servletPackage;
//import java.util.ArrayList;
//import java.util.Iterator;


public class ProjectTester {

	public static void main(String args[])
	{
		
		//Add all of our tester products
//		Product test = new Product("22", "Stratocaster", "Fender", 222.0, "Guitar", 2, "File Path");
//		Product test2 = new Product("23", "Jazzmaster", "Fender", 300.0, "Guitar", 2, "File Path");
//		Product test3 = new Product("33", "Les Paul", "Gibson", 300.0, "Guitar", 2, "File Path");
//		Product test4 = new Product("38", "Blues Junior", "Fender", 400.0, "Amp", 2, "File Path");
//		Product test5 = new Product("36", "Katana", "Boss", 350.0, "Amp", 2, "File Path");
//		Product test6 = new Product("39", "Thin Picks", "Tortex", 7.0, "Pick", 2, "File Path");
//		Product test7 = new Product("42", "Thick Gauge", "Fender", 12.0, "Strings", 2, "File Path");
//		Product test8 = new Product("58", "Tube Screamer", "Ibanez", 12.0, "Pedal", 2, "File Path");
//		Product test9 = new Product("44", "Metronome", "Boss", 12.0, "Metronome", 2, "File Path");
//		Product test10 = new Product("23", "Studio", "Epiphone", 300.00, "Guitar", 2, "File path");
		//Init our inventory collection class with no parameters
		//ProductCollection myList = new ProductCollection();
		
		//Init with our file name
		ProductCollection myList = new ProductCollection("./project1package/inventoryTest.txt");
		
		//read from our txt file
		myList.toRead();
		
		
		//Systematic approach of testing below that tests each method of collection class etc
//
//		
//		//Check our getters
//		System.out.println(test.getId());
//		System.out.println(test.getName());
//		System.out.println(test.getBrand());
//		System.out.println(test.getCost());
//		System.out.println(test.getInstrType());
//		System.out.println(test.getQuantity());
//		System.out.println(test.getImage());
//		
//		//Check our Setters
//		test.setId("23");
//		test.setName("Stratocaster");
//		test.setBrand("Fender");
//		test.setCost(300.0);
//		test.setInstrType("Guitar");
//		test.setQuantity(2);
//		test.setImage("File Path");
//		
//		//add all the products to the product collection myList manually for init testing
//		myList.addInstrument(test);
//		myList.addInstrument(test2);
//		myList.addInstrument(test3);
//		myList.addInstrument(test4);
//		myList.addInstrument(test5);
//		myList.addInstrument(test6);
//		myList.addInstrument(test7);
//		myList.addInstrument(test8);
//		myList.addInstrument(test9);
//		myList.addInstrument(test10);
//		
//		
//		//Check and see if one product is equal to another
//		System.out.println(myList.equals("11"));
//
//		//Print out the string representation of our collection
//		System.out.println(myList.toString());
//		
//		//Print out an instrument found using its id
//		System.out.println(myList.findInstrument("71"));
//		
//		//Delete a product according to its id
//		myList.deleteProduct("23");
//		
//		//Finds a collection of items
//		System.out.println(myList.retrieveCollection("Guitar"));
//		
//		//Decrease the quantity of an item (Purchase this item)
//		myList.updateStatus("12");
//		
//		//Give the method a product, and then return a list of products
//		//that compliment that product
//		//works for every instrument that currently exists or may be added in the future
//		//functioning on a guitar store for now that sells amps, guitars, pedals, drums
//		//could just add woodwinds and reeds to inventory and it will suggest properly
//		System.out.println(myList.suggestCollection(test4));
//		
//		//Print out the collection again including all changes
//		System.out.println(myList.toString());
		
		//Print out the entire collection
		System.out.println(myList.toString());
		
		//Write to our text file at end of run
		myList.toWrite();
		
		
		
	}

}



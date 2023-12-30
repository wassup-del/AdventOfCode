// This class stores information about a single grocery item being ordered.
//Bharath Yelamali
public class GroceryItemOrder {
	private String name;
	private double cost;
	private int quantity;
	
	//creating a new GroceryItemOrder with given parameters from the user
	public GroceryItemOrder(String name, int quantity, double pricePerUnit) {
		this.name = name;
		this.quantity = quantity;
		this.cost = pricePerUnit;
	}
	
	//updates quantity of order
	public void setQuantity(int quantity2) {
		quantity = quantity2;
	}
	
	//returns cost of the items
	public double getCost() {
		return (cost * quantity);
	}
	
	//returns a string describing the order
	public String toString() {
		return quantity + " of " + name;
	}
		
}

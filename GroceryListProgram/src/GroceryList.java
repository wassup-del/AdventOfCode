// This class keeps track of a grocery list of items
//Bharath Yelamali
public class GroceryList {
	//Global SIZE of the array. requirements of 5. can be adjusted
	//array that stores each grocery item order
	private GroceryItemOrder[] items;
	private int numItems;
	private final int SIZE = 5;
	

	public GroceryList() {
		//creating a new grocterItemOrder within the array
		this.items = new GroceryItemOrder[SIZE];
		this.numItems = numItems;
	}

	public boolean add(GroceryItemOrder item) {
		//goes through each value in the array
		for (int i = 0; i < items.length; i++) {
			//checks if the value is null
			if (items[i] == null) {
				//insterts the new GroceryItemOrder
				items[i] = item;
				return true;
			}
		}
		return false;
	}

	public double getTotalCost() {
		//initial cost
		double total = 0.0;
		for (int i = 0; i < items.length; i++) {
			//check if not null. this is so that
			//we are not running the .getCost()
			//on something that doesn't exist. 
			//avoid nullPointer exception
			if (items[i] != null) {
				//add cost to total
				total += items[i].getCost();
			}
		}

		return total;
	}

	public String toString() {
		String line = "";
		for (int i = 0; i < items.length; i++) {
			//check if not null. this is so that
			//we are not running the .toString
			//on something that doesn't exist. 
			//avoid nullPointer exception
			if (items[i] != null) {
				line += items[i].toString() + "\n";
			}
		}
		
		return line;
	}
}

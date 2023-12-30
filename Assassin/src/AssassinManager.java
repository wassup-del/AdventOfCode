import java.util.ArrayList;
//Bharath Yelamali
//P2
//maintains and rearranges two linked Lists. changes the configuration
//of the people in the killRing until there is one person left.
//manages and stores who is assinged to kill who
public class AssassinManager {

	// variables that point to the front of each linkedList
	AssassinNode killRingFront = null;
	AssassinNode graveyardFront = null;

	// constructor
	public AssassinManager(ArrayList<String> names) {
		// creates the list kill ring(list of all names)
		if (names == null || names.size() == 0) {
			throw new IllegalArgumentException();
		} else {
			for (int i = names.size() - 1; i >= 0; i--) {
				killRingFront = new AssassinNode(names.get(i), killRingFront);
			}
		}
	}

	// walks through each value in the killRing and
	// lists who is stalking who
	public void printKillRing() {
		// front of the list
		AssassinNode current = killRingFront;
		while (current.next != null) {
			// extract name values
			String stalker = current.name;
			String stalked = current.next.name;
			System.out.println("  " + stalker + " is stalking " + stalked);
			current = current.next;// walk
		}
		// makes the last person stalk the first person
		String stalker = current.name;
		System.out.println("  " + stalker + " is stalking " + killRingFront.name);
		current = current.next;
	}

	// prints out the names of everyone in the graveyard and who killed them
	public void printGraveyard() {
		AssassinNode current = graveyardFront;

		if (current == null) {
			// do nothing
			// System.out.println();
		} else {
			while (current != null) {
				// walk in the loop
				String killed = current.name;
				String killer = current.killer;
				System.out.println("  " + killed + " was killed by " + killer);
				current = current.next;
			}
		}
	}

	// checks to see if the killRing contains the person passed
	public boolean killRingContains(String name) {
		// sets current to the first value of the killRing
		AssassinNode current = killRingFront;
		// walk until the end of the list to check each value
		while (current != null) {
			if (current.name.equals(name)) {
				return true;
			}
			// walk
			current = current.next;
		}
		return false;
	}

	// checks to see if the graveyard contains the person passed
	public boolean graveyardContains(String name) {
		// sets current to the front
		AssassinNode current = graveyardFront;
		while (current != null) {
			if (current.name.equals(name)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	// checks to see if the game is over
	public boolean isGameOver() {
		AssassinNode current = killRingFront;
		// null indicates that current is the last in the list
		if (current.next == null) {
			return true;
		} else {
			return false;
		}
	}

	// returns the winner of the game
	public String winner() {
		AssassinNode current = killRingFront;
		// checks to see if there is only one value in the list
		if (current.next == null) {
			return current.name;

		} else {
			return null;
		}
	}

	// helper method that rearranges the value of the killRing and graveyard
	// takes in the values of killed, killer, and value
	private void rearrange(AssassinNode killed, AssassinNode killer, boolean value) {
		// sets the name of the killer of killed to killer name
		killed.killer = killer.name;
		// true indicates that it's accounting for the front value
		// cuts out the killed person from the KillRing
		if (value == true) {
			killRingFront = killed.next;
		} else {
			killer.next = killed.next;
		}
		// points the killed person to the graveyard
		killed.next = graveyardFront;
		// graveyard points to whoever got killed
		graveyardFront = killed;
	}

	// takes in the name to kill by the user
	// and traverses through the code until the values
	// of killer and killed are correct. Then sends to helper
	// method rearrange to complete task
	public void kill(String name) {
		// checks if the game is over
		if (isGameOver() == true || killRingContains(name) == false) {
			throw new IllegalArgumentException();
		}
		// accounts for the front case
		if (killRingFront.name.equals(name)) {
			AssassinNode killer = killRingFront;
			AssassinNode killed = killRingFront;
			// walk until the end of the list
			while (killer.next != null) {
				killer = killer.next;
			}
			// we send values to rearrange to kill person
			// true indicates that it's the front case
			rearrange(killed, killer, true);

			// middle and end cases
		} else {
			AssassinNode killer = killRingFront;
			AssassinNode killed = killRingFront.next;
			// walk until killed and killer are correct
			boolean run = false;
			while (run == false) {
				if (killed.name.equals(name)) {
					// set the value to true to bring out of loop
					run = true;
				} else {
					// walk
					killed = killed.next;
					killer = killer.next;
				}
			}
			// we send values to rearrange to kill person
			// false indicates that it's not the front case
			rearrange(killed, killer, false);

		}
	}

}

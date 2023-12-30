import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

//Bharath Yelamali
//Period 2


//Tracks the evidence the user finds and updates the values given in the game
public class EvidenceTracker {
	
	//all ghosts
	Set<Ghost> allGhosts = new TreeSet<>();
	//all Evidence in the game
	Set<String> allEvidence = new TreeSet<>();
	//all the evidence that's been logged
	Set<String> loggedEvidence = new TreeSet<>();
	//map of each ghosts and what evidence they require to be solved
	Map<Ghost, Set<String>> ghostMap = new TreeMap();
	
	//Constructor
	public EvidenceTracker(Set<Ghost> ghosts) {
		//checks for null and size of zero
		//throw IllegalArgumentException
		if (ghosts == null || ghosts.size() == 0) {
			throw new IllegalArgumentException();
		}
		
		for (Ghost current : ghosts) {
			allGhosts.add(current);
			allEvidence.addAll(current.getEvidence());
			ghostMap.put(current, current.getEvidence());
		}
	}
	//returns the set of all the Ghosts in the game
	public Set<Ghost> getAllGhosts() {
		return allGhosts;
	}
	//returns a set of all EvidenceTypes in the game
	public Set<String> getAllEvidenceTypes() {
		return allEvidence;
	}
	//returns a set of all the logged evidence
	public Set<String> getCurrentEvidence() {
		return loggedEvidence;
	}
	//returns a set of the possible ghosts based on 
	//the logged evidence
	public Map<Ghost, Set<String>> getCandidateMap() {
		return ghostMap;
	}
	//resets all inputed value and resets game
	public void resetGame() {
		Map<Ghost, Set<String>> tempMap = new TreeMap();
		for (Ghost value: allGhosts) {
			tempMap.put(value, value.getEvidence());
		}
		ghostMap.putAll(tempMap);
			
		loggedEvidence.clear();
	}
	//Traverse the map. returns a set of all the ghosts
	//matching the given evidenceType
	public Set<Ghost> getMatchingGhosts(String evidenceType) {
		if (evidenceType == null || !(allEvidence.contains(evidenceType))) {
			throw new IllegalArgumentException();
		}
		Set<Ghost> set = new TreeSet<>();
		Iterator<Ghost> itr = allGhosts.iterator();
		while (itr.hasNext()) {
			Ghost ghost = itr.next();
			if (ghost.hasEvidence(evidenceType)) {
				set.add(ghost);
			}
		}
		return set;
	}
	//input the given evidence Type
	//into the set containing the logged Evidence
	//throws error for null evidence types or evidence that is not valid
	public void logEvidence(String evidenceType) {
		if (evidenceType == null || !(allEvidence.contains(evidenceType))) {
			throw new IllegalArgumentException();
		}
		loggedEvidence.add(evidenceType);
		Iterator<Ghost> itr = ghostMap.keySet().iterator();
		while (itr.hasNext()) {
			Ghost evidence = itr.next();
			if (evidence.hasEvidence(evidenceType)) {
				ghostMap.get(evidence).remove(evidenceType);
			} else {
				itr.remove();
			}
		}
	}
}

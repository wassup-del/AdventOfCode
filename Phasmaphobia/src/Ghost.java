// Project 4: Phasmophobia
// Teacher-provided code.  You should not modify this file!
//
// Last Updated: 12/13/2022

import java.util.*;

public class Ghost implements Comparable<Ghost>, Cloneable {
	private String name;
	private String description;
	private Set<String> evidence;
	
	// Constructs a Ghost from the given name, description, and evidence
	// If any of the given values are null, throws an IllegalArgumentException
	public Ghost(String name, String description, Set<String> evidence) {
		if (name == null || description == null || evidence == null) {
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		this.description = description;
		this.evidence = evidence;
	}
	
	// Returns the name of this Ghost
	public String getName() {
		return name;
	}
	
	// Returns the description of this Ghost
	public String getDescription() {
		return description;
	}
	
	// Returns the set of evidence used to identify this Ghost
	public Set<String> getEvidence() {
		return new TreeSet<>(evidence);
	}
	
	// Returns true if the given evidence type is used to identify this Ghost
	public boolean hasEvidence(String evidenceType) {
		return evidence.contains(evidenceType);
	}
	
	// Returns a string representation of this ghost's name.
	// For example: "Hantu"
	public String toString() {
		return name;
	}
	
	// Compares this ghost to the given ghost based on their names
	@Override
	public int compareTo(Ghost other) {
		return this.name.compareTo(other.name);
	}
	
	// Returns a cloned instance of this Ghost
	@Override
	public Object clone() {
		return new Ghost(this.name, this.description, new TreeSet<>(this.evidence));
	}
}

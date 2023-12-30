// Project 4: Phasmophobia
//
// Teacher-provided code. You should not modify this file!
// This program tests your EvidenceTracker object on any ghost file you want.
//
// When it prompts you for a file name, if you type a simple string such
// as "ghosts.txt" (without the quotes) it will just look on your hard disk
// in the same directory as your code or Eclipse project.
//
// Last Updated: 12/13/2022

import java.io.*;
import java.util.*;

public class GhostHunter {
	public static void main(String[] args) {
		EvidenceTracker tracker = null;

		Scanner console = new Scanner(System.in);
		String choice = "n";
		while (true) {
			if (choice.startsWith("n")) { // new file
				// prompt for ghost filename
				System.out.print("Which filename? ");
				String filename = console.nextLine().trim();
				if (filename.length() > 0) {
					try {
						Set<Ghost> ghosts = loadGhosts(filename);
						tracker = new EvidenceTracker(ghosts);
					} catch (FileNotFoundException fnfe) {
						System.out.println("File not found: " + filename + "\n");
						continue;
					}
				} else {
					System.out.println("No filename entered." + "\n");
					continue;
				}
			} else if (choice.startsWith("s")) { // see current evidence
				System.out.println(tracker.getCurrentEvidence());
			} else if (choice.startsWith("l")) { // log evidence
				System.out.print("Evidence to log? ");
				String evidence = console.nextLine().trim().toLowerCase();
				try {
					tracker.logEvidence(evidence);
					System.out.println("Current evidence: " + tracker.getCurrentEvidence());
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid evidence.");
				}
			} else if (choice.startsWith("r")) { // reset game
				tracker.resetGame();
				System.out.println("Game has been reset.");
			} else if (choice.startsWith("f")) { // find matching ghosts
				System.out.print("Evidence to get matches? ");
				String evidence = console.nextLine().trim().toLowerCase();
				try {
					Set<Ghost> ghostMatches = tracker.getMatchingGhosts(evidence);
					System.out.println(ghostMatches);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid evidence.");
				}
			} else if (choice.startsWith("v")) { // view candidate ghosts
				Map<Ghost, Set<String>> evidenceMap = tracker.getCandidateMap();
				printCandidateMap(evidenceMap);
			} else if (choice.startsWith("q")) { // quit
				break;
			}

			System.out.println();

			System.out.println("Choose an action:");
			System.out.println("  (s)ee current evidence");
			System.out.println("  (v)iew candidate ghosts");
			System.out.println("  (l)og evidence");
			System.out.println("  (f)ind matching ghosts");
			System.out.println("  (r)eset game");
			System.out.println("  (n)ew file");
			System.out.println("  (q)uit");
			System.out.print("What would you like to do? ");

			choice = console.nextLine().trim().toLowerCase();
		}

		System.out.println("Thanks for playing!!");
		console.close();
	}

	// Scans through the given filename and returns a set of Ghost objects
	// created from the contents of the file. The contents must be formatted
	// on new lines as follows:
	// Ghost Name (e.g. Hantu)
	// Ghost Description (e.g. Lower temperatures allow the Hantu to move faster.)
	// Ghost Evidence (e.g. Orbs, Fingerprints, Freezing)
	public static Set<Ghost> loadGhosts(String filename) throws FileNotFoundException {
		Scanner ghostFile = new Scanner(new File(filename));
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		while (ghostFile.hasNextLine()) {
			String name = ghostFile.nextLine();
			String description = ghostFile.nextLine();
			String evidence = ghostFile.nextLine();
			String[] evidenceList = evidence.split(",");
			
			Set<String> evidenceSet = new TreeSet<>();
			for (int i = 0; i < evidenceList.length; i++) {
				evidenceSet.add(evidenceList[i].trim());
			}

			Ghost ghost = new Ghost(name, description, evidenceSet);
			ghosts.add(ghost);
		}
		ghostFile.close();

		return ghosts;
	}

	// Prints the given candidate map by printing the ghost and its associated
	// missing evidence as follows:
	// Hantu
	// Missing: [Orbs, Freezing]
	public static void printCandidateMap(Map<Ghost, Set<String>> candidateMap) {
		for (Ghost ghost : candidateMap.keySet()) {
			System.out.println(ghost);

			Set<String> evidence = candidateMap.get(ghost);
			System.out.println("Missing: " + evidence);
			System.out.println();
		}
	}
}

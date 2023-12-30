// A simple JUnit testing class for the Phasmophobia assignment.
// Passing all of these tests does NOT guarantee you a 100% on your
// project. You must write additional tests to capture more complex
// behavior and test edge cases.
//
// Last Updated: 12/13/2022

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class EvidenceTrackerSimpleTest {
	
	/*** CONSTRUCTOR TESTS ***/
	@Test (expected = IllegalArgumentException.class)
	public void constructorTestNull() {
		new EvidenceTracker(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructorTestEmpty() {
		new EvidenceTracker(new TreeSet<Ghost>());
	}
	
	/*** GETTER TESTS ***/
	@Test
	public void getAllGhostsTest() {
		// create a set of ghosts
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "writing"))));
		ghosts.add(new Ghost("Carlos", "hey", new TreeSet<>(Arrays.asList("emf 5", "freezing"))));
		
		// make constructor with a copy of these ghosts (in case constructor wrecks it)
		Set<Ghost> ghostsCopy = new TreeSet<Ghost>(ghosts);
		EvidenceTracker tracker = new EvidenceTracker(ghostsCopy);
		
		// parameters: failure message, expected, actual
		assertEquals("Mismatch with allGhosts", ghosts, tracker.getAllGhosts());
	}
	
	@Test
	public void getAllEvidenceTypesTest() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "writing"))));
		ghosts.add(new Ghost("Carlos", "hey", new TreeSet<>(Arrays.asList("emf 5", "freezing"))));
		Set<Ghost> ghostsCopy = new TreeSet<Ghost>(ghosts);
		
		EvidenceTracker tracker = new EvidenceTracker(ghostsCopy);
		Set<String> evidenceTypes = new TreeSet<String>(Arrays.asList("orbs", "freezing", "writing", "emf 5"));
		
		assertEquals("Mismatch with allEvidenceTypes", evidenceTypes, tracker.getAllEvidenceTypes());
	}
	
	@Test
	public void getCurrentEvidenceEmptyTest() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		
		Set<String> evidence = new TreeSet<>();
		
		assertEquals("Incorrect current evidence", evidence, tracker.getCurrentEvidence());
	}
	
	@Test
	public void getCurrentEvidenceTestTwoItems() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.logEvidence("freezing");
		tracker.logEvidence("orbs");
		
		Set<String> evidence = new TreeSet<>(Arrays.asList("freezing", "orbs"));
		
		assertEquals("Incorrect current evidence", evidence, tracker.getCurrentEvidence());
	}
	
	@Test
	public void getCandidateMapAllTest() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		Ghost alice = new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing")));
		Ghost bob = new Ghost("Bob", "hi", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s.")));
		ghosts.add(alice);
		ghosts.add(bob);
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		
		Map<Ghost, Set<String>> candidateMap = new TreeMap<>();
		candidateMap.put(alice, new TreeSet<>(Arrays.asList("orbs", "freezing")));
		candidateMap.put(bob, new TreeSet<>(Arrays.asList("orbs", "d.o.t.s.")));
		
		assertEquals("Incorrect candidate map", candidateMap, tracker.getCandidateMap());
	}
	
	/*** GET MATCHING GHOSTS TESTS ***/
	@Test (expected = IllegalArgumentException.class)
	public void getMatchingGhostsTestNull() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.getMatchingGhosts(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getMatchingGhostsTestEmpty() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.getMatchingGhosts("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getMatchingGhostsTestInvalidEvidence() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.getMatchingGhosts("llamallamallama");
	}
	
	@Test
	public void getMatchingGhostsTest() {
		Ghost alice = new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing")));
		Ghost bob = new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "emf 5")));
		Ghost carlos = new Ghost("Carlos", "hello", new TreeSet<>(Arrays.asList("freezing", "orbs")));
		
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(alice);
		ghosts.add(bob);
		ghosts.add(carlos);
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		
		Set<Ghost> matches = new TreeSet<Ghost>(Arrays.asList(new Ghost[] {alice, carlos}));
		
		assertEquals("Incorrect current evidence", matches, tracker.getMatchingGhosts("freezing"));
	}
	
	/*** LOG EVIDENCE TESTS ***/
	@Test
	public void logEvidenceTestEvidence_OneValid() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.logEvidence("freezing");
		
		Set<String> evidence = new TreeSet<String>();
		evidence.add("freezing");
		
		assertEquals("Incorrect current evidence", evidence, tracker.getCurrentEvidence());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void logEvidenceTestEvidence_OneInvalid() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.logEvidence("Waffles");
	}
	
	/*** RESET GAME TESTS ***/
	@Test
	public void resetGameEvidenceTest() {
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing"))));
		ghosts.add(new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s."))));
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.logEvidence("freezing");
		tracker.resetGame();
		
		assertEquals("Incorrect current evidence", new TreeSet<String>(), tracker.getCurrentEvidence());
	}
	
	@Test
	public void resetGameCandidateTest() {
		Ghost alice = new Ghost("Alice", "hi", new TreeSet<>(Arrays.asList("orbs", "freezing")));
		Ghost bob = new Ghost("Bob", "hello", new TreeSet<>(Arrays.asList("orbs", "d.o.t.s.")));
		
		Set<Ghost> ghosts = new TreeSet<Ghost>();
		ghosts.add(alice);
		ghosts.add(bob);
		
		EvidenceTracker tracker = new EvidenceTracker(ghosts);
		tracker.logEvidence("freezing");
		tracker.resetGame();
		
		Map<Ghost, Set<String>> candidateMap = new TreeMap<>();
		candidateMap.put(alice, new TreeSet<>(Arrays.asList("orbs", "freezing")));
		candidateMap.put(bob, new TreeSet<>(Arrays.asList("orbs", "d.o.t.s.")));
		
		assertEquals("Incorrect candidate map", candidateMap, tracker.getCandidateMap());
	}
}
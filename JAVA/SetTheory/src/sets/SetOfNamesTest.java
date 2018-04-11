package sets;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SetOfNamesTest {
	
	SetOfNames nameSet;

	@Before
	public void setUp() throws Exception {
		nameSet = new SetOfNames();
	}

	@Test
	public void testAddString() {
		String name = "Tom";
		nameSet.add(name);
		String expected = "{Tom}";
		assertEquals(expected, nameSet.toString());
		
		nameSet.add(name);
		expected = "{Tom}";
		assertEquals(expected, nameSet.toString());
		
		name = "";
		nameSet.add(name);
		expected = "{Tom, }";
		assertEquals(expected, nameSet.toString());
	}

	@Test
	public void testAddStringArray() {
		String [] names1 = {"Tom", "Jack", "Bruce"};
		nameSet.add(names1);
		String expected = "{Tom, Jack, Bruce}";
		assertEquals(expected, nameSet.toString());
		
		String [] names2 = {"", "", ""};
		nameSet.add(names2);
		expected = "{Tom, Jack, Bruce, }";
		assertEquals(expected, nameSet.toString());
		
		String [] names3 = {"Tom", "Jack", "Amy"};
		nameSet.add(names3);
		expected = "{Tom, Jack, Bruce, , Amy}";
		assertEquals(expected, nameSet.toString());
	}

	@Test
	public void testDelete() {
		String [] names = {"Tom", "Jack", "Bruce"};
		nameSet.add(names);
		nameSet.delete("Jack");
		String expected = "{Tom, Bruce}";
		assertEquals(expected, nameSet.toString());
		
		nameSet.delete("");
		expected = "{Tom, Bruce}";
		assertEquals(expected, nameSet.toString());
		
		nameSet.delete("Amy");
		expected = "{Tom, Bruce}";
		assertEquals(expected, nameSet.toString());
	}

	@Test
	public void testIsElementOf() {
		String [] names = {"Tom", "Jack", "Bruce"};
		nameSet.add(names);
		assertTrue(nameSet.isElementOf("Jack"));
		
		nameSet.delete("Jack");
		assertFalse(nameSet.isElementOf("Jack"));
		
		nameSet.add("");
		assertTrue(nameSet.isElementOf(""));
	}

	@Test
	public void testIntersect() {
		SetOfNames otherNameSet = new SetOfNames();
		
		String [] names1 = {"Tom", "Jack", "Bruce"};
		String [] names2 = {"Tom", "Jack", "Amy"};
		nameSet.add(names1);
		otherNameSet.add(names2);
		
		String expected = "{Tom, Jack}";
		assertEquals(expected, nameSet.intersect(otherNameSet).toString());
		
		nameSet.delete("Jack");
		expected = "{Tom}";
		assertEquals(expected, nameSet.intersect(otherNameSet).toString());
		
		nameSet.delete("Tom");
		expected = "emptyset";		
		assertEquals(expected, nameSet.intersect(otherNameSet).toString());
	}

	@Test
	public void testUnion() {
		SetOfNames otherNameSet = new SetOfNames();
		
		String [] names1 = {"Tom", "Jack", "Bruce"};
		
		nameSet.add(names1);
		
		String expected = "{Tom, Jack, Bruce}";
		assertEquals(expected, nameSet.union(otherNameSet).toString());
		
		String [] names2 = {"Tom", "Jack", "Amy"};
		otherNameSet.add(names2);
		expected = "{Tom, Jack, Bruce, Amy}";
		assertEquals(expected, nameSet.union(otherNameSet).toString());
		
		String [] names3 = {"Nick", ""};
		otherNameSet.add(names3);
		expected = "{Tom, Jack, Bruce, Amy, Nick, }";
		assertEquals(expected, nameSet.union(otherNameSet).toString());
	}

	@Test
	public void testDifference() {
		SetOfNames otherNameSet = new SetOfNames();		
		String [] names1 = {"Tom", "Jack", "Bruce"};
		
		nameSet.add(names1);
		
		String expected = "{Tom, Jack, Bruce}";
		assertEquals(expected, nameSet.difference(otherNameSet).toString());
		
		String [] names2 = {"Tom", "Jack", "Amy"};
		otherNameSet.add(names2);
		expected = "{Bruce, Amy}";
		assertEquals(expected, nameSet.difference(otherNameSet).toString());
		
		String [] names3 = {"Nick", ""};
		otherNameSet.add(names3);
		expected = "{Bruce, Amy, Nick, }";
		assertEquals(expected, nameSet.difference(otherNameSet).toString());
	}

	@Test
	public void testIsSubset() {
		SetOfNames otherNameSet = new SetOfNames();		
		String [] names1 = {"Tom", "Jack", "Bruce"};
		
		nameSet.add(names1);
				
		assertTrue(nameSet.isSubset(otherNameSet));
		
		String [] names2 = {"Tom", "Jack"};
		otherNameSet.add(names2);
		assertTrue(nameSet.isSubset(otherNameSet));
		
		String [] names3 = {""};
		otherNameSet.add(names3);
		assertFalse(nameSet.isSubset(otherNameSet));
	}

	@Test
	public void testCardinality() {	
		
		assertEquals(0, nameSet.cardinality());
		
		String [] names = {"Tom", "Jack", "Bruce"};		
		nameSet.add(names);
				
		assertEquals(3, nameSet.cardinality());
		
		nameSet.add("");
		assertEquals(4, nameSet.cardinality());		
	}

	@Test
	public void testToString() {
		assertEquals("emptyset", nameSet.toString());
		
		String [] names = {"Tom", "Jack", "Bruce"};		
		nameSet.add(names);
		
		String expected = "{Tom, Jack, Bruce}";
		assertEquals(expected, nameSet.toString());
		
		nameSet.add("");
		expected = "{Tom, Jack, Bruce, }";
		assertEquals(expected, nameSet.toString());		
	}

}

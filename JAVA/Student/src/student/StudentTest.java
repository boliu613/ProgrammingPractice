package student;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {
	
	Student stu;

	@Before
	public void setUp() throws Exception {
		stu = new Student("Bo Liu");
	}

	@Test
	public void testGetName() {
		assertEquals("Bo Liu", stu.getName());
	}

	@Test
	public void testAddHWScore() {
		stu.addHWScore(80);
		assertEquals(80.0, stu.getTotalScore(), .0001);
		
		stu.addHWScore(100);
		assertEquals(180.0, stu.getTotalScore(), .0001);
	}

	@Test
	public void testGetTotalScore() {
		stu.addHWScore(80);
		assertEquals(80.0, stu.getTotalScore(), .0001);
		
		stu.addHWScore(100);
		assertEquals(180.0, stu.getTotalScore(), .0001);
	}

	@Test
	public void testGetAverageScore() {
		stu.addHWScore(80);
		assertEquals(80.0, stu.getAverageScore(), .0001);
		
		stu.addHWScore(100);
		assertEquals(90.0, stu.getAverageScore(), .0001);
		
		stu.addHWScore(0);
		assertEquals(60.0, stu.getAverageScore(), .0001);
	}

	@Test
	public void testToString() {
		String expected = "Name: Bo Liu\nTotal Score: 0.0\nAverage Score: 0.0";
		assertEquals(expected, stu.toString());
		
		stu.addHWScore(80);
		expected = "Name: Bo Liu\nTotal Score: 80.0\nAverage Score: 80.0";
		assertEquals(expected, stu.toString());
		
		stu.addHWScore(100);
		expected = "Name: Bo Liu\nTotal Score: 180.0\nAverage Score: 90.0";
		assertEquals(expected, stu.toString());
	}

}

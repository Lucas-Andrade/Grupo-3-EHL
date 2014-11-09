package tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class testatest {

	int i;
	
	@Test
	public void test() {
		i = 1;
	}

	@Test
	public void test2() {
		assertEquals(1, i);
	}
}

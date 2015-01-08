package test.java.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;

import org.junit.Assert;
import org.junit.Test;

public class OptionalTest {

	// CONSTRUCTORS

	@Test
	public void test() throws InvalidArgumentException {

		new Optional<User>(new User("pantunes", "pantunesPassword", "pantunes@gmail.com"),
				new InvalidArgumentException());

	}

	@Test
	public void test2() throws InvalidArgumentException {

		new Optional<User>(null, new InvalidArgumentException());

	}

	@Test
	public void test3() throws InvalidArgumentException {

		InvalidArgumentException Test = null;

		new Optional<User>(new User("pantunes", "pantunesPassword", "pantunes@gmail.com"), Test);

	}

	@Test
	public void test4() throws InvalidArgumentException {

		new Optional<User>(new User("pantunes", "pantunesPassword", "pantunes@gmail.com"), "TEST");

	}

	@Test
	public void test5() throws InvalidArgumentException {

		new Optional<User>(new User("pantunes", "pantunesPassword", "pantunes@gmail.com"),
				new InvalidArgumentException(), "TEST");

	}

	@Test
	public void test6() throws InvalidArgumentException {

		new Optional<User>(new User("pantunes", "pantunesPassword", "pantunes@gmail.com"), null,
				"TEST");

	}

	@Test
	public void test7() throws InvalidArgumentException {

		Optional<User> optional = new Optional<User>(new User("pantunes", "pantunesPassword",
				"pantunes@gmail.com"), new InvalidArgumentException(), "TEST");
		Assert.assertFalse(optional.isNull());
	}

	@Test
	public void test8() throws InvalidArgumentException {

		Optional<User> optional = new Optional<User>(null, new InvalidArgumentException(), "TEST");

		Assert.assertTrue(optional.isNull());
	}

	@Test
	public void test9() throws InvalidArgumentException {

		Map<String, String> MapTester = new HashMap<String, String>();

		MapTester.put("Tester", "OptionalTeser");

		Optional<Map<String, String>> optional = new Optional<Map<String, String>>(MapTester,
				new InvalidArgumentException(), "TEST");

		Assert.assertFalse(optional.isEmpty());
	}

	@Test
	public void test10() throws Exception {

		Map<String, String> MapTester = new HashMap<String, String>();

		Optional<Map<String, String>> optional = new Optional<Map<String, String>>(MapTester,
				new InvalidArgumentException(), "TEST");

		Assert.assertTrue(optional.isEmpty());

	}

	@Test
	public void test11() throws InvalidArgumentException {

		Collection<String> MapTester = new ArrayList<String>();

		MapTester.add("Tester");

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), "TEST");

		Assert.assertFalse(optional.isEmpty());
	}

	@Test
	public void test12() throws InvalidArgumentException {

		Collection<String> MapTester = new ArrayList<String>();

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), "TEST");

		Assert.assertTrue(optional.isEmpty());
	}

	@Test
	public void test13() throws InvalidArgumentException {

		Collection<String> MapTester = new ArrayList<String>();

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), "TEST");

		Assert.assertTrue(optional.hasSpecificStringRepresentationIfEmpty());
	}

	@Test
	public void test14() throws InvalidArgumentException {

		Collection<String> MapTester = new ArrayList<String>();

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), null);

		Assert.assertFalse(optional.hasSpecificStringRepresentationIfEmpty());
	}

	@Test
	public void test15() throws InvalidArgumentException {

		Collection<String> MapTester = new ArrayList<String>();
		MapTester.add("Tester");

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), "TEST");

		Assert.assertFalse(optional.hasSpecificStringRepresentationIfEmpty());
	}

	@Test
	public void test16() throws InvalidArgumentException {

		Collection<String> MapTester = new ArrayList<String>();
		MapTester.add("Tester");

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), null);

		Assert.assertFalse(optional.hasSpecificStringRepresentationIfEmpty());
	}

	@Test
	public void test17() throws Exception {

		String Test = "Tester";
		Collection<String> MapTester = new ArrayList<String>();
		MapTester.add(Test);

		Optional<Collection<String>> optional = new Optional<Collection<String>>(MapTester,
				new InvalidArgumentException(), null);
		Assert.assertEquals(Test, optional.get().iterator().next());
	}

	@Test (expected = InvalidArgumentException.class)
	public void test18() throws Exception {

		Optional<Collection<String>> optional = new Optional<Collection<String>>(null,
				new InvalidArgumentException(), null);
		Assert.assertNull(optional.get().iterator().next());

	}
}
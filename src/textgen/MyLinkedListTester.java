/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list2;
    MyLinkedList<Integer> list3;
	MyLinkedList<Integer> listSet;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

		list2 = new MyLinkedList<Integer>();
		list2.add(20);
		list2.add(30);
		list2.add(12);

        list3 = new MyLinkedList<Integer>();
        list3.add(2);
        list3.add(5);
        list3.add(7);

		listSet = new MyLinkedList<Integer>();
		listSet.add(1);
		listSet.add(2);
		listSet.add(3);
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		int a = 99;
		int lastBeforeAdd = list2.get(list2.size() - 1);
		list2.add(a);
		assertEquals("Add: check a is correct", (Integer) a, list2.get(list2.size() - 1));
		assertEquals("Add: check size is correct", 4, list2.size());
		assertEquals("Add: check a -1", (Integer) lastBeforeAdd, list2.get(list2.size() - 2));

		try {
			list2.get(4);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
        assertEquals("Size: long list", LONG_LIST_LENGTH, longerList.size());
    }

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		int aBefore = list3.get(1);
		int index = 1;
		int a = 9;
		list3.add(1, 9);
		assertEquals("AddAtIndex: Check a is correct", (Integer) a, list3.get(1));
		assertEquals("AddAtIndex: Check a+1 is correct", (Integer) aBefore, list3.get(2));
		assertEquals("AddAtIndex: Check size is correct", 4, list3.size());
    }
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		int value0 = 5;
		listSet.set(0, value0);
		int value1 = 10;
		listSet.set(1, value1);
		assertEquals("Set: Check value is correct:", (Integer) value0, listSet.get(0));
		assertEquals("Set: Check value is correct:", (Integer) value1, listSet.get(1));
	}
	
	
	// TODO: Optionally add more test methods.
	
}

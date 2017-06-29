package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.TermType;

/**
 * This class is used to test all of the behaviours of a
 * TermType object.
 */

public class TermTypeTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a TermType.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting TermType Test: constructor");

        TermType tt = new TermType(1, "Fall");
        assertNotNull(tt);
        assertEquals("ID was assigned incorrectly", 1, tt.getId());
        assertEquals("Season was assigned incorrectly", "Fall", tt.getSeason());

        System.out.println("Finished TermType Test: constructor");
    }

    /*
     * testEquals
     *
     * Tests that two TermTypes with the same ID are considered equal.
     */

    @Test
    public void testEquals() {
        System.out.println("\nStarting TermType Test: testEquals");

        TermType tt1 = new TermType(1, "Winter");
        TermType tt2 = new TermType(1, "Winter");

        assertNotNull(tt1);
        assertNotNull(tt2);
        assertTrue("TermType 1 is not equal to TermType2", tt1.equals(tt2));

        System.out.println("Finished TermType Test: testEquals");
    }

    /*
     * testComparatorWhereThisLessThanOther
     *
     * Tests that a TermType that is "less than" other causes a
     * negative return value for compareTo.
     */

    @Test
    public void testComparatorWhereThisLessThanOther() {
        System.out.println("\nStarting TermType Test: testComparatorWhereThisLessThanOther");

        TermType tt1 = new TermType(1, "Winter");
        TermType tt2 = new TermType(3, "Fall");
        
        assertNotNull(tt1);
        assertNotNull(tt2);
        assertTrue("TermType 1 is not less than TermType2", tt1.compareTo(tt2) < 0);

        System.out.println("Finished TermType Test: testComparatorWhereThisLessThanOther");
    }
    
    /*
     * testComparatorWhereThisEqualsOther
     *
     * Tests that a TermType that is "equal" to other causes a
     * 0 return value for compareTo.
     */

    @Test
    public void testComparatorWhereThisEqualsOther() {
        System.out.println("\nStarting TermType Test: testComparatorWhereThisEqualsOther");

        TermType tt1 = new TermType(1, "Winter");
        TermType tt2 = new TermType(1, "Winter");

        assertNotNull(tt1);
        assertNotNull(tt2);
        assertEquals("TermType 1 is not equal to TermType2", 0, tt1.compareTo(tt2));

        System.out.println("Finished TermType Test: testComparatorWhereThisEqualsOther");
    }
    
    
    /*
     * testComparatorWhereThisGreaterThanOther
     *
     * Tests that a TermType that is "greater than" other causes a
     * positive return value for compareTo.
     */

    @Test
    public void testComparatorWhereThisGreaterThanOther() {
        System.out.println("\nStarting TermType Test: testComparatorWhereThisGreaterThanOther");

        TermType tt1 = new TermType(3, "Fall");
        TermType tt2 = new TermType(1, "Winter");

        assertNotNull(tt1);
        assertNotNull(tt2);
        assertTrue("TermType 1 is not greater than TermType2", tt1.compareTo(tt2) > 0);

        System.out.println("Finished TermType Test: testComparatorWhereThisGreaterThanOther");
    }
}

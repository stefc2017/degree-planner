package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;

/**
 * This class is used to test all of the behaviours of a
 * CoursePlan object.
 */

public class CoursePlanTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CoursePlan.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CoursePlan Test: constructor");

        CoursePlan cp = new CoursePlan(1, new ScienceCourse(3, "Object Orientation", 3.0,
                null, 2150, "Detailed look at proper object oriented programming."), new Student(1, 1234567,
                "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", null), new TermType(2, "Winter"), 2018);
        assertNotNull(cp);
        assertEquals("Course ID was assigned incorrectly", 3, cp.getCourse().getId());
        assertEquals("Student ID was assigned incorrectly", 1, cp.getStudent().getId());
        assertEquals("Term Type ID was assigned incorrectly", 2, cp.getTermType().getId());
        assertEquals("Year was assigned incorrectly", 2018, cp.getYear());

        System.out.println("Finished CoursePlan Test: constructor");
    }
    
    /*
     * testComparatorWhereThisLessThanOther
     *
     * Tests that a CoursePlan that is "less than" other causes a
     * negative return value for compareTo.
     */

    @Test
    public void testComparatorWhereThisLessThanOther() {
        System.out.println("\nStarting CoursePlan Test: testComparatorWhereThisLessThanOther");

        CoursePlan cp1 = new CoursePlan(1, null, null, new TermType(1, "Winter"), 2018);
        CoursePlan cp2 = new CoursePlan(1, null, null, new TermType(1, "Winter"), 2019);
        CoursePlan cp3 = new CoursePlan(1, null, null, new TermType(2, "Summer"), 2018);

        assertNotNull(cp1);
        assertNotNull(cp2);
        assertNotNull(cp3);
        assertTrue("CoursePlan with year < other does not give a negative value", cp1.compareTo(cp2) < 0);
        assertTrue("CoursePlan with TermType < other does not give a negative value", cp1.compareTo(cp3) < 0);

        System.out.println("Finished CoursePlan Test: testComparatorWhereThisLessThanOther");
    }
    
    /*
     * testComparatorWhereThisEqualsOther
     *
     * Tests that a CoursePlan that is "equal" to other causes a
     * 0 return value for compareTo.
     */

    @Test
    public void testComparatorWhereThisEqualsOther() {
        System.out.println("\nStarting CoursePlan Test: testComparatorWhereThisEqualsOther");

        CoursePlan cp1 = new CoursePlan(1, null, null, new TermType(3, "Fall"), 2018);
        CoursePlan cp2 = new CoursePlan(1, null, null, new TermType(3, "Fall"), 2018);

        assertNotNull(cp1);
        assertNotNull(cp2);
        assertEquals("CoursePlan 1 is not equal to CoursePlan 2", 0, cp1.compareTo(cp2));

        System.out.println("Finished CoursePlan Test: testComparatorWhereThisEqualsOther");
    }
    
    
    /*
     * testComparatorWhereThisGreaterThanOther
     *
     * Tests that a CoursePlan that is "greater than" other causes a
     * positive return value for compareTo.
     */

    @Test
    public void testComparatorWhereThisGreaterThanOther() {
        System.out.println("\nStarting CoursePlan Test: testComparatorWhereThisGreaterThanOther");

        CoursePlan cp1 = new CoursePlan(1, null, null, new TermType(3, "Fall"), 2018);
        CoursePlan cp2 = new CoursePlan(1, null, null, new TermType(3, "Fall"), 2017);
        CoursePlan cp3 = new CoursePlan(1, null, null, new TermType(2, "Summer"), 2018);

        assertNotNull(cp1);
        assertNotNull(cp2);
        assertNotNull(cp3);
        assertTrue("CoursePlan with year > other does not give a positive value", cp1.compareTo(cp2) > 0);
        assertTrue("CoursePlan with TermType > other does not give a positive value", cp1.compareTo(cp3) > 0);

        System.out.println("Finished CoursePlan Test: testComparatorWhereThisGreaterThanOther");
    }
}

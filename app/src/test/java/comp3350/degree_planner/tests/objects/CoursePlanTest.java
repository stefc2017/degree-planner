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

        CoursePlan cp = new CoursePlan(1, new ScienceCourse(3, "Object Orientation", 3.0, 1,
                2150, "Detailed look at proper object oriented programming."), new Student(1, 1234567,
                "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(2, "Winter"), 2018);
        assertNotNull(cp);
        assertEquals("Course ID was assigned incorrectly", 3, cp.getCourse().getId());
        assertEquals("Student ID was assigned incorrectly", 1, cp.getStudent().getId());
        assertEquals("Term Type ID was assigned incorrectly", 2, cp.getTermType().getId());
        assertEquals("Year was assigned incorrectly", 2018, cp.getYear());

        System.out.println("Finished CoursePlan Test: constructor");
    }
}

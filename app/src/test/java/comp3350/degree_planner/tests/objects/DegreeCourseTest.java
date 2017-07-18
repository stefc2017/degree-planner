package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.DegreeCourse;
import comp3350.degree_planner.objects.DegreeCourseType;
import comp3350.degree_planner.objects.ScienceCourse;

/**
 * This class is used to test all of the behaviours of a
 * DegreeCourse object.
 */

public class DegreeCourseTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a DegreeCourse.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting DegreeCourse Test: constructor");

        DegreeCourse dc = new DegreeCourse(new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0),
                new ScienceCourse(1, "Introductory Computer Science I", 3.0, null, 1010, "Basic programming concepts."),
                new DegreeCourseType(1, "Required"));
        assertNotNull(dc);
        assertEquals("Degree ID was assigned incorrectly", 1, dc.getDegreeId());
        assertEquals("Course ID was assigned incorrectly", 1, dc.getCourseId());
        assertEquals("Degree Course Type ID was assigned incorrectly", 1, dc.getDegreeCourseTypeId());

        System.out.println("Finished DegreeCourse Test: constructor");
    }
}

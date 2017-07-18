package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.TermType;

/**
 * This class is used to test all of the behaviours of a
 * CourseOffering object.
 */

public class CourseOfferingTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CourseOffering.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CourseOffering Test: constructor");

        CourseOffering co = new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I", 3.0,
                null, 1010, "Basic programming concepts."), new TermType(1, "Fall"));
        assertNotNull(co);
        assertEquals("Course ID was assigned incorrectly", 1, co.getCourseId());
        assertEquals("Term Type ID was assigned incorrectly", 1, co.getTermTypeId());

        System.out.println("Finished CourseOffering Test: constructor");
    }
}

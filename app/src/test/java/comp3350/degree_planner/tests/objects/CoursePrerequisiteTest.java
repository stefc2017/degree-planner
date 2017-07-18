package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.degree_planner.objects.CoursePrerequisite;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;

/**
 * This class is used to test all of the behaviours of a
 * CoursePrerequisite object.
 */

public class CoursePrerequisiteTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a CoursePrerequisite.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting CoursePrerequisite Test: constructor");

        CoursePrerequisite cp = new CoursePrerequisite(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                null, 1020, "More basic programming concepts."),
                new ScienceCourse(1, "Introductory Computer Science I",
                3.0, null, 1010, "Basic programming concepts."));
        assertNotNull(cp);
        assertEquals("Course ID was assigned incorrectly", 2, cp.getCourseId());
        assertEquals("Prerequisite Course ID was assigned incorrectly", 1, cp.getPrereqCourseId());

        System.out.println("Finished CoursePrerequisite Test: constructor");
    }
}

package comp3350.degree_planner.tests.objects;

import static org.junit.Assert.*;
import comp3350.degree_planner.objects.Degree;

import org.junit.Test;

import comp3350.degree_planner.objects.Student;

/**
 * This class is used to test all of the behaviours of a
 * Student object.
 */

public class StudentTest {

    /*
     * testConstructor
     *
     * Tests that the arguments given are assigned to the correct instance
     * variables by creating a Student.
     */

    @Test
    public void testConstructor() {
        System.out.println("\nStarting Student Test: constructor");

        Student s = new Student(1, 1234567, "Test Student", "email@ex.com", "pa55word",
                new Degree(23, "Test Degree", 0.0, 0.0, 1.0));
        assertNotNull(s);
        assertEquals("ID was assigned incorrectly", 1, s.getId());
        assertEquals("Student Number was assigned incorrectly", 1234567, s.getStudentNumber());
        assertEquals("Name was assigned incorrectly", "Test Student", s.getName());
        assertEquals("Email was assigned incorrectly", "email@ex.com", s.getEmail());
        assertEquals("Password was assigned incorrectly", "pa55word", s.getPassword());
        assertEquals("Degree ID was assigned incorrectly", 23, s.getDegreeId());

        System.out.println("Finished Student Test: constructor");
    }
}

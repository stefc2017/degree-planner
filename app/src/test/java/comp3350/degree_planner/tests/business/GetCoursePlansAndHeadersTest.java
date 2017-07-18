package comp3350.degree_planner.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.CompletedCourses;
import comp3350.degree_planner.business.Season;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kaleigh McCormick on 2017-06-28.
 *
 * Unit tests for getting course plans for a student
 */

public class GetCoursePlansAndHeadersTest {
    private AccessCoursePlan acp;

    //Sets up test data with the entries we need for all tests below
    @Before
    public void setUp() throws Exception {

        /*
         * Most of the stuff here is copied from DataAccessStub.java, see there for comments etc.
         * Except changed some of the data stored
         * Just here so we can have a separate data source for testing
         * Overriding (copied) only the methods needed for the testing
        */
        final DataAccess testData = new DataAccessStub() {
            private List<Course> courses;
            private List<CoursePlan> coursePlans;
            private List<Department> departments;
            private List<ScienceCourse> scienceCourses;
            private List<Student> students;
            private List<TermType> termTypes;
            private List<UserDefinedCourse> userDefinedCourses;

            private String dbName;
            private String dbType = "stub";

            @Override
            public void open(String dbName) {
                ScienceCourse tempScienceCourse;
                UserDefinedCourse tempUserDefinedCourse;

                // Create Types

                termTypes = new ArrayList<TermType>();
                termTypes.add(new TermType(1, "Winter"));
                termTypes.add(new TermType(2, "Summer"));
                termTypes.add(new TermType(3, "Fall"));

                // Create Departments

                departments = new ArrayList<Department>();
                departments.add(new Department(1, "Computer Science", "COMP"));

                // Create Courses

                courses = new ArrayList<Course>();
                scienceCourses = new ArrayList<ScienceCourse>();
                userDefinedCourses = new ArrayList<UserDefinedCourse>();

                tempScienceCourse = new ScienceCourse(1, "Introductory Computer Science I", 3.0,
                        departments.get(0), 1010, "Basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        departments.get(0), 1020, "More basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(3, "Object Orientation", 3.0, departments.get(0),
                        2150, "Detailed look at proper object oriented programming.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(4, "Software Engineering I", 3.0, departments.get(0),
                        3350, "Good software development practices.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempUserDefinedCourse = new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220");
                courses.add(tempUserDefinedCourse);
                userDefinedCourses.add(tempUserDefinedCourse);

                tempUserDefinedCourse = new UserDefinedCourse(6, "Language and Culture", 3.0, "ANTH 2370");
                courses.add(tempUserDefinedCourse);
                userDefinedCourses.add(tempUserDefinedCourse);

                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", null));
                students.add(new Student(2, 9999999, "DK", "dk@myumanitoba.ca", "password1", null));

                // Create Course Plans
                // Note that these are in sorted order, since that is how they will be retrieved
                // from the persistence layer

                coursePlans = new ArrayList<CoursePlan>();
                coursePlans.add(new CoursePlan(1, courses.get(0), students.get(0), termTypes.get(2), 2017));
                coursePlans.add(new CoursePlan(2, courses.get(4), students.get(0), termTypes.get(2), 2017));
                coursePlans.add(new CoursePlan(3, courses.get(1), students.get(0), termTypes.get(0), 2018));
                coursePlans.add(new CoursePlan(4, courses.get(2), students.get(0), termTypes.get(1), 2018));

                System.out.println("Opened " +dbType +" database " +dbName);
            }

            public List<CoursePlan> getCoursePlansByStudentId (int studentId) {
                ArrayList<CoursePlan> studentCoursePlans = new ArrayList<CoursePlan>();
                CoursePlan currPlan;

                for (int i = 0; i < coursePlans.size(); i++) {
                    currPlan = coursePlans.get(i);
                    if (currPlan.getStudent().getId() == studentId) {
                        studentCoursePlans.add(currPlan);
                    }
                }

                return studentCoursePlans;
            }
        };

        acp = new AccessCoursePlan(testData);
        testData.open(Main.dbName);
    }

    @Test
    public void testInvalidStudentId() throws Exception {
        System.out.println("\nStarting GetCoursePlansAndHeaders Test: invalid student id");
        assertEquals("Completed courses list was not empty", 0, acp.getCoursePlansAndHeaders(-1).size());
        System.out.println("Finished GetCoursePlansAndHeaders Test: invalid student id");
    }

    @Test
    public void testEmptyCoursePlans() throws Exception {
        System.out.println("\nStarting GetCoursePlansAndHeaders Test: valid student id and empty course results");
        assertEquals("Completed courses list was not empty", 0, acp.getCoursePlansAndHeaders(2).size());
        System.out.println("Finished GetCoursePlansAndHeaders Test: valid student id and empty course results");
    }

    @Test
    public void testValidData() throws Exception {
        final int NB_LIST_SIZE_FOR_STUDENT_1 = 7;
        CoursePlan tempCP;  // Used for checking the ID's of CoursePlans

        System.out.println("\nStarted GetCoursePlansAndHeaders Test: valid student id and non-empty course results");

        List coursePlansAndHeaders = acp.getCoursePlansAndHeaders(1);
        for (int i = 0; i<coursePlansAndHeaders.size(); i++) {
            System.out.println (coursePlansAndHeaders.get(i).toString());
        }
        assertNotNull("Course Plans and Headers array is null", coursePlansAndHeaders);
        assertEquals("Result length is not the same as expected result length",
                NB_LIST_SIZE_FOR_STUDENT_1, coursePlansAndHeaders.size());

        assertTrue("No starting header", coursePlansAndHeaders.get(0) instanceof ArrayList);
        assertEquals("Header at index 0 is incorrect", Season.FALL.getValue(), ((ArrayList)coursePlansAndHeaders.get(0)).get(0));

        assertTrue("Value at index 1 is not a CoursePlan", coursePlansAndHeaders.get(1) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(1));
        assertEquals("CoursePlan at index 1 is incorrect", 1, tempCP.getId());

        assertTrue("Value at index 2 is not a CoursePlan", coursePlansAndHeaders.get(2) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(2));
        assertEquals("CoursePlan at index 2 is incorrect", 2, tempCP.getId());

        assertTrue("Value at index 3 is not a header", coursePlansAndHeaders.get(3) instanceof ArrayList);
        assertEquals("Header at index 3 is incorrect", Season.WINTER.getValue(), ((ArrayList)coursePlansAndHeaders.get(3)).get(0));

        assertTrue("Value at index 4 is not a CoursePlan", coursePlansAndHeaders.get(4) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(4));
        assertEquals("CoursePlan at index 4 is incorrect", 3, tempCP.getId());

        assertTrue("Value at index 5 is not a header", coursePlansAndHeaders.get(5) instanceof ArrayList);
        assertEquals("Header at index 5 is incorrect", Season.SUMMER.getValue(), ((ArrayList)coursePlansAndHeaders.get(5)).get(0));

        assertTrue("Value at index 6 is not a CoursePlan", coursePlansAndHeaders.get(6) instanceof CoursePlan);
        tempCP = (CoursePlan)(coursePlansAndHeaders.get(6));
        assertEquals("CoursePlan at index 6 is incorrect", 4, tempCP.getId());

        System.out.println("Finished GetCoursePlansAndHeaders Test: valid student id and non-empty course results");
    }
}

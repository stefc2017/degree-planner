package comp3350.degree_planner.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import comp3350.degree_planner.business.CompletedCourses;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.DegreeCourse;
import comp3350.degree_planner.objects.DegreeCourseType;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.GradeType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * Unit tests for getting completed courses for a student
 */

public class GetCompletedCoursesTest {
    private CompletedCourses cc;

    //Sets up test data with the entries we need for all tests below
    @Before
    public void setUp() {

        /*
         * Most of the stuff here is copied from DataAccessStub.java, see there for comments etc.
         * Except changed some of the data stored
         * Just here so we can have a separate data source for testing
         * Overriding (copied) only the methods needed for the testing
        */
        final DataAccess testData = new DataAccessStub() {
            private ArrayList<Course> courses;
            private ArrayList<CourseResult> courseResults;
            private ArrayList<DegreeCourseType> degreeCourseTypes;
            private ArrayList<Degree> degrees;
            private ArrayList<DegreeCourse> degreeCourses;
            private ArrayList<Department> departments;
            private ArrayList<GradeType> gradeTypes;
            private ArrayList<ScienceCourse> scienceCourses;
            private ArrayList<Student> students;
            private ArrayList<TermType> termTypes;
            private ArrayList<UserDefinedCourse> userDefinedCourses;

            @Override
            public void open() {
                ScienceCourse tempScienceCourse;
                UserDefinedCourse tempUserDefinedCourse;

                // Create Types

                degreeCourseTypes = new ArrayList<DegreeCourseType>();
                degreeCourseTypes.add(new DegreeCourseType(1, "Required"));
                degreeCourseTypes.add(new DegreeCourseType(2, "Elective for Major"));

                termTypes = new ArrayList<TermType>();
                termTypes.add(new TermType(1, "Fall"));
                termTypes.add(new TermType(2, "Winter"));
                termTypes.add(new TermType(3, "Summer"));

                gradeTypes = new ArrayList<GradeType>();
                gradeTypes.add(new GradeType(1, "A+", 4.5));
                gradeTypes.add(new GradeType(2, "A", 4.0));
                gradeTypes.add(new GradeType(3, "B+", 3.5));
                gradeTypes.add(new GradeType(4, "B", 3.0));
                gradeTypes.add(new GradeType(5, "C+", 2.5));
                gradeTypes.add(new GradeType(6, "C", 2.0));
                gradeTypes.add(new GradeType(7, "D", 1.0));
                gradeTypes.add(new GradeType(8, "F", 0.0));

                // Create Departments

                departments = new ArrayList<Department>();
                departments.add(new Department(1, "Computer Science", "COMP"));

                // Create Courses

                courses = new ArrayList<Course>();
                scienceCourses = new ArrayList<ScienceCourse>();
                userDefinedCourses = new ArrayList<UserDefinedCourse>();

                tempScienceCourse = new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
                        1010, "Basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempUserDefinedCourse = new UserDefinedCourse(3, "Cultural Anthropology", 3.0, "ANTH 1220");
                courses.add(tempUserDefinedCourse);
                userDefinedCourses.add(tempUserDefinedCourse);

                // Create Degrees

                degrees = new ArrayList<Degree>();
                Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
                degrees.add(degree);

                // Map courses to degrees

                degreeCourses = new ArrayList<DegreeCourse>();

                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new DegreeCourseType(1, "Required")));

                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));
                students.add(new Student(2, 9999999, "DK", "dk@myumanitoba.ca", "password1", 1));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();

                //Student 1 science course passed
                courseResults.add(new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                        3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));

                //Student 1 science course failed
                courseResults.add(new CourseResult(2, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                        1, 1020, "More basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(8, "F", 0.0)));

                //Student 1 user-defined course failed
                courseResults.add(new CourseResult(3, new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(8, "F", 0.0)));

                //Student 1 user-defined course passed
                courseResults.add(new CourseResult(4, new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming."), new Student(1, 1234567, "Jim Bob",
                        "jimbob@myumanitoba.ca", "helloworld1", 1),new GradeType(3, "B+", 3.5)));
            }

            @Override
            public ArrayList<CourseResult> getCourseResultsByStudentId(int studentId) {
                ArrayList<CourseResult> crByStudentId = new ArrayList<CourseResult>();
                CourseResult currCR;

                for (int i = 0; i < courseResults.size(); i++) {
                    currCR = courseResults.get(i);
                    if (currCR.getStudent().getId() == studentId) {
                        crByStudentId.add(currCR);
                    }
                }

                return crByStudentId;
            }

            @Override
            public int getFailingGradeId() {
                int failingGradeId = -1;

                for (int i = 0; i < gradeTypes.size(); i++) {
                    if (gradeTypes.get(i).getName().equals("F")) {
                        failingGradeId = gradeTypes.get(i).getId();
                        break;
                    }
                }
                return failingGradeId;
            }

            @Override
            public Course getCourseById(int courseId) {
                Course result = null;

                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId() == courseId) {
                        result = courses.get(i);
                        break;
                    }
                }

                return result;
            }

            @Override
            public Department getDepartmentById(int departmentId) {
                Department result = null;

                for (int i = 0; i < departments.size(); i++) {
                    if (departments.get(i).getId() == departmentId) {
                        result = departments.get(i);
                        break;
                    }
                }

                return result;
            }
        };

        cc = new CompletedCourses(testData);
        testData.open();
    }

    @Test
    public void testInvalidStudentId() {
        System.out.println("\nStarting Get Completed Courses Test: invalid student id");

        String[][] result = cc.getCompletedCourses(-1);
        assertNull("Completed courses array was not null", result);

        System.out.println("Finished Get Completed Courses Test: invalid student id");
    }

    @Test
    public void testEmptyCourseResults() {
        System.out.println("\nStarting Get Completed Courses Test: valid student id and empty course results");

        String[][] result = cc.getCompletedCourses(2);
        assertNull("Completed courses array was not null", result);

        System.out.println("Finished Get Completed Courses Test: valid student id and empty course results");
    }

    @Test
    public void testValidData() {
        System.out.println("\nStarted Get Completed Courses Test: valid student id and non-empty course results");
        final int NB_COMPLETED_COURSES_FOR_STUDENT_1 = 4;

        String[][] expectedResult = new String[NB_COMPLETED_COURSES_FOR_STUDENT_1][2];
        expectedResult[0][0] = "COMP 1010: Introductory Computer Science I";
        expectedResult[0][1] = "Passed";
        expectedResult[1][0] = "COMP 1020: Introductory Computer Science II";
        expectedResult[1][1] = "Failed";
        expectedResult[2][0] = "ANTH 1220: Cultural Anthropology";
        expectedResult[2][1] = "Failed";
        expectedResult[3][0] = "ANTH 1220: Cultural Anthropology";
        expectedResult[3][1] = "Passed";

        String[][] result = cc.getCompletedCourses(1);

        assertNotNull("Completed courses array is null", result);
        assertEquals("Result length is not the same as expected result length", expectedResult.length, result.length);

        for (int i = 0; i < NB_COMPLETED_COURSES_FOR_STUDENT_1; i++) {
            assertTrue("Expected result and result are not equal at index " + i, Arrays.equals(expectedResult[i], result[i]));
        }

        System.out.println("Finished Get Completed Courses Test: valid student id and non-empty course results");
    }
}

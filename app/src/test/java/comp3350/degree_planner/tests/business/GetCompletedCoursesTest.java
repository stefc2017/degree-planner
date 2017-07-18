package comp3350.degree_planner.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comp3350.degree_planner.application.Main;
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
    public void setUp() throws Exception {

        /*
         * Most of the stuff here is copied from DataAccessStub.java, see there for comments etc.
         * Except changed some of the data stored
         * Just here so we can have a separate data source for testing
         * Overriding (copied) only the methods needed for the testing
        */
        final DataAccess testData = new DataAccessStub() {
            private List<Course> courses;
            private List<CourseResult> courseResults;
            private List<DegreeCourseType> degreeCourseTypes;
            private List<Degree> degrees;
            private List<DegreeCourse> degreeCourses;
            private List<Department> departments;
            private List<GradeType> gradeTypes;
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

                // Create Degrees

                degrees = new ArrayList<Degree>();
                Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
                degrees.add(degree);

                // Map courses to degrees

                degreeCourses = new ArrayList<DegreeCourse>();
                degreeCourses.add(new DegreeCourse(degree, scienceCourses.get(0), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, scienceCourses.get(1), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, scienceCourses.get(2), new DegreeCourseType(1, "Required")));
                degreeCourses.add(new DegreeCourse(degree, scienceCourses.get(3), new DegreeCourseType(1, "Required")));

                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", degree));
                students.add(new Student(2, 9999999, "DK", "dk@myumanitoba.ca", "password1", degree));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();
                courseResults.add(new CourseResult(1, scienceCourses.get(0), students.get(0), new GradeType(1, "A+", 4.5)));
                courseResults.add(new CourseResult(2, scienceCourses.get(1), students.get(0), new GradeType(2, "A", 4.0)));
                System.out.println("Opened " +dbType +" database " +dbName);
            }

            public List<CourseResult> getCourseResultsByStudentId (int studentId) {
                List<CourseResult> crByStudentId = new ArrayList<CourseResult>();
                Iterator<CourseResult> crIterator = courseResults.iterator();
                CourseResult currCR;

                while (crIterator.hasNext()) {
                    currCR = crIterator.next();

                    if (currCR.getStudentId() == studentId) {
                        crByStudentId.add (currCR);
                    }
                }

                return crByStudentId;
            }
        };

        cc = new CompletedCourses(testData);
        testData.open(Main.dbName);
    }

    @Test
    public void testInvalidStudentId() throws Exception {
        System.out.println("\nStarting Get Completed Courses Test: invalid student id");
        assertEquals("Completed courses list was not empty", cc.getCompletedCourses(-1).size(), 0);
        System.out.println("Finished Get Completed Courses Test: invalid student id");
    }

    @Test
    public void testEmptyCourseResults() throws Exception {
        System.out.println("\nStarting Get Completed Courses Test: valid student id and empty course results");
        assertEquals("Completed courses list was not empty", cc.getCompletedCourses(2).size(), 0);
        System.out.println("Finished Get Completed Courses Test: valid student id and empty course results");
    }

    @Test
    public void testValidData() throws Exception {
        final int NB_COMPLETED_COURSES_FOR_STUDENT_1 = 2;

        System.out.println("\nStarted Get Completed Courses Test: valid student id and non-empty course results");

        List<CourseResult> courseResults = cc.getCompletedCourses(1);

        assertNotNull("Completed courses array is null", courseResults);
        assertEquals("Result length is not the same as expected result length", NB_COMPLETED_COURSES_FOR_STUDENT_1, courseResults.size());

        for (int i = 0; i < NB_COMPLETED_COURSES_FOR_STUDENT_1; i++) {
            assertEquals("Expected result and result are not equal at index " + i, courseResults.get(i).getCourseId(), i+1);
        }

        System.out.println("Finished Get Completed Courses Test: valid student id and non-empty course results");
    }
}

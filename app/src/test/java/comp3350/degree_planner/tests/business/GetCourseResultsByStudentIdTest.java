package comp3350.degree_planner.tests.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.business.AccessCourseResult;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CoursePrerequisite;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.CourseType;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.DegreeCourse;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.GradeType;
import comp3350.degree_planner.objects.Rating;
import comp3350.degree_planner.objects.RatingType;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessStub;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * Unit tests to get completed courses for a student
 */

public class GetCourseResultsByStudentIdTest extends TestCase {
    private ArrayList<CourseResult> courseResults;
    private AccessCourseResult acr;

//    @Before
//    public void setUp() {
//        Services.createDataAccess(); //?
//        acr = new AccessCourseResult();
//    }

    @Test
    public void testInvalidStudentId() {
        System.out.println("\nStarting Get Course Results By Student ID Test: invalid student id");

        courseResults = acr.getCourseResultsByStudentId(-1);
        assertTrue("Course results list wasn't empty", courseResults.isEmpty());

        System.out.println("Starting Get Course Results By Student ID Test: invalid student id");
    }

    @Test
    public void testEmptyCourseResults() {
        System.out.println("\nStarting Get Course Results By Student ID Test: valid student id and empty course results");

        courseResults = acr.getCourseResultsByStudentId(2);
        assertTrue("Course results list wasn't empty", courseResults.isEmpty());

        System.out.println("Finished Get Course Results By Student ID Test: valid student id and empty course results");
    }

    @Test
    public void testValid() {
        System.out.println("\nStarting Get Completed Courses Test: valid student id and non-empty course results");

        courseResults = acr.getCourseResultsByStudentId(1);
        assertEquals("ArrayList sizes weren't equal.", 2, courseResults.size());

        final DataAccess dataAccess = new DataAccessStub() {
            private ArrayList<Course> courses;
            private ArrayList<CourseOffering> courseOfferings;
            private ArrayList<CoursePlan> coursePlans;
            private ArrayList<CoursePrerequisite> coursePrerequisites;
            private ArrayList<CourseResult> courseResults;
            private ArrayList<CourseType> courseTypes;
            private ArrayList<Degree> degrees;
            private ArrayList<DegreeCourse> degreeCourses;
            private ArrayList<Department> departments;
            private ArrayList<GradeType> gradeTypes;
            private ArrayList<Rating> ratings;
            private ArrayList<RatingType> ratingTypes;
            private ArrayList<ScienceCourse> scienceCourses;
            private ArrayList<Student> students;
            private ArrayList<TermType> termTypes;
            private ArrayList<UserDefinedCourse> userDefinedCourses;

            @Override
            public void open() {
                ScienceCourse tempScienceCourse;    // Used to hold a science course so it can be added
                // to both the ScienceCourse and Course arrays
                UserDefinedCourse tempUserDefinedCourse;    // Used to hold a user defined course so it can
                // be added to both the ScienceCourse and
                // Course arrays

                // Create Types

                courseTypes = new ArrayList<CourseType>();
                courseTypes.add(new CourseType(1, "Required"));
                courseTypes.add(new CourseType(2, "Elective for Major"));

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
                departments.add(new Department(2, "Biology", "BIOL"));

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

                tempScienceCourse = new ScienceCourse(3, "Object Orientation", 3.0, 1,
                        2150, "Detailed look at proper object oriented programming.");
                courses.add(tempScienceCourse);
                scienceCourses.add(tempScienceCourse);

                tempScienceCourse = new ScienceCourse(4, "Software Engineering I", 3.0, 1,
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
                degrees.add(new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0));

                // Map courses to degrees

                degreeCourses = new ArrayList<DegreeCourse>();
                degreeCourses.add(new DegreeCourse(1, 1, 1));
                degreeCourses.add(new DegreeCourse(1, 2, 1));
                degreeCourses.add(new DegreeCourse(1, 3, 1));
                degreeCourses.add(new DegreeCourse(1, 4, 1));

                // Create Students

                students = new ArrayList<Student>();
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));
                students.add(new Student(2, 9999999, "DK", "dk@myumanitoba.ca", "password1", 1));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();
                courseResults.add(new CourseResult(1, 1, 1, 1));
                courseResults.add(new CourseResult(2, 1, 1, 2));
            }
        };

        acr = new AccessCourseResult(dataAccess);



        System.out.println("Finished Get Completed Courses Test: valid student id and non-empty course results");
    }
}

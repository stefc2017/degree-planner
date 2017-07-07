package comp3350.degree_planner.tests.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.business.AccessCoursePlan;
import comp3350.degree_planner.business.exceptions.CoursePlanDoesNotExistException;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CoursePrerequisite;
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
 * Created by Tiffany Jiang on 2017-06-07.
 *
 * Unit tests for removing a course plan
 */

public class RemoveFromCoursePlanTest {
    private AccessCoursePlan acp;
    private DataAccess testData;

    @Before
    public void setUp() {
        //Setting up test data for the remove
        //Mostly copied over from DataAccessStub.java
        testData = new DataAccessStub() {
            private List<Course> courses;
            private List<CourseOffering> courseOfferings;
            private List<CoursePlan> coursePlans;
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
            private List<CoursePrerequisite> coursePrerequisites;

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
                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", null));

                // Create Course Results

                courseResults = new ArrayList<CourseResult>();
                courseResults.add(new CourseResult(1, scienceCourses.get(0), students.get(0), new GradeType(1, "A+", 4.5)));
                courseResults.add(new CourseResult(2, scienceCourses.get(1), students.get(0), new GradeType(2, "A", 4.0)));

                // Create Course Offerings

                courseOfferings = new ArrayList<CourseOffering>();
                courseOfferings.add(new CourseOffering(scienceCourses.get(0), new TermType(1, "Fall")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(0), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(0), new TermType(3, "Summer")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(1), new TermType(1, "Fall")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(1), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(1), new TermType(3, "Summer")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(2), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(3), new TermType(2, "Winter")));
                courseOfferings.add(new CourseOffering(scienceCourses.get(3), new TermType(3, "Summer")));

                // Create Course Plans

                coursePlans = new ArrayList<CoursePlan>();
                coursePlans.add(new CoursePlan(1, scienceCourses.get(2), students.get(0),
                        new TermType(2, "Winter"), 2018));
                coursePlans.add(new CoursePlan(2, scienceCourses.get(1), students.get(0),
                        new TermType(1, "Fall"), 2017));
                coursePlans.add(new CoursePlan(3, new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220"),
                        students.get(0), new TermType(1, "Fall"), 2017));

                // Create Course Prerequisites

                coursePrerequisites = new ArrayList<CoursePrerequisite>();
                coursePrerequisites.add(new CoursePrerequisite(scienceCourses.get(1), scienceCourses.get(0)));
                coursePrerequisites.add(new CoursePrerequisite(scienceCourses.get(2), scienceCourses.get(1)));
                coursePrerequisites.add(new CoursePrerequisite(scienceCourses.get(3), scienceCourses.get(2)));
            }

            @Override
            public void removeFromCoursePlan (int coursePlanId) {
                for (int i = 0; i<coursePlans.size(); i++) {
                    if (coursePlans.get(i).getId() == coursePlanId) {
                        coursePlans.remove(i);
                        break;
                    }
                }
            }

            @Override
            public CoursePlan getCoursePlan (int coursePlanId) {
                CoursePlan result = null;
                CoursePlan currCoursePlan;

                for (int i = 0; i<coursePlans.size(); i++) {
                    currCoursePlan = coursePlans.get(i);

                    if (currCoursePlan.getId() == coursePlanId) {
                        result = currCoursePlan;
                        break;
                    }
                }

                return result;
            }
        };

        acp = new AccessCoursePlan(testData);
        testData.open(Main.dbName);
    }

    @Test(expected = CoursePlanDoesNotExistException.class)
    public void testInvalidCoursePlanId() throws Exception {
        System.out.println("\nStarting Remove From Course Plan Test: invalid course plan id");
        acp.removeFromCoursePlan(-1);
        System.out.println("Finished Remove From Course Plan Test: invalid course plan id");
    }

    @Test(expected = CoursePlanDoesNotExistException.class)
    public void testNonExistentCoursePlanId() throws Exception {
        System.out.println("\nStarting Remove From Course Plan Test: non-existent course plan id");
        acp.removeFromCoursePlan(5);
        System.out.println("Finished Remove From Course Plan Test: non-existent course plan id");
    }

    @Test
    public void testValidData() throws Exception{
        final int COURSE_PLAN_ID = 1;

        System.out.println("\nStarting Remove From Course Plan Test: valid data");

        acp.removeFromCoursePlan(COURSE_PLAN_ID);
        assertNull("Removing a course plan was not successful", testData.getCoursePlan(COURSE_PLAN_ID));

        System.out.println("Finished Remove From Course Plan Test: valid data");
    }
}

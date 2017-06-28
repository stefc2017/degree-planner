//package comp3350.degree_planner.tests.business;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import comp3350.degree_planner.application.Main;
//import comp3350.degree_planner.business.AccessCoursePlan;
//import comp3350.degree_planner.objects.Course;
//import comp3350.degree_planner.objects.CourseOffering;
//import comp3350.degree_planner.objects.CoursePlan;
//import comp3350.degree_planner.objects.CourseResult;
//import comp3350.degree_planner.objects.Degree;
//import comp3350.degree_planner.objects.DegreeCourse;
//import comp3350.degree_planner.objects.DegreeCourseType;
//import comp3350.degree_planner.objects.Department;
//import comp3350.degree_planner.objects.GradeType;
//import comp3350.degree_planner.objects.ScienceCourse;
//import comp3350.degree_planner.objects.Student;
//import comp3350.degree_planner.objects.TermType;
//import comp3350.degree_planner.objects.UserDefinedCourse;
//import comp3350.degree_planner.persistence.DataAccess;
//import comp3350.degree_planner.persistence.DataAccessStub;
//
///**
// * Created by Tiffany Jiang on 2017-06-07.
// *
// * Unit tests for moving a course in a course plan to a different term/year
// */
//
//public class RemoveFromCoursePlanTest {
//    private AccessCoursePlan acp;
//    private DataAccess testData;
//
//    @Before
//    public void setUp() {
//        //Setting up test data for the remove
//        //Mostly copied over from DataAccessStub.java with a few changes to data
//        testData = new DataAccessStub() {
//            private List<Course> courses;
//            private List<CourseOffering> courseOfferings;
//            private List<CoursePlan> coursePlans;
//            private List<CourseResult> courseResults;
//            private List<DegreeCourseType> degreeCourseTypes;
//            private List<Degree> degrees;
//            private List<DegreeCourse> degreeCourses;
//            private List<Department> departments;
//            private List<GradeType> gradeTypes;
//            private List<ScienceCourse> scienceCourses;
//            private List<Student> students;
//            private List<TermType> termTypes;
//            private List<UserDefinedCourse> userDefinedCourses;
//
//            @Override
//            public void open(String dbName) {
//                ScienceCourse tempScienceCourse;
//                UserDefinedCourse tempUserDefinedCourse;
//
//                // Create Types
//
//                degreeCourseTypes = new ArrayList<DegreeCourseType>();
//                degreeCourseTypes.add(new DegreeCourseType(1, "Required"));
//                degreeCourseTypes.add(new DegreeCourseType(2, "Elective for Major"));
//
//                termTypes = new ArrayList<TermType>();
//                termTypes.add(new TermType(1, "Fall"));
//                termTypes.add(new TermType(2, "Winter"));
//                termTypes.add(new TermType(3, "Summer"));
//
//                gradeTypes = new ArrayList<GradeType>();
//                gradeTypes.add(new GradeType(1, "A+", 4.5));
//                gradeTypes.add(new GradeType(2, "A", 4.0));
//                gradeTypes.add(new GradeType(3, "B+", 3.5));
//                gradeTypes.add(new GradeType(4, "B", 3.0));
//                gradeTypes.add(new GradeType(5, "C+", 2.5));
//                gradeTypes.add(new GradeType(6, "C", 2.0));
//                gradeTypes.add(new GradeType(7, "D", 1.0));
//                gradeTypes.add(new GradeType(8, "F", 0.0));
//
//                // Create Departments
//
//                departments = new ArrayList<Department>();
//                departments.add(new Department(1, "Computer Science", "COMP"));
//
//                // Create Courses
//
//                courses = new ArrayList<Course>();
//                scienceCourses = new ArrayList<ScienceCourse>();
//                userDefinedCourses = new ArrayList<UserDefinedCourse>();
//
//                tempScienceCourse = new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
//                        1010, "Basic programming concepts.");
//                courses.add(tempScienceCourse);
//                scienceCourses.add(tempScienceCourse);
//
//                tempScienceCourse = new ScienceCourse(2, "Introductory Computer Science II", 3.0,
//                        1, 1020, "More basic programming concepts.");
//                courses.add(tempScienceCourse);
//                scienceCourses.add(tempScienceCourse);
//
//                tempUserDefinedCourse = new UserDefinedCourse(3, "Cultural Anthropology", 3.0, "ANTH 1220");
//                courses.add(tempUserDefinedCourse);
//                userDefinedCourses.add(tempUserDefinedCourse);
//
//                // Create Degrees
//
//                degrees = new ArrayList<Degree>();
//                Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
//                degrees.add(degree);
//
//                // Map courses to degrees
//
//                degreeCourses = new ArrayList<DegreeCourse>();
//                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(1, "Introductory Computer Science I",
//                        3.0, 1, 1010, "Basic programming concepts."), new DegreeCourseType(1, "Required")));
//                degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
//                        1, 1020, "More basic programming concepts."), new DegreeCourseType(1, "Required")));
//
//                // Create Students
//
//                students = new ArrayList<Student>();
//                students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));
//
//                // Create Course Results
//
//                courseResults = new ArrayList<CourseResult>();
//                courseResults.add(new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
//                        3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
//                        "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));
//
//                // Create Course Offerings
//
//                courseOfferings = new ArrayList<CourseOffering>();
//                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
//                        3.0, 1, 1010, "Basic programming concepts."), new TermType(1, "Fall")));
//                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
//                        3.0, 1, 1010, "Basic programming concepts."), new TermType(2, "Winter")));
//                courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
//                        3.0, 1, 1010, "Basic programming concepts."), new TermType(3, "Summer")));
//                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
//                        1, 1020, "More basic programming concepts."), new TermType(1, "Fall")));
//                courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
//                        1, 1020, "More basic programming concepts."), new TermType(2, "Winter")));
//
//                // Create Course Plans
//
//                coursePlans = new ArrayList<CoursePlan>();
//                coursePlans.add(new CoursePlan(1, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
//                        1, 1020, "More basic programming concepts."), new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca",
//                        "helloworld1", 1), new TermType(1, "Fall"), 2018));
//                coursePlans.add(new CoursePlan(2, new ScienceCourse(3, "Object Orientation", 3.0, 1,
//                        2150, "Detailed look at proper object oriented programming."), new Student(1, 1234567, "Jim Bob",
//                        "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(2, "Winter"), 2018));
//            }
//
//            @Override
//            public boolean removeFromCoursePlan (int coursePlanId) {
//                boolean removeSuccessful = false;
//
//                for (int i = 0; i<coursePlans.size(); i++) {
//                    if (coursePlans.get(i).getId() == coursePlanId) {
//                        coursePlans.remove(i);
//                        removeSuccessful = true;
//                        break;
//                    }
//                }
//
//                return removeSuccessful;
//            }
//
//            @Override
//            public CoursePlan getCoursePlanById (int coursePlanId) {
//                CoursePlan result = null;
//
//                for (int i = 0; i<coursePlans.size(); i++) {
//                    if (coursePlans.get(i).getId() == coursePlanId) {
//                        result = coursePlans.get(i);
//                    }
//                }
//
//                return result;
//            }
//        };
//
//        acp = new AccessCoursePlan(testData);
//        testData.open(Main.dbName);
//    }
//
//    @Test
//    public void testInvalidCoursePlanId() {
//        System.out.println("\nStarting Remove From Course Plan Test: invalid course plan id");
//        assertFalse ("Removing a course plan with invalid ID did not fail", acp.removeFromCoursePlan(-1));
//        System.out.println("Finished Remove From Course Plan Test: invalid course plan id");
//    }
//
//    @Test
//    public void testNonExistentCoursePlanId() {
//        System.out.println("\nStarting Remove From Course Plan Test: non-existent course plan id");
//        assertFalse ("Removing a non-existent course plan did not fail", acp.removeFromCoursePlan(5));
//        System.out.println("Finished Remove From Course Plan Test: non-existent course plan id");
//    }
//
//    @Test
//    public void testValidData() {
//        final int COURSE_PLAN_ID = 1;
//
//        System.out.println("\nStarting Remove From Course Plan Test: valid data");
//
//        assertTrue ("Removing a course plan with valid data failed", acp.removeFromCoursePlan(COURSE_PLAN_ID));
//        assertNull("Removing a course plan was not successful", testData.getCoursePlanById(COURSE_PLAN_ID));
//
//        System.out.println("Finished Remove From Course Plan Test: valid data");
//    }
//}

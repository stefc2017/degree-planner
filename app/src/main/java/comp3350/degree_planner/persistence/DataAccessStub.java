package comp3350.degree_planner.persistence;

import java.util.ArrayList;
import java.util.Iterator;

import comp3350.degree_planner.objects.*;

/**
 * Created by Kaleigh on 2017-05-31.
 *
 * The DataAccessStub provides a simple stub "database" to be used
 * during development.
 *
 * Much of the code is based on code from the sample project, srsys.
 */

public class DataAccessStub {
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

    public DataAccessStub() {}

    /*
     * open
     *
     * This method generates objects for each of the ArrayLists, simulating
     * data stored in a real database.
     */

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

        // Create Course Offerings

        courseOfferings = new ArrayList<CourseOffering>();
        courseOfferings.add(new CourseOffering(1, 1));
        courseOfferings.add(new CourseOffering(1, 2));
        courseOfferings.add(new CourseOffering(1, 3));
        courseOfferings.add(new CourseOffering(2, 1));
        courseOfferings.add(new CourseOffering(2, 2));
        courseOfferings.add(new CourseOffering(2, 3));
        courseOfferings.add(new CourseOffering(3, 2));
        courseOfferings.add(new CourseOffering(4, 2));
        courseOfferings.add(new CourseOffering(4, 3));

        // Create Course Plans

        coursePlans = new ArrayList<CoursePlan>();
        coursePlans.add(new CoursePlan(3, 1, 2, 2018));
        coursePlans.add(new CoursePlan(1, 2, 1, 2017));
        coursePlans.add(new CoursePlan(5, 2, 1, 2017));

        // Create Course Prerequisites

        coursePrerequisites = new ArrayList<CoursePrerequisite>();
        coursePrerequisites.add(new CoursePrerequisite(2, 1));
        coursePrerequisites.add(new CoursePrerequisite(3, 2));
        coursePrerequisites.add(new CoursePrerequisite(4, 3));

        System.out.println("Opened stub database.");
    }

    public void close()
    {
        System.out.println("Closed stub database.");
    }

    /*
     * Created by Tiffany Jiang on 2017-06-04
     *
     * Returns a list of course results for the specified student
     */
    public ArrayList<CourseResult> getCourseResultsByStudentId (int studentId) {
        ArrayList<CourseResult> crByStudentId = new ArrayList<CourseResult>();
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

    public boolean addToCoursePlan (int courseId, int studentId, int termTypeId, int year) {
        coursePlans.add(new CoursePlan(courseId, studentId, termTypeId, year));
        //Error checking?
        return true;
    }

    public boolean removeFromCoursePlan (int coursePlanId) {
        boolean removeSuccessful = false;

        for (int i = 0; i<coursePlans.size(); i++) {
            if (coursePlans.get(i).getId() == coursePlanId) {
                coursePlans.remove(i);
                removeSuccessful = true;
                break;
            }
        }

        return removeSuccessful;
    }

    public boolean moveCourse (int coursePlanId, int newTermTypeId, int newYear) {
        CoursePlan coursePlan;
        CourseOffering currCourseOffering;
        boolean validTerm = false;
        boolean moveSuccessful = false;

        for (int i = 0; i<coursePlans.size(); i++) {
            coursePlan = coursePlans.get(i);
            if (coursePlan.getId() == coursePlanId) {
                //Checking that term entered is a term that the course is typically offered in?
                for (int j = 0; j<courseOfferings.size(); j++) {
                    currCourseOffering = courseOfferings.get(j);
                    if (currCourseOffering.getCourseId() == coursePlan.getCourseId() && currCourseOffering.getTermTypeId() == newTermTypeId) {
                        validTerm = true;
                        break;
                    }
                }

                if (validTerm) {
                    coursePlan.setTermTypeId(newTermTypeId);
                    coursePlan.setYear(newYear);
                    moveSuccessful = true;
                    break;
                }
            }
        }

        return moveSuccessful;
    }
//    private ArrayList<CourseOffering> getCourseOfferingsByCourseId (int courseId) {
//        ArrayList<CourseOffering> result = new ArrayList<CourseOffering>();
//        CourseOffering currCourseOffering;
//
//        for (int i = 0; i<courseOfferings.size(); i++) {
//            currCourseOffering = courseOfferings.get(i);
//
//            if (currCourseOffering.getCourseId() == courseId) {
//                result.add(currCourseOffering);
//            }
//        }
//
//        return result;
//    }

    public ArrayList<CoursePlan> getCoursePlanByStudentId (int studentId) {
        ArrayList<CoursePlan> result = new ArrayList<CoursePlan>();
        CoursePlan currCoursePlan;

        for (int i = 0; i<coursePlans.size(); i++) {
            currCoursePlan = coursePlans.get(i);

            if (currCoursePlan.getStudentId() == studentId) {
                result.add(currCoursePlan);
            }
        }

        return result;
    }
}

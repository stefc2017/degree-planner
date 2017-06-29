package comp3350.degree_planner.persistence;

import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.TermType;

/**
 * Created by Tiffany Jiang on 2017-06-06.
 *
 * Interface for data access
 */

public interface DataAccess {
    void open(String dbName);

    void close();

    Course getCourse(CourseResult courseResult, List<Course> allCourses);

    List<Course> getAllCourses();

    List<Degree> getAllDegrees();

    List<Course> getAllPrerequisites(Course course);

    List<CourseOffering> getAllCourseOfferings();

    List<Course> getCoursesTaken(int studentId);

    List<Course> getCoursesNotTaken(int studentNumber);

    List<Course> getCoursesCanTake(int studentNumber);

    List<Course> getEligibleRequiredCourse(int studentNum, int degreeId);

    boolean hasPrerequisites(int studentNumber, String courseName);

    Course getCourseByName(String courseName);

    Degree getDegreeByName(String degreeName);

    Course getCourseById(int courseId);

    Degree getDegreeById(int degreeId);

    Department getDepartmentById(int departmentId);

    List<CourseResult> getCourseResultsByStudentId(int studentId) throws Exception;

    int getFailingGradeId();

    List<CourseOffering> getCourseOfferingsByTerm(TermType type);

    List<Course> getDegreeCoursesTaken(int studentId, int degreeId);

    List<Course> getDegreeCourses(int degreeId);

    void addToCoursePlan (int courseId, int studentId, int termTypeId, int year) throws Exception;

    boolean isValidStudentId (int studentId) throws Exception;

    boolean isValidCourseId (int courseId) throws Exception;

    boolean isValidTermTypeId (int termTypeId) throws Exception;

    boolean courseOffered (int courseId, int termTypeId) throws Exception;

    boolean coursePlanExists (int courseId, int studentId, int termTypeId, int year) throws Exception;

    void moveCourse (int coursePlanId, int newTermTypeId, int newYear) throws Exception;

    void removeFromCoursePlan (int coursePlanId) throws Exception;

    CoursePlan getCoursePlan (int courseId, int studentId, int termTypeId, int year) throws Exception;

    CoursePlan getCoursePlan (int coursePlanId) throws Exception;
}

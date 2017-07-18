package comp3350.degree_planner.persistence;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

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
 * Interface for opening and closing the data access
 */

public interface DataAccess {
    void open(String dbName) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void close() throws SQLException;

    Connection getDataAccessConnection();

    Course getCourse(CourseResult courseResult, List<Course> allCourses);

    List<Course> getAllCourses() throws SQLException;

    List<Degree> getAllDegrees() throws SQLException;

    List<Course> getAllPrerequisites(Course course) throws SQLException;

    List<CourseOffering> getAllCourseOfferings() throws SQLException;

    List<Course> getCoursesTaken(int studentId) throws SQLException;

    List<Course> getCoursesNotTaken(int studentNumber) throws SQLException;

    List<Course> getCoursesCanTake(int studentNumber) throws SQLException;

    List<Course> getEligibleRequiredCourse(int studentNum, int degreeId) throws SQLException;

    boolean hasPrerequisites(int studentNumber, String courseName) throws SQLException;

    Course getCourseByName(String courseName) throws SQLException;

    Degree getDegreeByName(String degreeName) throws SQLException;

    Course getCourseById(int courseId) throws SQLException;

    Degree getDegreeById(int degreeId) throws SQLException;

    Department getDepartmentById(int departmentId) throws SQLException;

    List<CourseResult> getCourseResultsByStudentId(int studentId) throws SQLException;

    int getFailingGradeId() throws SQLException;

    List<CourseOffering> getCourseOfferingsByTerm(TermType type) throws SQLException;

    int getTermTypeIdByName(String termType) throws Exception;

    List<Course> getDegreeCoursesTaken(int studentId, int degreeId) throws SQLException;

    List<Course> getDegreeCourses(int degreeId) throws SQLException;

    List<Course> getAllUserDefinedCourses() throws SQLException;

    void removeUserDefinedCourse(int courseId) throws SQLException;

    void createUserDefinedCourse(String name, double creditHours, String fullAbbreviation) throws SQLException;

//    void addToCoursePlan (int courseId, int studentId, int termTypeId, int year) throws SQLException;
//
//    boolean isValidStudentId (int studentId) throws SQLException;
//
//    boolean isValidCourseId (int courseId) throws SQLException;
//
//    boolean isValidTermTypeId (int termTypeId) throws SQLException;
//
//    boolean courseOffered (int courseId, int termTypeId) throws SQLException;
//
//    boolean coursePlanExists (int courseId, int studentId, int termTypeId, int year) throws SQLException;
//
//    void moveCourse (int coursePlanId, int newTermTypeId, int newYear) throws SQLException;
//
//    void removeFromCoursePlan (int coursePlanId) throws SQLException;
//
//    CoursePlan getCoursePlan (int courseId, int studentId, int termTypeId, int year) throws SQLException;
//
//    CoursePlan getCoursePlan (int coursePlanId) throws SQLException;
//
//    List<CoursePlan> getCoursePlansByStudentId (int studentId) throws SQLException;
}

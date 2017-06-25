package comp3350.degree_planner.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.TermType;

/**
 * Created by Tiffany Jiang on 2017-06-24.
 */

public class DataAccessObject implements DataAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    private ArrayList<Degree> degrees;

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void open() {

    }

    public void open(String dbPath)
    {
        String url;
        int result;

        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

//            result = st1.executeUpdate("CREATE MEMORY TABLE DEGREE(ID INTEGER NOT NULL PRIMARY KEY, NAME VARCHAR(20), CREDIT_HOURS FLOAT, MAJOR_CREDIT_HOURS FLOAT, GPA_REQUIRED FLOAT)");
//            result = st1.executeUpdate("INSERT INTO DEGREE VALUES(1,'Computer Science Major', 120.0, 81.0, 2.0)");
//            result = st1.executeUpdate("INSERT INTO DEGREE VALUES(2,'Computer Science Honours', 120.0, 81.0, 3.0)");
//            result = st1.executeUpdate("INSERT INTO DEGREE VALUES(3,'Statistics Major', 120.0, 81.0, 2.0)");
        }
        catch (Exception e)
        {
            System.out.println (e);
//            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
//            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public ArrayList<Course> getCoursesNotTaken(int studentNumber) {
        return null;
    }

    public Course getCourse(CourseResult courseResult, ArrayList<Course> allCourses) {
        return null;
    }

    public ArrayList<Course> getAllCourses() {
        return null;
    }

    public ArrayList<Course> getCoursesCanTake(int studentNumber) {
        return null;
    }

    public boolean hasPrerequisites(int studentNumber, String courseName) {
        return false;
    }

    public ArrayList<Course> getAllPrerequisites(Course course) {
        return null;
    }

    public Course findCourse(int courseId) {
        return null;
    }

    public Course findCourse(String courseName) {
        return null;
    }

    public ArrayList<Degree> getAllDegrees() {
        Degree degree;
        int id;
        String name;
        double creditHours, majorCreditHours, gpaRequired;
        degrees = new ArrayList<Degree>();

        result = null;
        try
        {
            cmdString = "Select * from Degree";
            rs2 = st1.executeQuery(cmdString);
        }
        catch (Exception e)
        {
//            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                id = Integer.parseInt(rs2.getString("ID"));
                name = rs2.getString("NAME");
                creditHours = Double.parseDouble(rs2.getString("CREDIT_HOURS"));
                majorCreditHours = Double.parseDouble(rs2.getString("MAJOR_CREDIT_HOURS"));
                gpaRequired = Double.parseDouble(rs2.getString("GPA_REQUIRED"));
                degree = new Degree(id, name, creditHours, majorCreditHours, gpaRequired);
                degrees.add(degree);
            }
            rs2.close();
        }
        catch (Exception e)
        {
//            result = processSQLError(e);
        }

        return degrees;
    }

    public Degree getDegreeByName(String degreeName) {
        return null;
    }

    public Degree getDegreeById(int degreeId) {
        return null;
    }

    public ArrayList<CourseResult> getCourseResultsByStudentId(int studentId) {
        return null;
    }

    public ArrayList<CourseOffering> getAllCourseOfferings() {
        return null;
    }

    public int getFailingGradeId() {
        return -1;
    }

    public Course getCourseById(int courseId) {
        return null;
    }

    public ArrayList<CourseOffering> getCourseOfferingsByTerm(TermType type) {
        return null;
    }

    public Department getDepartmentById(int departmentId) {
        return null;
    }

    public ArrayList<Course> getCoursesTaken(int studentId) {
        return null;
    }

    public ArrayList<Course> getDegreeCoursesTaken(int studentId, int degreeId) {
        return null;
    }

    public ArrayList<Course> getDegreeCourses(int degreeId) {
        return null;
    }

    public ArrayList<Course> getEligibleRequiredCourse(int studentNum, int degreeId) {
        return null;
    }

    public int addToCoursePlan (int courseId, int studentId, int termTypeId, int year) {
        return -1;
    }

    public boolean moveCourse (int coursePlanId, int newTermTypeId, int newYear) {
        return false;
    }

    public boolean removeFromCoursePlan (int coursePlanId) {
        return false;
    }

    public CoursePlan getCoursePlanById (int coursePlanId) {
        return null;
    }
}

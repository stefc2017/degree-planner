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
 *
 * Comments about the database:
 * - There are a total of 14 tables (for 16 objects)
 * - See Degree_Planner.script (in assets->db) for required info, basically same as what's in Objects
 * - Has been filled with same "default" info as stub, as instructed
 * - All courses are in 1 table: Course table contains some columns corresponding to fields belonging
 * to only ScienceCourse or UserDefinedCourse that are nullable, and also contains a IsUserDefined boolean
 * - Most of the id's are auto-generated (starts at 1, increments for each insert),
 * except for: reference type tables and some tables where some combo of its attributes make up the primary key
 * (again, generally same as what we currently have in the objects - if there's no id field, no id column)
 * - Basically I've found out how the db runs is that when the person runs the app the first time,
 * the db is set up through whatever's in Degree_Planner.script
 * - Then you can only make changes to db through executing updates/queries via DataAccessObject.java,
 * changes to Degree_Planner.script will not be run again
 * - There is currently only 1 student (id 1), please use that as the student for now,
 * at least until we get login capabilities set up
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

    public void open(String dbPath)
    {
        String url;

        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        }
        catch (Exception e)
        {
            processSQLError(e);
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
            processSQLError(e);
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
            processSQLError(e);
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
            result = processSQLError(e);
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

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}

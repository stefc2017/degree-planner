package comp3350.degree_planner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.degree_planner.application.Services;
import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseOffering;
import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.Degree;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;
import comp3350.degree_planner.persistence.DataAccessCoursePlan;

/**
 * Created by tiffanyjiang on 2017-07-18.
 */

public class DataAccessCoursePlanObject implements DataAccessCoursePlan {
    private Statement st1, st2, st3;
    private static Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessCoursePlanObject() throws SQLException {
        c1 = Services.getDataAccess().getDataAccessConnection();

        st1 = c1.createStatement();
        st2 = c1.createStatement();
        st3 = c1.createStatement();
    }

    public void addToCoursePlan(int courseId, int studentId, int termTypeId, int year) throws SQLException {
        String newCoursePlanValues = courseId + ", " + studentId + ", " + termTypeId + ", " + year;
        cmdString = "INSERT INTO Course_Plan (Course_Id, Student_Id, Term_Type_Id, Year) " + " VALUES (" + newCoursePlanValues + ")";
        updateCount = st1.executeUpdate(cmdString);
    }

    //These next few methods perform checks (as stated respectively) for adding and modify course plans

    public boolean courseOffered(int courseId, int termTypeId) throws SQLException {
        boolean validTerm = false;

        //For user-defined courses, let the user freely enter the the term and year
        cmdString = "Select is_user_defined from Course where id = " + courseId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            validTerm = rs4.getBoolean("is_user_defined");
        }
        rs4.close();

        if (!validTerm) {
            //Is the course historically offered in this term?
            cmdString = "Select count(*) as courseOfferingCount from Course_Offering where course_id = " + courseId + " and term_type_id = " + termTypeId;
            rs4 = st2.executeQuery(cmdString);
            while (rs4.next()) {
                validTerm = (rs4.getInt("courseOfferingCount") > 0);
            }
            rs4.close();
        }

        return validTerm;
    }

    public boolean isValidStudentId(int studentId) throws SQLException {
        boolean studentExists = false;

        cmdString = "Select count(*) as studentCount from Student where id = " + studentId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            studentExists = (rs4.getInt("studentCount") > 0);
        }
        rs4.close();

        return studentExists;
    }

    public boolean isValidCourseId(int courseId) throws SQLException {
        boolean courseExists = false;

        cmdString = "Select count(*) as courseCount from Course where id = " + courseId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            courseExists = (rs4.getInt("courseCount") > 0);
        }
        rs4.close();

        return courseExists;
    }

    public boolean isValidTermTypeId(int termTypeId) throws SQLException {
        boolean termTypeExists = false;

        cmdString = "Select count(*) as termTypeCount from Term_Type where id = " + termTypeId;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            termTypeExists = (rs4.getInt("termTypeCount") > 0);
        }
        rs4.close();

        return termTypeExists;
    }

    public boolean coursePlanExists(int courseId, int studentId, int termTypeId, int year) throws SQLException {
        boolean coursePlanExists = false;

        cmdString = "Select count(*) as coursePlanCount from Course_Plan where course_id = " + courseId + " and student_id = " + studentId + " and term_type_id = " + termTypeId + " and year = " + year;
        rs4 = st2.executeQuery(cmdString);
        while (rs4.next()) {
            coursePlanExists = (rs4.getInt("coursePlanCount") > 0);
        }
        rs4.close();

        return coursePlanExists;
    }

    public void moveCourse(int coursePlanId, int newTermTypeId, int newYear) throws SQLException {
        cmdString = "UPDATE Course_Plan SET term_type_id = " + newTermTypeId + ", year = " + newYear + " WHERE id = " + coursePlanId;
        updateCount = st1.executeUpdate(cmdString);
    }

    public void removeFromCoursePlan(int coursePlanId) throws SQLException {
        cmdString = "DELETE FROM Course_Plan WHERE id = " + coursePlanId;
        updateCount = st3.executeUpdate(cmdString);
    }

    public CoursePlan getCoursePlan(int courseId, int studentId, int termTypeId, int year) throws SQLException {
        CoursePlan cp = null;
        Course course;
        Student student;
        TermType termType;

        cmdString = "SELECT * FROM Course_Plan cp WHERE cp.course_id = " + courseId + " and cp.student_id = " + studentId + " and cp.term_type_id = " + termTypeId + " and cp.year = " + year;
        rs4 = st2.executeQuery(cmdString);

        while (rs4.next()) {
            course = getCourseById(rs4.getInt("course_id"));
            student = getStudentById(rs4.getInt("student_id"));
            termType = getTermTypeById(rs4.getInt("term_type_id"));
            cp = new CoursePlan(rs4.getInt("id"), course, student, termType, rs4.getInt("year"));
        }
        rs4.close();

        return cp;
    }

    public CoursePlan getCoursePlan(int coursePlanId) throws SQLException {
        CoursePlan cp = null;
        Course course;
        Student student;
        TermType termType;

        cmdString = "SELECT * FROM Course_Plan WHERE id = " + coursePlanId;
        rs4 = st2.executeQuery(cmdString);

        while (rs4.next()) {
            course = getCourseById(rs4.getInt("course_id"));
            student = getStudentById(rs4.getInt("student_id"));
            termType = getTermTypeById(rs4.getInt("term_type_id"));
            cp = new CoursePlan(rs4.getInt("id"), course, student, termType, rs4.getInt("year"));
        }
        rs4.close();

        return cp;
    }

    private Student getStudentById(int studentId) throws SQLException {
        Student student = null;

        cmdString = "Select * from Student where id = " + studentId;
        rs2 = st1.executeQuery(cmdString);

        while (rs2.next()) {
            student = new Student(rs2.getInt("id"), rs2.getInt("student_number"),
                    rs2.getString("name"), rs2.getString("email"), rs2.getString("password"),
                    getDegreeById(rs2.getInt("degree_id")));
        }
        rs2.close();

        return student;
    }

    private TermType getTermTypeById(int termTypeId) throws SQLException {
        TermType tt = null;

        cmdString = "Select * from Term_Type where id = " + termTypeId;
        rs2 = st1.executeQuery(cmdString);

        while (rs2.next()) {
            tt = new TermType(rs2.getInt("id"), rs2.getString("season"));
        }
        rs2.close();

        return tt;
    }


    /**
     * getCoursePlansByStudentId
     *
     * @param studentId: The ID of the student whose course plans will be found
     * @return: List of all course plans for the student
     **/

    public List<CoursePlan> getCoursePlansByStudentId(int studentId) throws SQLException {
        ArrayList<CoursePlan> coursePlans = new ArrayList<CoursePlan>();

        int coursePlanId;
        int coursePlanYear;

        Course course;
        int courseId;
        String courseName;
        double courseCreditHours;
        int courseNumber = 0;
        String courseDescription = null;
        int courseDepartmentId = 0;
        String courseFullAbbreviation = null;
        boolean isUserDefined;

        Department department;

        TermType termType;
        int termTypeId;
        String termTypeSeason;

        Student student;

        cmdString = "select cp.ID, cp.YEAR, " +
                "c.ID as COURSE_ID, c.NAME as COURSE_NAME, c.CREDIT_HOURS, " +
                "c.COURSE_NUMBER, c.DESCRIPTION, c.DEPARTMENT_ID, " +
                "c.FULL_ABBREVIATION, c.IS_USER_DEFINED, " +
                "tt.ID as TERM_TYPE_ID, tt.SEASON " +
                "from Course_Plan cp inner join " +
                "Course c on cp.COURSE_ID = c.ID inner join " +
                "Term_Type tt on cp.TERM_TYPE_ID = tt.ID " +
                "where cp.STUDENT_ID = " + studentId + " " +
                "order by cp.YEAR, tt.ID";
        rs5 = st1.executeQuery(cmdString);

        student = getStudentById(studentId);

        while (rs5.next()) {
            // Get the Course Plan information
            coursePlanId = rs5.getInt("ID");
            coursePlanYear = rs5.getInt("YEAR");

            // Get the course information
            courseId = rs5.getInt("COURSE_ID");
            courseName = rs5.getString("COURSE_NAME");
            courseCreditHours = rs5.getDouble("CREDIT_HOURS");
            isUserDefined = rs5.getBoolean("IS_USER_DEFINED");
            if (isUserDefined) {
                courseFullAbbreviation = rs5.getString("FULL_ABBREVIATION");
                course = new UserDefinedCourse(courseId, courseName, courseCreditHours,
                        courseFullAbbreviation);
            } else {
                courseNumber = rs5.getInt("COURSE_NUMBER");
                courseDescription = rs5.getString("DESCRIPTION");

                // Get Department information
                courseDepartmentId = rs5.getInt("DEPARTMENT_ID");
                department = getDepartmentById(courseDepartmentId);

                course = new ScienceCourse(courseId, courseName, courseCreditHours,
                        department, courseNumber, courseDescription);
            }

            // Get the term type information
            termTypeId = rs5.getInt("TERM_TYPE_ID");
            termTypeSeason = rs5.getString("SEASON");
            termType = new TermType(termTypeId, termTypeSeason);

            // Add this course plan to the list the CoursePlan
            coursePlans.add(new CoursePlan(coursePlanId, course, student, termType, coursePlanYear));
        }
        rs5.close();

        return coursePlans;
    }


    public Degree getDegreeById(int degreeId) throws SQLException {
        Degree degree;
        int id;
        String name;
        double creditHours, majorCreditHours, gpaRequired;

        degree = null;
        result = null;

        cmdString = "Select * from Degree where ID = " + degreeId;
        rs2 = st1.executeQuery(cmdString);

        while (rs2.next()) {
            id = Integer.parseInt(rs2.getString("ID"));
            name = rs2.getString("NAME");
            creditHours = Double.parseDouble(rs2.getString("CREDIT_HOURS"));
            majorCreditHours = Double.parseDouble(rs2.getString("MAJOR_CREDIT_HOURS"));
            gpaRequired = Double.parseDouble(rs2.getString("GPA_REQUIRED"));
            degree = new Degree(id, name, creditHours, majorCreditHours, gpaRequired);
        }

        rs2.close();

        return degree;
    }

    public Course getCourseById(int courseId) throws SQLException {
        Course course;
        int id;
        String name;
        double creditHours;
        int courseNumber;
        String description;
        int departmentId;
        String fullAbbreviation;
        Boolean isUserDefined;
        Department department;

        course = null;
        result = null;

        cmdString = "Select * from Course where ID = " + courseId;
        rs2 = st1.executeQuery(cmdString);

        while (rs2.next()) {
            id = Integer.parseInt(rs2.getString("ID"));
            name = rs2.getString("NAME");
            creditHours = Double.parseDouble(rs2.getString("CREDIT_HOURS"));
            departmentId = rs2.getInt("DEPARTMENT_ID");
            courseNumber = rs2.getInt("COURSE_NUMBER");
            description = rs2.getString("DESCRIPTION");
            fullAbbreviation = rs2.getString("FULL_ABBREVIATION");
            isUserDefined = Boolean.parseBoolean(rs2.getString("IS_USER_DEFINED"));

            if (isUserDefined) {
                course = new UserDefinedCourse(id, name, creditHours, fullAbbreviation);
            } else {
                department = getDepartmentById(departmentId);
                course = new ScienceCourse(id, name, creditHours, department, courseNumber, description);
            }
        }

        rs2.close();

        return course;
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        Department department;
        int id;
        String name;
        String abbreviation;

        department = null;
        result = null;

        cmdString = "Select * from DEPARTMENT where ID = " + departmentId;
        rs6 = st1.executeQuery(cmdString);

        while (rs6.next()) {
            id = rs6.getInt("ID");
            name = rs6.getString("NAME");
            abbreviation = rs6.getString("ABBREVIATION");
            department = new Department(id, name, abbreviation);
        }

        return department;
    }

    public int getTermTypeIdByName(String termType) throws SQLException {
        int termTypeId = -1;

        cmdString = "Select * from Term_Type where Season = " + "'" + termType + "'";
        rs2 = st1.executeQuery(cmdString);

        while(rs2.next()) {
            termTypeId = rs2.getInt("ID");
        }
        rs2.close();

        return termTypeId;
    }
}

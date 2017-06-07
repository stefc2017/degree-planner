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

    /**
     * getCoursesNotTaken
     * @param studentNumber: The student number of the student we want to search for and get the
     *                       courses they have not taken yet.
     * @return: The courses that student has not taken yet.
     **/
    public ArrayList<Course> getCoursesNotTaken(int studentNumber){
        ArrayList<Course> coursesNotTaken; //the list of courses that the student has not taken
        ArrayList<Course> allCourses = getAllCourses(); //the list of all courses offered
        ArrayList<CourseResult> coursesTaken = getCourseResultsByStudentId(studentNumber); //the list of courses taken
                                                                               //by the student
        int numCoursesTaken = coursesTaken.size(); //number of courses taken
        int index = -1; //whether or not the course the student took is a course in the list
        int numberOfCourses; //the total number of courses offered
        Course currentCourse; //the current course

        for(int j = 0; j < numCoursesTaken; j++){
            numberOfCourses = allCourses.size();

            if(getCourse(coursesTaken.get(j), allCourses) != null){
                currentCourse = getCourse(coursesTaken.get(j), allCourses);
                index = allCourses.indexOf(currentCourse);

                if(index != -1){ //if the course is in the list of courses
                    allCourses.remove(index);
                }//end if
            }//end if
        }//end for

        coursesNotTaken = allCourses;

        return coursesNotTaken;
    }//end getCoursesNotTaken

    /**
     * getCourse
     * @param courseResult: a CourseList that we want to convert to a Course object
     * @param allCourses: an arraylist of all the courses in the database
     * @return a Course Object
     **/
    public Course getCourse(CourseResult courseResult, ArrayList<Course> allCourses){
        Course course = null;
        int courseId = courseResult.getCourseId();
        int numberOfCourses = allCourses.size();
        int index = 0;

        while(index < numberOfCourses && (allCourses.get(index)).getId() != courseId){
            index++;
        }//end while

        if(index < numberOfCourses && (allCourses.get(index)).getId() == courseId){
            course = allCourses.get(index);
        }//end if

        return course;
    }//end

    /**
     * getAllCourses
     * @return: An arrayList of all the courses offered
     **/
    public ArrayList<Course> getAllCourses(){
        return courses;
    }//end getAllCourses

    /**
     * getCoursesCanTake
     * @param studentNumber: the student number of the student. We want to get all the courses that this student
     *                       has not taken but has the preRequisites to take
     * @return: an arraylist of all the courses the student has the preRequisites for.
     **/
    public ArrayList<Course> getCoursesCanTake(int studentNumber){
        ArrayList<Course> coursesNotTaken = getCoursesNotTaken(studentNumber); //get the courses that student has not taken yet
        ArrayList<Course> coursesCanTake = new ArrayList<Course>(); //will contain the courses the student can take
        int numOfCoursesNotTaken = coursesNotTaken.size(); //number of courses that the student has not taken
        Course currentCourse;

        for(int i = 0; i < numOfCoursesNotTaken; i++){
            currentCourse = coursesNotTaken.get(i);

            if(hasPrerequisites(studentNumber, currentCourse.getName())){ //if the student has all preRequisites for the course
                coursesCanTake.add(currentCourse);
            }//end if

        }//end for

        return coursesCanTake;
    }//end getCoursesCanTake

    /**
     * hasPrerequisites
     * @param studentNumber: the student number of the student we want to check if they have all preReqs to take the
     *                       course with courseName given as a parameter
     * @param courseName: the name of the course we want to see if the student has all preReqs for it
     * @return: an arraylist of all prerequisites of the course given as a parameter
     **/
    public boolean hasPrerequisites(int studentNumber, String courseName){
        ArrayList<CourseResult> coursesTaken = getCourseResultsByStudentId(studentNumber); //the courses the student has taken
        Course course = findCourse(courseName); //the course we want to get the preRequisites of
        ArrayList<Course> coursePreReqs = null; //the preReqs for the course
        boolean hasPreReqs = true; //initially- we have all preReqs say if there is no preRequisites
        Course currentPreReq; //the current preReq, used for in the while loop
        int outer_index = 0; //index for the outer while loop
        int inner_index; //index for the inner while loop
        int preReqId; //the id of the prerequisite

        if(course != null){ //if the course was found
            coursePreReqs = getAllPrerequisites(course); //gets all the preRequisites of the course

            //determine if all prereqs were taken

            while( hasPreReqs && outer_index < coursePreReqs.size()){ //goes through each preReq Course
                currentPreReq = coursePreReqs.get(outer_index);

                if(currentPreReq != null){
                    preReqId = currentPreReq.getId();
                    inner_index = 0; //initialize inner_index to 0

                    while( hasPreReqs && inner_index < coursesTaken.size() && preReqId !=
                            (coursesTaken.get(inner_index)).getCourseId()){ //goes through each course that the student has taken
                        inner_index++;
                    }//end while

                    if(inner_index >= coursesTaken.size()){ //if we searched through all the courses the student has taken but they
                        hasPreReqs = false;                   //don't have the prerequisite.
                    }//end if
                }//end if

                outer_index++;
            }//end while

        }//end if
        return hasPreReqs;
    }//end hasPrerequisites

    /**
     * getAllPrerequisites
     * @param course: the course we want to get all the prerequisites of
     * @return: an arraylist of all prerequisites of the course given as a parameter
     **/
    public ArrayList<Course> getAllPrerequisites(Course course){
        ArrayList<Course> prerequisites = new ArrayList<Course>();
        int numberOfCoursePrereqs = coursePrerequisites.size(); //the number of prerequisites
        int courseId = course.getId(); //the course number of the course object
        Course currentCourse; //to keep track of the current course

        for(int i = 0; i < numberOfCoursePrereqs; i++){
            if((coursePrerequisites.get(i)).getCourseId() == courseId){ //if this is a prerequisite for the course
                currentCourse = findCourse((coursePrerequisites.get(i)).getPrereqCourseId()); //get the course
                prerequisites.add(currentCourse); //add the course to the list of prerequisites
            }//end if
        }//end for

        return prerequisites;
    }//end getAllPrerequisites

    /**
     * findCourse
     * @param courseId: given the course Id, find that course
     * @return: the course with the id specified as the parameter
     **/
    public Course findCourse(int courseId){
        int numberOfCourses = courses.size(); //the number of all courses
        Course course = null; //the course we will return
        int index = 0; //index for searching

        while(index < numberOfCourses && (courses.get(index)).getId() != courseId){
            index++;
        }//end while

        if(index < numberOfCourses && (courses.get(index)).getId() == courseId){ //we found the course
            course = courses.get(index);
        }//end if

        return course;
    }//end findCourse

    /**
     * findCourse
     * @param courseName: given the course name, find that course
     * @return: the course with the name specified as the parameter
     **/
    public Course findCourse(String courseName){
        int numberOfCourses = courses.size(); //the number of all courses
        Course course = null; //the course we will return
        int index = 0; //index for searching

        while(index < numberOfCourses && !((courses.get(index)).getName()).equals(courseName)){
            index++;
        }//end while

        if(index < numberOfCourses && ((courses.get(index)).getName()).equals(courseName)){ //we found the course
            course = courses.get(index);
        }//end if

        return course;
    }//end findCourse

    /**
     * getAllDegrees
     * @return: All the degrees that are offered.
     **/
    public ArrayList<Degree> getAllDegrees(){
        return degrees;
    }//end getAllDegrees

    /**
     * getDegreeByName
     * @param degreeName: The name of the degree you want to find information about.
     * @return: The degree with the name given as a parameter or null if that degree does not exist.
     **/
    public Degree getDegreeByName(String degreeName){
        ArrayList<Degree> allDegrees = degrees; //all degrees offered
        int numberOfDegrees = allDegrees.size(); //number of degrees offered
        Degree degree = null;
        int index = 0; //index to loop through degrees

        while(index < numberOfDegrees && !((allDegrees.get(index)).getName()).equals(degreeName)){
            index++;
        }//end while

        if(index < numberOfDegrees && ((allDegrees.get(index)).getName()).equals(degreeName)){
            degree = allDegrees.get(index); //get the degree with the name given as a parameter
        }//end if

        return degree;
    }//end getDegreeByName

    /**
     * getDegreeById
     * @param degreeId: The id number of the degree you want to find.
     * @return: The degree with the id given as the parameter.
     **/
    public Degree getDegreeById(int degreeId){
        ArrayList<Degree> allDegrees = degrees; //all degrees offered
        int numberOfDegrees = allDegrees.size(); //number of degrees offered
        Degree degree = null;
        int index = 0; //index to loop through degrees

        while(index < numberOfDegrees && (allDegrees.get(index)).getId() != degreeId){
            index++;
        }//end while

        if(index < numberOfDegrees && (allDegrees.get(index)).getId() == degreeId){
            degree = allDegrees.get(index); //get the degree with the id given as a parameter
        }//end if

        return degree;
    }//end getDegreeById

    /*
     * Created by Tiffany Jiang on 2017-06-04
     *
     * Returns a list of course results for the specified student
     */
    public ArrayList<CourseResult> getCourseResultsByStudentId (int studentId) {
        ArrayList<CourseResult> crByStudentId = new ArrayList<CourseResult>();
        CourseResult currCR;

        for (int i = 0; i<courseResults.size(); i++) {
            currCR = courseResults.get(i);
            if (currCR.getStudentId() == studentId) {
                crByStudentId.add(currCR);
            }
        }

        return crByStudentId;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of courses taken by a given student
     */
    public ArrayList<Course> getCoursesTaken( int studentId ){
        ArrayList<Course> coursesTaken = new ArrayList<Course>();
        ArrayList<CourseResult> crByStudentId = getCourseResultsByStudentId( studentId );

        for( CourseResult result : crByStudentId ){
            coursesTaken.add( findCourse( result.getCourseId() ));
        }

        return coursesTaken;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of degree required courses student has taken
    */
    public ArrayList<Course> getDegreeCoursesTaken( int studentId, int degreeId ){
        ArrayList<Course> coursesTaken = getCoursesTaken( studentId );
        ArrayList<Course> degreeCourses = getDegreeCourses( degreeId );
        ArrayList<Course> takenDegreeCourses = new ArrayList<Course>();

        for( Course degreeCourse : degreeCourses ){
            if( coursesTaken.contains( degreeCourse ) ){
                takenDegreeCourses.add( degreeCourse );
            }
        }

        return takenDegreeCourses;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of courses required by a degree
    */
    public ArrayList<Course> getDegreeCourses( int degreeId ) {
        ArrayList<Course> reqCourseList = new ArrayList<Course>();

        for( DegreeCourse course : degreeCourses ){
            if( course.getDegreeId() == degreeId ){
                reqCourseList.add( findCourse( course.getCourseId() ) );
            }
        }

        return reqCourseList;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of required degree courses that a given student can take
    */
    public ArrayList<Course> getEligibleRequiredCourse( int studentNum, int degreeId ){
        ArrayList<Course> coursesTaken = getCoursesTaken( studentNum );
        ArrayList<Course> degreeCourses = getDegreeCourses( degreeId );
        ArrayList<Course> notTakenDegreeCourses = new ArrayList<Course>();
        ArrayList<Course> eligibleDegreeCourses = new ArrayList<Course>();

        for( Course degreeCourse : degreeCourses ){
            if( !(coursesTaken.contains( degreeCourse )) ){
                notTakenDegreeCourses.add( degreeCourse );
            }
        }

        for( Course course : notTakenDegreeCourses ){
            if( hasPrerequisites( studentNum, course.getName() ) ){
                eligibleDegreeCourses.add( course );
            }
        }

        return eligibleDegreeCourses;
    }
}

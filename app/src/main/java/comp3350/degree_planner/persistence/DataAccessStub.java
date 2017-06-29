package comp3350.degree_planner.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comp3350.degree_planner.application.Main;
import comp3350.degree_planner.objects.*;

/**
 * Created by Kaleigh on 2017-05-31.
 *
 * The DataAccessStub provides a simple stub "database" to be used
 * during development.
 *
 * Much of the code is based on code from the sample project, srsys.
 */

public class DataAccessStub implements DataAccess {
    private List<Course> courses;
    private List<CourseOffering> courseOfferings;
    private List<CoursePlan> coursePlans;
    private List<CoursePrerequisite> coursePrerequisites;
    private List<CourseResult> courseResults;
    private List<DegreeCourseType> degreeCourseTypes;
    private List<Degree> degrees;
    private List<DegreeCourse> degreeCourses;
    private List<Department> departments;
    private List<GradeType> gradeTypes;
    private List<Rating> ratings;
    private List<RatingType> ratingTypes;
    private List<ScienceCourse> scienceCourses;
    private List<Student> students;
    private List<TermType> termTypes;
    private List<UserDefinedCourse> userDefinedCourses;

    private String dbName;
    private String dbType = "stub";

    public DataAccessStub() {
        this.dbName = Main.dbName;
    }

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    /*
     * open
     *
     * This method generates objects for each of the ArrayLists, simulating
     * data stored in a real database.
     */

    public void open(String dbName) {
        ScienceCourse tempScienceCourse;    // Used to hold a science course so it can be added
        // to both the ScienceCourse and Course arrays
        UserDefinedCourse tempUserDefinedCourse;    // Used to hold a user defined course so it can
        // be added to both the ScienceCourse and
        // Course arrays

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
        Degree degree = new Degree(1, "Computer Science Major", 120.0, 81.0, 2.0);
        degrees.add(degree);

        // Map courses to degrees

        degreeCourses = new ArrayList<DegreeCourse>();
        degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts."), new DegreeCourseType(1, "Required")));
        degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts."), new DegreeCourseType(1, "Required")));
        degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(3, "Object Orientation", 3.0, 1,
                2150, "Detailed look at proper object oriented programming."), new DegreeCourseType(1, "Required")));
        degreeCourses.add(new DegreeCourse(degree, new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                3350, "Good software development practices."), new DegreeCourseType(1, "Required")));

        // Create Students

        students = new ArrayList<Student>();
        students.add(new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1));

        // Create Course Results

        courseResults = new ArrayList<CourseResult>();
        courseResults.add(new CourseResult(1, new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(1, "A+", 4.5)));
        courseResults.add(new CourseResult(2, new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts."), new Student(1, 1234567, "Jim Bob",
                "jimbob@myumanitoba.ca", "helloworld1", 1), new GradeType(2, "A", 4.0)));

        // Create Course Offerings

        courseOfferings = new ArrayList<CourseOffering>();
        courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts."), new TermType(1, "Fall")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts."), new TermType(2, "Winter")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts."), new TermType(3, "Summer")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts."), new TermType(1, "Fall")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts."), new TermType(2, "Winter")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts."), new TermType(3, "Summer")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(3, "Object Orientation", 3.0, 1,
                2150, "Detailed look at proper object oriented programming."), new TermType(2, "Winter")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                3350, "Good software development practices."), new TermType(2, "Winter")));
        courseOfferings.add(new CourseOffering(new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                3350, "Good software development practices."), new TermType(3, "Summer")));

        // Create Course Plans

        coursePlans = new ArrayList<CoursePlan>();
        coursePlans.add(new CoursePlan(1, new ScienceCourse(3, "Object Orientation", 3.0, 1, 2150,
                "Detailed look at proper object oriented programming."), new Student(1,
                1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(2, "Winter"), 2018));
        coursePlans.add(new CoursePlan(2, new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1, 1010,
                "Basic programming concepts."), new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1),
                new TermType(1, "Fall"), 2017));
        coursePlans.add(new CoursePlan(3, new UserDefinedCourse(5, "Cultural Anthropology", 3.0, "ANTH 1220"),
                new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1), new TermType(1, "Fall"), 2017));

        // Create Course Prerequisites

        coursePrerequisites = new ArrayList<CoursePrerequisite>();
        coursePrerequisites.add(new CoursePrerequisite(new ScienceCourse(2, "Introductory Computer Science II", 3.0,
                1, 1020, "More basic programming concepts."), new ScienceCourse(1, "Introductory Computer Science I",
                3.0, 1, 1010, "Basic programming concepts.")));
        coursePrerequisites.add(new CoursePrerequisite(new ScienceCourse(3, "Object Orientation", 3.0, 1,
                2150, "Detailed look at proper object oriented programming."), new ScienceCourse(2,
                "Introductory Computer Science II", 3.0, 1, 1020, "More basic programming concepts.")));
        coursePrerequisites.add(new CoursePrerequisite(new ScienceCourse(4, "Software Engineering I", 3.0, 1,
                3350, "Good software development practices."), new ScienceCourse(3, "Object Orientation", 3.0, 1,
                2150, "Detailed look at proper object oriented programming.")));

        //Create Rating data
        ratingTypes = new ArrayList<RatingType>();
        ratingTypes.add (new RatingType(1, "Excellent", 5));
        ratingTypes.add (new RatingType(2, "Good", 4));
        ratingTypes.add (new RatingType(3, "Fair", 3));
        ratingTypes.add (new RatingType(4, "Poor", 2));
        ratingTypes.add (new RatingType(5, "Very Poor", 1));

        ratings = new ArrayList<Rating>();
        ratings.add (new Rating(1, new Student(1, 1234567, "Jim Bob", "jimbob@myumanitoba.ca", "helloworld1", 1),
                new ScienceCourse(1, "Introductory Computer Science I", 3.0, 1,
                1010, "Basic programming concepts."), new RatingType(1, "Excellent", 5),
                "I learned a lot from this course!"));

        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close() {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    /**
     * getCoursesNotTaken
     *
     * @param studentNumber: The student number of the student we want to search for and get the
     *                       courses they have not taken yet.
     * @return: The courses that student has not taken yet.
     **/
    public List<Course> getCoursesNotTaken(int studentNumber) {
        List<Course> coursesNotTaken; //the list of courses that the student has not taken
        List<Course> allCourses = getAllCourses(); //the list of all courses offered
        List<CourseResult> coursesTaken = getCourseResultsByStudentId(studentNumber); //the list of courses taken by the student
        int numCoursesTaken = coursesTaken.size(); //number of courses taken
        int index = -1; //whether or not the course the student took is a course in the list
        int numberOfCourses; //the total number of courses offered
        Course currentCourse; //the current course

        for (int j = 0; j < numCoursesTaken; j++) {
            numberOfCourses = allCourses.size();

            if (getCourse(coursesTaken.get(j), allCourses) != null) {
                currentCourse = getCourse(coursesTaken.get(j), allCourses);
                index = allCourses.indexOf(currentCourse);

                if (index != -1) { //if the course is in the list of courses
                    allCourses.remove(index);
                }//end if
            }//end if
        }//end for

        coursesNotTaken = allCourses;

        return coursesNotTaken;
    }//end getCoursesNotTaken

    /**
     * getCourse
     *
     * @param courseResult: a CourseList that we want to convert to a Course object
     * @param allCourses:   a list of all the courses in the database
     * @return a Course Object
     **/
    public Course getCourse(CourseResult courseResult, List<Course> allCourses) {
        Course course = null;
        int courseId = courseResult.getCourse().getId();
        int numberOfCourses = allCourses.size();
        int index = 0;

        while (index < numberOfCourses && (allCourses.get(index)).getId() != courseId) {
            index++;
        }//end while

        if (index < numberOfCourses && (allCourses.get(index)).getId() == courseId) {
            course = allCourses.get(index);
        }//end if

        return course;
    }//end

    /**
     * getAllCourses
     *
     * @return: An arrayList of all the courses offered
     **/
    public List<Course> getAllCourses() {
        return courses;
    }//end getAllCourses

    /**
     * getCoursesCanTake
     *
     * @param studentNumber: the student number of the student. We want to get all the courses that this student
     *                       has not taken but has the preRequisites to take
     * @return: an arraylist of all the courses the student has the preRequisites for.
     **/
    public List<Course> getCoursesCanTake(int studentNumber) {
        List<Course> coursesNotTaken = getCoursesNotTaken(studentNumber); //get the courses that student has not taken yet
        List<Course> coursesCanTake = new ArrayList<Course>(); //will contain the courses the student can take
        int numOfCoursesNotTaken = coursesNotTaken.size(); //number of courses that the student has not taken
        Course currentCourse;

        for (int i = 0; i < numOfCoursesNotTaken; i++) {
            currentCourse = (Course) coursesNotTaken.get(i);

            if (hasPrerequisites(studentNumber, currentCourse.getName())) { //if the student has all preRequisites for the course
                coursesCanTake.add(currentCourse);
            }//end if

        }//end for

        return coursesCanTake;
    }//end getCoursesCanTake

    /**
     * hasPrerequisites
     *
     * @param studentNumber: the student number of the student we want to check if they have all preReqs to take the
     *                       course with courseName given as a parameter
     * @param courseName:    the name of the course we want to see if the student has all preReqs for it
     * @return: boolean saying if the student has the prerequisites or not
     **/
    public boolean hasPrerequisites(int studentNumber, String courseName) {
        List<CourseResult> coursesTaken = getCourseResultsByStudentId(studentNumber); //the courses the student has taken
        Course course = getCourseByName(courseName); //the course we want to get the preRequisites of
        List<Course> coursePreReqs = null; //the preReqs for the course
        boolean hasPreReqs = true; //initially- we have all preReqs say if there is no preRequisites
        Course currentPreReq; //the current preReq, used for in the while loop
        int outer_index = 0; //index for the outer while loop
        int inner_index; //index for the inner while loop
        int preReqId; //the id of the prerequisite

        if (course != null) { //if the course was found
            coursePreReqs = getAllPrerequisites(course); //gets all the preRequisites of the course

            //determine if all prereqs were taken

            while (hasPreReqs && outer_index < coursePreReqs.size()) { //goes through each preReq Course
                currentPreReq = coursePreReqs.get(outer_index);

                if (currentPreReq != null) {
                    preReqId = currentPreReq.getId();
                    inner_index = 0; //initialize inner_index to 0

                    while (hasPreReqs && inner_index < coursesTaken.size() && preReqId !=
                            (coursesTaken.get(inner_index)).getCourse().getId()) { //goes through each course that the student has taken
                        inner_index++;
                    }//end while

                    if (inner_index >= coursesTaken.size()) { //if we searched through all the courses the student has taken but they
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
     *
     * @param course: the course we want to get all the prerequisites of
     * @return: an arraylist of all prerequisites of the course given as a parameter
     **/
    public List<Course> getAllPrerequisites(Course course) {
        List<Course> prerequisites = new ArrayList<Course>();
        int numberOfCoursePrereqs = coursePrerequisites.size(); //the number of prerequisites
        int courseId = course.getId(); //the course number of the course object
        Course currentCourse; //to keep track of the current course

        for (int i = 0; i < numberOfCoursePrereqs; i++) {
            if ((coursePrerequisites.get(i)).getCourse().getId() == courseId) { //if this is a prerequisite for the course
                currentCourse = getCourseById((coursePrerequisites.get(i)).getPrereqCourse().getId()); //get the course
                prerequisites.add(currentCourse); //add the course to the list of prerequisites
            }//end if
        }//end for

        return prerequisites;
    }//end getAllPrerequisites

    /**
     * getCourseByName
     *
     * @param courseName: given the course name, find that course
     * @return: the course with the name specified as the parameter
     **/
    public Course getCourseByName(String courseName) {
        int numberOfCourses = courses.size(); //the number of all courses
        Course course = null; //the course we will return
        int index = 0; //index for searching

        while (index < numberOfCourses && !((courses.get(index)).getName()).equals(courseName)) {
            index++;
        }//end while

        if (index < numberOfCourses && ((courses.get(index)).getName()).equals(courseName)) { //we found the course
            course = courses.get(index);
        }//end if

        return course;
    }//end getCourseByName

    /**
     * getAllDegrees
     *
     * @return: All the degrees that are offered.
     **/
    public List<Degree> getAllDegrees() {
        return degrees;
    }//end getAllDegrees

    /**
     * getDegreeByName
     *
     * @param degreeName: The name of the degree you want to find information about.
     * @return: The degree with the name given as a parameter or null if that degree does not exist.
     **/
    public Degree getDegreeByName(String degreeName) {
        List<Degree> allDegrees = degrees; //all degrees offered
        int numberOfDegrees = allDegrees.size(); //number of degrees offered
        Degree degree = null;
        int index = 0; //index to loop through degrees

        while (index < numberOfDegrees && !((allDegrees.get(index)).getName()).equals(degreeName)) {
            index++;
        }//end while

        if (index < numberOfDegrees && ((allDegrees.get(index)).getName()).equals(degreeName)) {
            degree = allDegrees.get(index); //get the degree with the name given as a parameter
        }//end if

        return degree;
    }//end getDegreeByName

    /**
     * getDegreeById
     *
     * @param degreeId: The id number of the degree you want to find.
     * @return: The degree with the id given as the parameter.
     **/
    public Degree getDegreeById(int degreeId) {
        List<Degree> allDegrees = degrees; //all degrees offered
        int numberOfDegrees = allDegrees.size(); //number of degrees offered
        Degree degree = null;
        int index = 0; //index to loop through degrees

        while (index < numberOfDegrees && (allDegrees.get(index)).getId() != degreeId) {
            index++;
        }//end while

        if (index < numberOfDegrees && (allDegrees.get(index)).getId() == degreeId) {
            degree = allDegrees.get(index); //get the degree with the id given as a parameter
        }//end if

        return degree;
    }//end getDegreeById

    /*
     * Created by Tiffany Jiang on 2017-06-04
     *
     * Returns a list of course results for the specified student
     */
    public List<CourseResult> getCourseResultsByStudentId (int studentId) {
        List<CourseResult> crByStudentId = new ArrayList<CourseResult>();
        Iterator<CourseResult> crIterator = courseResults.iterator();
        CourseResult currCR;

        while (crIterator.hasNext()) {
            currCR = crIterator.next();

            if (currCR.getStudent().getId() == studentId) {
                crByStudentId.add (currCR);
            }
        }

        return crByStudentId;
    }

    //By F.D.
    //Returns list of all Course Offerings
    public List<CourseOffering> getAllCourseOfferings(){
        return courseOfferings;
    }

    //By F.D.
    //Returns list of courses fy selected term
    public List<CourseOffering> getCourseOfferingsByTerm(TermType term) {
        List<CourseOffering> courseOfferingsByTermList = new ArrayList<CourseOffering>();
        if(term != null) {
            for (int i = 0; i < courseOfferings.size(); i++) {
                try {
                    if (term.getId() == 1 || term.getId() == 2 || term.getId() == 3) {
                        if (term.getId() == (courseOfferings.get(i)).getTermType().getId()) {
                            //Adds course offering based on courseID from CourseOfferings and matching TermID
                            courseOfferingsByTermList.add(courseOfferings.get(i));
                        }
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        }

        return courseOfferingsByTermList;
    }

    /*
     * Created by Tiffany Jiang on 2017-06-07
     *
     * Returns the ID of the GradeType entry whose grade is F, or -1 if no such GradeType exists
     */
    public int getFailingGradeId() {
        int failingGradeId = -1;

        for (int i = 0; i < gradeTypes.size(); i++) {
            if (gradeTypes.get(i).getName().equals("F")) {
                failingGradeId = gradeTypes.get(i).getId();
                break;
            }
        }

        return failingGradeId;
    }

    /*
     * Created by Tiffany Jiang on 2017-06-07
     *
     * Returns the Course object with the specified id, or null if no such course exists
     */
    public Course getCourseById(int courseId) {
        Course result = null;

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                result = courses.get(i);
                break;
            }
        }

        return result;
    }

    /*
     * Created by Tiffany Jiang on 2017-06-07
     *
     * Returns the Department object with the specified id, or null if no such department exists
     */
    public Department getDepartmentById(int departmentId) {
        Department result = null;

        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId() == departmentId) {
                result = departments.get(i);
                break;
            }
        }

        return result;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of courses taken by a given student
     */
    public List<Course> getCoursesTaken(int studentId) {
        List<Course> coursesTaken = new ArrayList<Course>();
        List<CourseResult> crByStudentId = getCourseResultsByStudentId(studentId);

        for (CourseResult result : crByStudentId) {
            coursesTaken.add(getCourseById(result.getCourse().getId()));
        }

        return coursesTaken;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of degree required courses student has taken
    */
    public List<Course> getDegreeCoursesTaken(int studentId, int degreeId) {
        List<Course> coursesTaken = getCoursesTaken(studentId);
        List<Course> degreeCourses = getDegreeCourses(degreeId);
        List<Course> takenDegreeCourses = new ArrayList<Course>();

        for (Course degreeCourse : degreeCourses) {
            if (coursesTaken.contains(degreeCourse)) {
                takenDegreeCourses.add(degreeCourse);
            }
        }

        return takenDegreeCourses;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of courses required by a degree
    */
    public List<Course> getDegreeCourses( int degreeId ) {
        final int REQUIRED_COURSE = 1;
        List<Course> reqCourseList = new ArrayList<Course>();

        for( DegreeCourse course : degreeCourses ){
            if( course.getDegree().getId() == degreeId && course.getDegreeCourseType().getId() == REQUIRED_COURSE){
                reqCourseList.add( getCourseById( course.getCourse().getId() ) );
            }
        }

        return reqCourseList;
    }

    /*
        Created by Matthew Provencher on 2017-06-06

        Returns a list of required degree courses that a given student can take
    */
    public List<Course> getEligibleRequiredCourse(int studentNum, int degreeId) {
        List<Course> coursesTaken = getCoursesTaken(studentNum);
        List<Course> degreeCourses = getDegreeCourses(degreeId);
        List<Course> notTakenDegreeCourses = new ArrayList<Course>();
        List<Course> eligibleDegreeCourses = new ArrayList<Course>();

        for (Course degreeCourse : degreeCourses) {
            if (!(coursesTaken.contains(degreeCourse))) {
                notTakenDegreeCourses.add(degreeCourse);
            }
        }

        for (Course course : notTakenDegreeCourses) {
            if (hasPrerequisites(studentNum, course.getName())) {
                eligibleDegreeCourses.add(course);
            }
        }

        return eligibleDegreeCourses;
    }


    /*
     * Created by Tiffany Jiang on 2017-06-07
     *
     * Adds a new course plan
     */
    public void addToCoursePlan (int courseId, int studentId, int termTypeId, int year) {
        CoursePlan newCoursePlan;
        newCoursePlan = new CoursePlan(getMaxCoursePlanId()+1, getCourseById(courseId), getStudentById(studentId), getTermTypeById(termTypeId), year);
        coursePlans.add(newCoursePlan);
    }

    private Student getStudentById (int studentId){
        Student student = null;

        for (Student s : students) {
            if (s.getId() == studentId) {
                student = s;
                break;
            }
        }
        return student;
    }

    private TermType getTermTypeById (int termTypeId){
        TermType termType = null;

        for (TermType type : termTypes) {
            if (type.getId() == termTypeId) {
                termType = type;
                break;
            }
        }
        return termType;
    }

    //Private helper method for add that returns the next increment of the id
    private int getMaxCoursePlanId() {
        int max = 1;

        for (int i = 0; i<coursePlans.size(); i++) {
            if (coursePlans.get(i).getId() > max) {
                max = coursePlans.get(i).getId();
            }
        }

        return max;
    }

    //These next few methods perform checks (as stated respectively) for adding and modify course plans

    public boolean isValidStudentId (int studentId) {
        boolean validStudentId = false;

        //Does a student with the entered studentId exist?
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                validStudentId = true;
                break;
            }
        }

        return validStudentId;
    }

    public boolean isValidCourseId (int courseId) {
        boolean validCourseId = false;

        //Does a course with the entered courseId exist?
        for (int i = 0; i<courses.size(); i++) {
            if (courses.get(i).getId() == courseId) {
                validCourseId = true;
                break;
            }
        }

        return validCourseId;
    }

    public boolean isValidTermTypeId (int termTypeId) {
        boolean validTermTypeId = false;

        //Does a term type with the entered termTypeId exist?
        for (int i = 0; i<termTypes.size(); i++) {
            if (termTypes.get(i).getId() == termTypeId) {
                validTermTypeId = true;
                break;
            }
        }

        return validTermTypeId;
    }

    public boolean courseOffered (int courseId, int termTypeId) {
        boolean validTerm = false;
        Course course = getCourseById(courseId);

        if (course instanceof ScienceCourse) {
            //Is the course historically offered in this term?
            for (int i = 0; i < courseOfferings.size(); i++) {
                if (courseOfferings.get(i).getCourse().getId() == courseId &&
                        courseOfferings.get(i).getTermType().getId() == termTypeId) {
                    validTerm = true;
                    break;
                }
            }
        } else if (course instanceof UserDefinedCourse) {
            //For user-defined courses, let the user freely enter the the term and year
            validTerm = true;
        }

        return validTerm;
    }

    public boolean coursePlanExists(int courseId, int studentId, int termTypeId, int year) {
        boolean coursePlanExists = false;
        CoursePlan currCoursePlan;

        //Does a course plan for the specified course in the specified term already exist?
        for (int i = 0; i<coursePlans.size(); i++) {
            currCoursePlan = coursePlans.get(i);

            if (currCoursePlan.getCourse().getId() == courseId && currCoursePlan.getStudent().getId() == studentId
                    && currCoursePlan.getTermType().getId() == termTypeId && currCoursePlan.getYear() == year) {
                coursePlanExists = true;
                break;
            }
        }

        return coursePlanExists;
    }

    /*
     * Created by Tiffany Jiang on 2017-06-07
     *
     * Moves a course in an existing course plan to a different term/year
     */
    public void moveCourse (int coursePlanId, int newTermTypeId, int newYear) {
        CoursePlan coursePlan;

        for (int i = 0; i<coursePlans.size(); i++) {
            coursePlan = coursePlans.get(i);
            if (coursePlan.getId() == coursePlanId) {
                coursePlan.setTermType(getTermTypeById(newTermTypeId));
                coursePlan.setYear(newYear);
            }
        }
    }

    /*
     * Created by Tiffany Jiang on 2017-06-07
     *
     * Removes a course plan
     */
    public void removeFromCoursePlan (int coursePlanId) {
        for (int i = 0; i<coursePlans.size(); i++) {
            if (coursePlans.get(i).getId() == coursePlanId) {
                coursePlans.remove(i);
                break;
            }
        }
    }

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

    public CoursePlan getCoursePlan (int courseId, int studentId, int termTypeId, int year) {
        CoursePlan result = null;
        CoursePlan currCoursePlan;

        for (int i = 0; i<coursePlans.size(); i++) {
            currCoursePlan = coursePlans.get(i);

            if (currCoursePlan.getCourse().getId() == courseId && currCoursePlan.getStudent().getId() == studentId
                    && currCoursePlan.getTermType().getId() == termTypeId && currCoursePlan.getYear() == year) {
                result = currCoursePlan;
                break;
            }
        }

        return result;
    }
}

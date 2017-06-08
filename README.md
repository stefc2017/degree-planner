# Degree Planner

[Iteration 1 Developer Log](https://docs.google.com/document/d/1VlnjG8H_lTQEfeJ99SGC2vxbQOdTMdJD4wwznMJKhIU/edit?usp=sharing)

[Repository](https://github.com/tiffcj/degree-planner.git)

Submission Contents:

Main package: comp3350.degree_planner

Additional Packages:

- comp3350.degree_planner.application
  contains code for calling to initialize the database
  -- Main.java - calls Services.java for initializing database
  -- Services.java - holds/manages stub database object

- comp3350.degree_planner.business
  contains code for business logic, linking database services to the presentation layer
  most code here simply calls database services and does no further processing
  -- AccessCourses.java
  -- AccessDegrees.java
  -- CompletedCourses.java
  -- CreditHours.java

- comp3350.degree_planner.objects
  contains classes for business objects
  -- Course.java
  -- CourseOffering.java
  -- CoursePlan.java
  -- CoursePrerequisite.java
  -- CourseResult.java
  -- Degree.java
  -- DegreeCourse.java
  -- DegreeCourseType.java
  -- Department.java
  -- GradeType.java
  -- Rating.java
  -- RatingType.java
  -- ScienceCourse.java
  -- Student.java
  -- TermType.java
  -- UserDefinedCourse.java

- comp3350.degree_planner.persistence
  contains stub database code as well as code for retrieving data from the stub
  -- DataAccess.java - contains stub interface
  -- DataAccessStub.java - contains stub implementation

- comp3350.degree_planner.presentation
  contains code for the UI
  -- DegreeInfoActivity - shows detailed information about a specific degree
  -- DegreesActivity - shows a list of Degrees (user can choose one to go to DegreeInfoActivity)
  -- MainActivity - shows app home screen
  -- Splash - shows startup screen

- comp3350.degree_planner.tests
  contain unit tests for methods / classes in the above packages
  each test package corresponds to the package of the same name (above)
  also contains AllTests.java, which can be used to run all unit tests

Major Features:

- View Degree Requirements
  The user can view specific requirements for a degree, including required courses
  Implemented in:
  -- DegreeInfoActivity
  -- AccessCourses.getDegreeCourses
  -- DataAccessStub.getDegreeCourses
  -- AccessDegrees.getDegreeById
  -- DataAccessStub.getDegreeById
  To reach this feature in the UI, tap "Degree Information" and then tap a degree on the list.
 
- See Progress Towards Degree
  Processing for determining the courses a student has already taken and those the student has not taken for a given degree
  Implemented in:
  -- CompletedCourses.getCompletedCourses
  -- DataAccessStub.getCourseResultByStudentId
  -- DataAccessStub.getCourseById
  -- DataAccessStub.getDepartmentById

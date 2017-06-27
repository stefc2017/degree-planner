package comp3350.degree_planner.business;

/*
 * Created by Tiffany Jiang on 2017-06-04.
 * Modified on 2017-06-07.
 */

import java.util.List;

import comp3350.degree_planner.objects.Course;
import comp3350.degree_planner.objects.CourseResult;
import comp3350.degree_planner.objects.Department;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.persistence.DataAccess;

public class CompletedCourses {
    private DataAccess dataAccess;

    public CompletedCourses(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /*
     * Retrieves completed courses for a student
     *
     * Returns a 2D array, with each entry in the array being a String array matching the example below
     * Or returns null if either student doesn't exist or student has not taken any courses,
     * or a foreign key points to an entity that does not exist
     *
     * Notes from Tiff:
     * Not sure yet how the UI would like this info to be returned.
     * For now, I'm going to assume they want to display it as e.g. Comp 1010: Introduction to Computer Science I, Passed
     * This can be easily changed later
     */
    public String[][] getCompletedCourses(int studentId) {
        List<CourseResult> completedCourses = dataAccess.getCourseResultsByStudentId(studentId);
        String[][] completedCoursesDisplay = null;
        //Processing/for loop variables:
        CourseResult currCourseResult;
        Course currCourse;
        ScienceCourse currSciCourse;
        UserDefinedCourse currUserCourse;
        String sciCourseCode;
        Department currSciCourseDep;
        int failingGradeId;

        //Error checking: completed courses retrieved isn't empty
        // (if is, student has not taken courses - return null)
        if (completedCourses.size() != 0) {
            completedCoursesDisplay = new String[completedCourses.size()][2];

            for (int i = 0; i < completedCourses.size(); i++) {
                currCourseResult = completedCourses.get(i);
                currCourse = dataAccess.getCourseById(currCourseResult.getCourse().getId());

                //Error checking: course returned is not null
                if (currCourse != null) {

                    //Getting course name display i.e. first index of String array entry
                    if (currCourse instanceof ScienceCourse) {
                        currSciCourse = (ScienceCourse) currCourse;
                        currSciCourseDep = dataAccess.getDepartmentById(currSciCourse.getDepartmentId());

                        //Error checking: department returned is not null (if is, bypass course code in returned String)
                        if (currSciCourseDep != null) {
                            sciCourseCode = currSciCourseDep.getAbbreviation() + " " + currSciCourse.getCourseNumber() + ": ";
                        } else {
                            sciCourseCode = "";
                        }

                        completedCoursesDisplay[i][0] = sciCourseCode + currSciCourse.getName();
                    } else if (currCourse instanceof UserDefinedCourse) {
                        currUserCourse = (UserDefinedCourse) currCourse;
                        completedCoursesDisplay[i][0] = currUserCourse.getFullAbbreviation() + ": " + currUserCourse.getName();
                    } else {
                        completedCoursesDisplay[i][0] = currCourse.getName();
                    }

                    //Getting pass/fail i.e. second index
                    failingGradeId = dataAccess.getFailingGradeId();
                    //Error checking: failingGradeId was found
                    if (failingGradeId != -1) {
                        if (currCourseResult.getGrade().getId() != failingGradeId) {
                            completedCoursesDisplay[i][1] = "Passed";
                        } else {
                            completedCoursesDisplay[i][1] = "Failed";
                        }
                    }
                }
            }
        }

        return completedCoursesDisplay;
    }
}

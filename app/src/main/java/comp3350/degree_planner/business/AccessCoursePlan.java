package comp3350.degree_planner.business;

import android.util.Log;

import comp3350.degree_planner.business.exceptions.CourseAlreadyPlannedForTermException;
import comp3350.degree_planner.business.exceptions.CourseNotOfferedInTermException;
import comp3350.degree_planner.business.exceptions.CourseDoesNotExistException;
import comp3350.degree_planner.business.exceptions.CoursePlanDoesNotExistException;
import comp3350.degree_planner.business.exceptions.StudentDoesNotExistException;
import comp3350.degree_planner.business.exceptions.TermTypeDoesNotExistException;
import comp3350.degree_planner.objects.Student;
import comp3350.degree_planner.objects.TermType;
import comp3350.degree_planner.persistence.DataAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


import comp3350.degree_planner.objects.CoursePlan;
import comp3350.degree_planner.objects.ScienceCourse;
import comp3350.degree_planner.objects.UserDefinedCourse;
import comp3350.degree_planner.presentation.CoursePlanAdapter;

/**
 * Created by Tiffany Jiang on 2017-06-04.
 *
 * This class allows for modifications to course plans
 */

public class AccessCoursePlan {
    private DataAccess dataAccess;
    public AccessCoursePlan(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    //Adds a course plan
    //Throws 5 different custom exceptions based on the cases specified below,
    // in which error can potentially occur
    //May throw a different unexpected exception, which is caught and rethrown
    public void addToCoursePlan(int courseId, int studentId, int termTypeId, int year) throws Exception {
        try {
            if (!dataAccess.isValidStudentId(studentId)) {
                throw new StudentDoesNotExistException();
            } else if (!dataAccess.isValidCourseId(courseId)) {
                throw new CourseDoesNotExistException();
            } else if (!dataAccess.isValidTermTypeId(termTypeId)) {
                throw new TermTypeDoesNotExistException();
            } else if (!dataAccess.courseOffered(courseId, termTypeId)) {
                throw new CourseNotOfferedInTermException();
            } else if (dataAccess.coursePlanExists(courseId, studentId, termTypeId, year)) {
                throw new CourseAlreadyPlannedForTermException();
            } else {
                dataAccess.addToCoursePlan(courseId, studentId, termTypeId, year);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //Moves a course plan to a different term
    //Throws 4 different exceptions based on the cases specified below, in which error can occur
    public void moveCourse(int coursePlanId, int newTermTypeId, int newYear) throws Exception {
        CoursePlan cp;

        try {
            cp = dataAccess.getCoursePlan(coursePlanId);

            if (cp != null) { //Course Plan exists
                if (!dataAccess.isValidTermTypeId(newTermTypeId)) {
                    throw new TermTypeDoesNotExistException();
                } else if (!dataAccess.courseOffered(cp.getCourse().getId(), newTermTypeId)) {
                    throw new CourseNotOfferedInTermException();
                } else if (dataAccess.coursePlanExists(cp.getCourse().getId(), cp.getStudent().getId(), newTermTypeId, newYear)) {
                    throw new CourseAlreadyPlannedForTermException();
                } else {
                    dataAccess.moveCourse(coursePlanId, newTermTypeId, newYear);
                }
            } else { //Course Plan does not exist - can't modify something that doesn't exist
                throw new CoursePlanDoesNotExistException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //Removes a course plan
    //Throws CoursePlanDoesNotExistException if coursePlanId parameter refers to a nonexistent Course Plan
    public void removeFromCoursePlan(int coursePlanId) throws Exception {
        CoursePlan cp;

        try {
            cp = dataAccess.getCoursePlan(coursePlanId);

            if (cp != null) {
                dataAccess.removeFromCoursePlan(coursePlanId);
            } else {
                throw new CoursePlanDoesNotExistException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Return a list of int tuples and CoursePlans
     * UI renders different ListItem layouts for CoursePlans and section headers(Ex. Fall 2017)
     * Parameters:
     * int studentId    Gives the student whose course plans will be returned
     */
    public List getCoursePlansAndHeaders(int studentId) throws Exception {
        ArrayList<Object> coursePlansAndHeaders = new ArrayList<Object>();  // List to be returned
        List coursePlans;   // Course plans as retrieved from the persistence layer
        // These will be in sorted order
        ListIterator cpIterator;    // Used to iterate over coursePlans
        CoursePlan currCP;  // Current course plan in coursePlans

        ArrayList<Integer> header = null;      // Header text for each section
        TermType currentTerm;   // Term for the current course plan, used in headers
        int currentYear;        // Year for the current course plan, used in headers
        final int YEAR_POS = 1; // year is at position 1 in the tuple
        boolean newHeader;      // Shows if a new section header is needed

        try {
            // Get the course plans from the database
            coursePlans = dataAccess.getCoursePlansByStudentId(studentId);

            // Process the results into a list with section headers

            currentTerm = null;
            currentYear = 0;
            newHeader = false;
            cpIterator = coursePlans.listIterator();
            while (cpIterator.hasNext()) {
                currCP = (CoursePlan)cpIterator.next();

                // Update the header text if the term / year has changed
                if (currentTerm == null || !currCP.getTermType().equals(currentTerm)) {
                    header = new ArrayList<>();
                    currentTerm = currCP.getTermType();

                    // Ordinals are WINTER(0) SUMMER(1) FALL(2)
                    if(currentTerm.getSeason().equalsIgnoreCase("Fall")){
                        header.add(Season.FALL.ordinal());
                    }else if(currentTerm.getSeason().equalsIgnoreCase("Summer")){
                        header.add(Season.SUMMER.ordinal());
                    }else if(currentTerm.getSeason().equalsIgnoreCase("Winter")){
                        header.add(Season.WINTER.ordinal());
                    }

                    header.add(currentYear);
                    newHeader = true;
                }

                if (currCP.getYear() != currentYear) {
                    currentYear = currCP.getYear();
                    header.set(YEAR_POS, currentYear);
                    newHeader = true;
                }

                if (newHeader) {
                    coursePlansAndHeaders.add(header);
                    header = null;
                    newHeader = false;
                }

                coursePlansAndHeaders.add(currCP);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return coursePlansAndHeaders;
    }

    //Retrieve a course plan
    //Throws CoursePlanDoesNotExistException if coursePlanId parameter refers to a nonexistent Course Plan
    public CoursePlan getCoursePlan(int coursePlanId) throws Exception {
        CoursePlan cp = null;

        try {
            cp = dataAccess.getCoursePlan(coursePlanId);
            if (cp == null) { throw new CoursePlanDoesNotExistException(); }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return cp;
    }

}

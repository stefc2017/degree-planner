package comp3350.degree_planner.objects;

import android.support.annotation.NonNull;

/**
 * Created by Kaleigh on 2017-06-01.
 * Modified by Tiffany on 2017-06-04
 * Modified by Kaleigh on 2017-06-07.
 * Modified by Kaleigh on 2017-06-28.
 *
 * A CoursePlan is a record of a student planning to take a course
 * in a particular term/year.
 */

public class CoursePlan implements Comparable<CoursePlan> {
    private int id;
    private Course course;
    private Student student;
    private TermType termType;
    private int year;

    public CoursePlan(int id, Course course, Student student, TermType termType, int year) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.termType = termType;
        this.year = year;
    }

    public Course getCourse() { return this.course; }

    public Student getStudent() { return student; }

    public TermType getTermType() { return termType; }

    public int getYear() { return year; }

    public int getId() { return id; }

    public void setTermType (TermType termType) {
        this.termType = termType;
    }

    public void setYear (int year) { this.year = year; }

    /*
     * compareTo
     *
     * Compares this CoursePlan with another CoursePlan object. The comparison
     * checks the year first, then TermTypes if the years are equal.
     * It returns
     *      a negative number if this is less than otherCP
     *      0 if the CoursePlans are equal
     *      a positive number if this is greater than otherCP
     * Parameters:
     * otherCP  CoursePlan object to compare with
     */

    public int compareTo(@NonNull CoursePlan otherCP) {
        int compareResult;

        compareResult = this.year - otherCP.year;
        if (compareResult == 0) {
            // Years are equal - determine which object is greater using the TermTypes
            compareResult = this.termType.compareTo(otherCP.termType);
        }

        return compareResult;
    }
}

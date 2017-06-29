package comp3350.degree_planner.objects;

import android.support.annotation.NonNull;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-07.
 * Modified by Kaleigh on 2017-06-28.
 *
 * A Term Type is used to store terms/semesters in which a course may be taken.
 * For example, there may be a Fall term or a Winter term.
 */

public class TermType implements Comparable<TermType> {
    private int id;
    private String season;      // May be Fall, Winter, Summer

    public TermType(int id, String season) {
        this.id = id;
        this.season = season;
    }

    public int getId() { return id; }

    public String getSeason() { return season; }

    /*
     * equals
     *
     * Checks the ID of the given TermType object to see if it matches
     * this ID.
     * Parameters:
     * otherTT  TermType object to compare with
     */
    public boolean equals(TermType otherTT) {
        return this.id == otherTT.id;
    }

    /*
     * compareTo
     *
     * Compares this TermType with another TermType object. The comparison
     * checks the IDs of the objects.
     * It returns
     *      a negative number if this is less than otherTT
     *      0 if the TermTypes are equal
     *      a positive number if this is greater than otherTT
     * Parameters:
     * otherTT  TermType object to compare with
     */

    public int compareTo(@NonNull TermType otherTT) {
        return this.id - otherTT.id;
    }
}

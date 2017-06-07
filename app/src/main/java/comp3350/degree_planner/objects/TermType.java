package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 * Modified by Kaleigh on 2017-06-07.
 *
 * A Term Type is used to store terms/semesters in which a course may be taken.
 * For example, there may be a Fall term or a Winter term.
 */

public class TermType {
    private int id;
    private String season;      // May be Fall, Winter, Summer

    public TermType(int id, String season) {
        this.id = id;
        this.season = season;
    }

    public int getId() { return id; }

    public String getSeason() { return season; }
}

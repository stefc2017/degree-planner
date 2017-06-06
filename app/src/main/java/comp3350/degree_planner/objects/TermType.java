package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
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

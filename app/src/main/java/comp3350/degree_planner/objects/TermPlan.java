package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class TermPlan {
    private TermType type;
    private int year;
    private Course[] coursesTaking;

    public TermPlan(TermType type, int year) {
        this.type = type;
        this.year = year;
    }

    public TermType getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public Course[] getCoursesTaking() {
        return coursesTaking;
    }
}

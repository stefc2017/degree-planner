package comp3350.degree_planner.objects;

/**
 * Created by Kaleigh on 2017-05-31.
 */

public class ScienceCourse extends Course {
    private String description;
    private TermType[] termsOffered;

    public ScienceCourse(String name, String creditHours, CourseType type, String description, TermType[] termsOffered) {
        super(name, creditHours, type);
        this.description = description;
        this.termsOffered = termsOffered;
    }

    public String getDescription() {
        return description;
    }

    public TermType[] getTermsOffered() {
        return termsOffered;
    }
}
